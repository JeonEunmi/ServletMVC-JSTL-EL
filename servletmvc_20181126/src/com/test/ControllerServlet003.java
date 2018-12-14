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

	//Map ��ü �غ�
	// <Ŀ�ǵ�, �ڵ鷯�ν��Ͻ�> ���� ���� ����
	//����) �ڵ鷯�ν��Ͻ����� �������̽� �ڷ������� �غ�
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();

	//���� ���� ��û�� �ڵ� ȣ��Ǵ� �޼ҵ�. �� ���� ȣ�� ����.
	@Override
	public void init() throws ServletException {
		
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
		Iterator keyIter = prop.keySet().iterator();
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			
			//Ư�� Ű�� ������ �� Ȯ��
			String handlerClassName = prop.getProperty(command);
			
			//System.out.printf("%s -> %s%n", command, handlerClassName);
			
			try {
				
				//���ڿ� ������ Ŭ���� ������ ������ ��ü ����
				Class<?> handlerClass = Class.forName(handlerClassName);
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
				
				//�̸� �غ�� Map �÷��ǿ� ��ü ����
				//����� ��û�ּҿ� �´� ��ü ���� Ž���� ���� �غ� ����
				//->�������(�ʵ�)�� ����� �����̹Ƿ� �ٸ� �޼ҵ忡�� ��� ����
				this.commandHandlerMap.put(command, handlerInstance);
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}			
			
		}
	}
	
	//����ڰ� ��û�ø��� ��ȣ��Ǵ� �޼ҵ�
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("Hello, World!");
		
		//2�ܰ�. ��û �м� -> Command ����
		//-> ���� ���ڿ��� ?cmd=hello ���� �ʿ�
		String type = request.getParameter("cmd");
		
		CommandHandler result = null;
		if (type == null) {
			result = new NullHandler();
		} else {
			//����� ��û �ּ� �м��� Map �÷��� ���� ���
			//����� ��û �ּҰ� "hello"�� ��� HelloHandler ��ü ���� ��ȯ
			result = this.commandHandlerMap.get(type);
			//System.out.println(result.toString());
		}
		
		//Handler ��ü�� ���� �޼ҵ� ȣ��
		String uri = result.process(request, response);
		
		//4�ܰ�. �˸��� ��(.jsp)�� ������ or �˸��� �������� �����̷�Ʈ
		//-> Ư�� .jsp ������ �ּ� ���� �ʿ�
		RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
		dispatcher.forward(request, response);
		
	}

}
