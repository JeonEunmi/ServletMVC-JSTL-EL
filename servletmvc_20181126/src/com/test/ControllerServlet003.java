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

public class ControllerServlet003 extends HttpServlet {

	//Map 객체 준비
	// <커맨드, 핸들러인스턴스> 매핑 정보 저장
	//주의) 핸들러인스턴스에는 인터페이스 자료형으로 준비
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();

	//최초 서블릿 요청시 자동 호출되는 메소드. 한 번만 호출 가능.
	@Override
	public void init() throws ServletException {
		
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
		Iterator keyIter = prop.keySet().iterator();
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			
			//특정 키를 가지고 값 확인
			String handlerClassName = prop.getProperty(command);
			
			//System.out.printf("%s -> %s%n", command, handlerClassName);
			
			try {
				
				//문자열 형태의 클래스 정보를 가지고 객체 생성
				Class<?> handlerClass = Class.forName(handlerClassName);
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
				
				//미리 준비된 Map 컬렉션에 객체 저장
				//사용자 요청주소와 맞는 객체 정보 탐색을 위한 준비 과정
				//->멤버변수(필드)에 저장된 상태이므로 다른 메소드에서 사용 가능
				this.commandHandlerMap.put(command, handlerInstance);
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}			
			
		}
	}
	
	//사용자가 요청시마다 재호출되는 메소드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("Hello, World!");
		
		//2단계. 요청 분석 -> Command 패턴
		//-> 쿼리 문자열에 ?cmd=hello 지정 필요
		String type = request.getParameter("cmd");
		
		CommandHandler result = null;
		if (type == null) {
			result = new NullHandler();
		} else {
			//사용자 요청 주소 분석시 Map 컬렉션 정보 사용
			//사용자 요청 주소가 "hello"인 경우 HelloHandler 객체 정보 반환
			result = this.commandHandlerMap.get(type);
			//System.out.println(result.toString());
		}
		
		//Handler 객체의 공통 메소드 호출
		String uri = result.process(request, response);
		
		//4단계. 알맞은 뷰(.jsp)로 포워딩 or 알맞은 서블릿으로 리다이렉트
		//-> 특정 .jsp 페이지 주소 지정 필요
		RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
		dispatcher.forward(request, response);
		
	}

}
