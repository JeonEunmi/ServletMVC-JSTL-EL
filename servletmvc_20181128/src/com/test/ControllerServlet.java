package com.test;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
	//<url, method_name> 매핑 정보 저장
	private Map<String, String> commandMethodMap = new HashMap<>();
	
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
			//"hello" -> "메소드이름"
			
			//미리 준비된 Map 컬렉션에 메소드이름 저장
			//사용자 요청주소와 맞는 메소드 정보 탐색을 위한 준비 과정
			//->멤버변수(필드)에 저장된 상태이므로 다른 메소드에서 사용 가능
			this.commandMethodMap.put(key, value);
			//"hello" -> 메소드이름
			
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
		
		//2단계. 요청 분석 -> URL 패턴
		//-> 서브주소(/mvc/*) 지정 필요
		String url = request.getRequestURI();
		//System.out.println(url);
		//->/프로젝트주소/메인주소/서브주소
		url = url.substring(request.getContextPath().length());
		//System.out.println(url);
		//->/메인주소/서브주소
		
		//준비된 Handler 클래스의 특정 메소드 호출
		//->서브주소와 매핑되는 메소드 호출
		//->command.properties 파일에 등록된 메소드
		//->invoke("메소드이름") 메소드 필요
		String method = this.commandMethodMap.get(url);
		Method m = null;
		String uri = "";
		try {
			m = HelloHandler.class.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
			uri = (String)m.invoke(new HelloHandler(), request, response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
			
		//4단계. 알맞은 뷰(.jsp)로 포워딩 or 알맞은 서블릿으로 리다이렉트
		//-> 특정 .jsp 페이지 주소 지정 필요
		RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
		dispatcher.forward(request, response);

	}
	
	
}
