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
	
	//Map ��ü �غ�
	// <Ŀ�ǵ�, �ڵ鷯�ν��Ͻ�> ���� ���� ����
	//����) �ڵ鷯�ν��Ͻ����� �������̽� �ڷ������� �غ�
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
	
	//���� �ʱ�ȭ ��� �޼ҵ�
	//�ڵ� ȣ�� �޼ҵ� -> ���� ��û�� �� ���� ȣ��ȴ�
	@Override
	public void init() throws ServletException {
		//����� ��û�ּ� �м��� ���� ȯ�� ���� ������ �޸𸮿� �ε�
		//�ε��� �̸� �غ�� Map��ü�� ���� ���� ����
		
		/* web.xml�� <init-param> ���� Ȯ�� */
		String configFile = getInitParameter("configFile");
		
		//�� ������Ƽ(Map �÷��� �� ����) ��ü �غ�
		//������ ������ .properties ���Ͽ� Ű, �� ���� ����
		Properties prop = new Properties();

		//������ ������ �ּ� Ȯ��
		String configFilePath = getServletContext().getRealPath(configFile);
		try (FileReader fis = new FileReader(configFilePath)) {
			
			//������ ������ .properties ������ �о�鿩�� 
			//������Ƽ(Map �÷��� �� ����) ��ü�� ���
			prop.load(fis);
			
		} catch (IOException e) {
			throw new ServletException(e);
		}
		
		
		//Iterator�� ���� Ű Ž��
		Iterator keySet = prop.keySet().iterator();
		while (keySet.hasNext()) {
			String key = (String) keySet.next();
			
			//Ư�� Ű�� ������ �� Ȯ��
			String value = prop.getProperty(key);
			
			//System.out.printf("%s -> %s%n", key, value);
			//"hello" -> "com.test.HelloHandler"
			
			try {
				
				//���ڿ� ������ Ŭ���� ������ ������ ��ü ����
				Class<?> handlerClass = Class.forName(value);
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
				
				//�̸� �غ�� Map �÷��ǿ� ��ü ����
				//����� ��û�ּҿ� �´� ��ü ���� Ž���� ���� �غ� ����
				//->�������(�ʵ�)�� ����� �����̹Ƿ� �ٸ� �޼ҵ忡�� ��� ����
				this.commandHandlerMap.put(key, handlerInstance);
				//"hello" -> com.test.HelloHandler ��ü
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}			
			
		}		
	
	}
	

	//�ڵ� ȣ�� �޼ҵ� -> GET ��� ȣ��
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processRequest(req, resp);
	}


	//�ڵ� ȣ�� �޼ҵ� -> POST ��� ȣ��
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processRequest(req, resp);
	}

	//���� ó�� �޼ҵ�
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//����� ��û�ּ� �м� �� ó��
		//����� ��û�ּҸ� ���� ȯ�� ���� ���� �غ� 
		//-> ������Ƽ(.properties) ����
		//-> �޸𸮿� �ε��ؼ� Map ��ü�� �����ϴ� ���� �ʿ�
		//-> init() �޼ҵ�
		
		//2�ܰ�. ��û �м� -> Command ����
		//-> �ּ� ���κп� �����ּ�(/�����ּ�.it) ���� �ʿ�
		String url = request.getRequestURI();
		//System.out.println(url);
		//->/������Ʈ�ּ�/�����ּ�.it
		url = url.substring(request.getContextPath().length());
		//System.out.println(url);
		//->/�����ּ�.it
		
		CommandHandler result = null;
		//����� ��û �ּ� �м��� Map �÷��� ���� ���
		//����� ��û �ּҰ� "/hello.it"�� ��� HelloHandler ��ü ���� ��ȯ
		result = this.commandHandlerMap.get(url);
		
		//Handler ��ü�� ���� �޼ҵ� ȣ��
		String uri = result.process(request, response);
			
		//4�ܰ�. �˸��� ��(.jsp)�� ������ or �˸��� �������� �����̷�Ʈ
		//-> Ȯ���� .jsp�� ���� ������ �׼� ó��
		//-> Ȯ���� .it�� ���(�����ּ�)�� �����̷�Ʈ �׼� ó��
		if (uri.contains(".it")) {
			response.sendRedirect(uri);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
			dispatcher.forward(request, response);
		}

	}
	
	
}
