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
	
	//Map ��ü �غ�
	//<url, method_name> ���� ���� ����
	private Map<String, String> commandMethodMap = new HashMap<>();
	
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
			//"hello" -> "�޼ҵ��̸�"
			
			//�̸� �غ�� Map �÷��ǿ� �޼ҵ��̸� ����
			//����� ��û�ּҿ� �´� �޼ҵ� ���� Ž���� ���� �غ� ����
			//->�������(�ʵ�)�� ����� �����̹Ƿ� �ٸ� �޼ҵ忡�� ��� ����
			this.commandMethodMap.put(key, value);
			//"hello" -> �޼ҵ��̸�
			
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
		
		//2�ܰ�. ��û �м� -> URL ����
		//-> �����ּ�(/mvc/*) ���� �ʿ�
		String url = request.getRequestURI();
		//System.out.println(url);
		//->/������Ʈ�ּ�/�����ּ�/�����ּ�
		url = url.substring(request.getContextPath().length());
		//System.out.println(url);
		//->/�����ּ�/�����ּ�
		
		//�غ�� Handler Ŭ������ Ư�� �޼ҵ� ȣ��
		//->�����ּҿ� ���εǴ� �޼ҵ� ȣ��
		//->command.properties ���Ͽ� ��ϵ� �޼ҵ�
		//->invoke("�޼ҵ��̸�") �޼ҵ� �ʿ�
		String method = this.commandMethodMap.get(url);
		Method m = null;
		String uri = "";
		try {
			m = HelloHandler.class.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
			uri = (String)m.invoke(new HelloHandler(), request, response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
			
		//4�ܰ�. �˸��� ��(.jsp)�� ������ or �˸��� �������� �����̷�Ʈ
		//-> Ư�� .jsp ������ �ּ� ���� �ʿ�
		RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
		dispatcher.forward(request, response);

	}
	
	
}
