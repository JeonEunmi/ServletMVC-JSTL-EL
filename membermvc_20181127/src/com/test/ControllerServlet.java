package com.test;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {
	
	//Map 객체 준비
	// <커맨드, 핸들러인스턴스> 매핑 정보 저장
	//주의) 핸들러인스턴스에는 인터페이스 자료형으로 준비
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
	
	//서블릿 초기화 담당 메소드
	//자동 호출 메소드 -> 최초 요청시 한 번만 호출된다
	@Override
	public void init() throws ServletException {
		//사용자 요청주소 분석을 위한 환경 설정 파일을 메모리에 로딩
		//로딩후 미리 준비된 Map객체에 정보 저장 예정
		
		/* web.xml의 <init-param> 정보 확인 */
		String configFile = getInitParameter("configFile");
		
		//빈 프로퍼티(Map 컬렉션 한 형태) 객체 준비
		//물리적 파일인 .properties 파일에 키, 값 저장 가능
		Properties prop = new Properties();

		//파일의 물리적 주소 확인
		String configFilePath = getServletContext().getRealPath(configFile);
		try (FileReader fis = new FileReader(configFilePath)) {
			
			//물리적 파일인 .properties 파일을 읽어들여서 
			//프로퍼티(Map 컬렉션 한 형태) 객체로 등록
			prop.load(fis);
			
		} catch (IOException e) {
			throw new ServletException(e);
		}
		
		
		//Iterator에 의한 키 탐색
		Iterator keySet = prop.keySet().iterator();
		while (keySet.hasNext()) {
			String key = (String) keySet.next();
			
			//특정 키를 가지고 값 확인
			String value = prop.getProperty(key);
			
			//System.out.printf("%s -> %s%n", key, value);
			//"hello" -> "com.test.HelloHandler"
			
			try {
				
				//문자열 형태의 클래스 정보를 가지고 객체 생성
				Class<?> handlerClass = Class.forName(value);
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
				
				//미리 준비된 Map 컬렉션에 객체 저장
				//사용자 요청주소와 맞는 객체 정보 탐색을 위한 준비 과정
				//->멤버변수(필드)에 저장된 상태이므로 다른 메소드에서 사용 가능
				this.commandHandlerMap.put(key, handlerInstance);
				//"hello" -> com.test.HelloHandler 객체
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}			
			
		}		
	
	}
	

	//자동 호출 메소드 -> GET 방식 호출
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processRequest(req, resp);
	}


	//자동 호출 메소드 -> POST 방식 호출
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processRequest(req, resp);
	}

	//통합 처리 메소드
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//사용자 요청주소 분석 및 처리
		//사용자 요청주소를 위한 환경 설정 파일 준비 
		//-> 프로퍼티(.properties) 파일
		//-> 메모리에 로딩해서 Map 객체에 저장하는 과정 필요
		//-> init() 메소드
		
		//2단계. 요청 분석 -> Command 패턴
		//-> 주소 끝부분에 서브주소(/서브주소.it) 지정 필요
		String url = request.getRequestURI();
		//System.out.println(url);
		//->/프로젝트주소/서브주소.it
		url = url.substring(request.getContextPath().length());
		//System.out.println(url);
		//->/서브주소.it
		
		CommandHandler result = null;
		//사용자 요청 주소 분석시 Map 컬렉션 정보 사용
		//사용자 요청 주소가 "/hello.it"인 경우 HelloHandler 객체 정보 반환
		result = this.commandHandlerMap.get(url);
		
		//Handler 객체의 공통 메소드 호출
		String uri = result.process(request, response);
			
		//4단계. 알맞은 뷰(.jsp)로 포워딩 or 알맞은 서블릿으로 리다이렉트
		//-> 확장자 .jsp인 경우는 포워딩 액션 처리
		//-> 확장자 .it인 경우(서블릿주소)는 리다이렉트 액션 처리
		if (uri.contains(".it")) {
			response.sendRedirect(uri);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
			dispatcher.forward(request, response);
		}

	}
	
	
}
