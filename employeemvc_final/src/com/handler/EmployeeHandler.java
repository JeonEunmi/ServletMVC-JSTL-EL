package com.handler;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.domain.Department;
import com.domain.Employee;
import com.domain.Job;
import com.domain.Login;
import com.domain.Picture;
import com.domain.Region;
import com.persistance.DepartmentDAO;
import com.persistance.EmployeeDAO;
import com.persistance.JobDAO;
import com.persistance.LoginDAO;
import com.persistance.PictureDAO;
import com.persistance.RegionDAO;
import com.util.Util;

public class EmployeeHandler {

	// DAO ��ü�� ���� ���� ������ ���� �ʵ� ����
	private EmployeeDAO emloyeeDAO = new EmployeeDAO();
	private RegionDAO regionDAO = new RegionDAO();
	private DepartmentDAO departmentDAO = new DepartmentDAO();
	private JobDAO jobDAO = new JobDAO();
	private LoginDAO loginDAO = new LoginDAO();

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String employeeList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ���� �Ǵ� �˻� ������ ���� �޾Ƽ� ���� �Ǵ� �˻� �׼� �����ϴ� ����

		// request.setCharacterEncoding("UTF-8");
		// ->���ڵ� ���ͷ� ��ü

		String order = request.getParameter("order");
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		
		if (key == null || key.equals("all")) {
			key = "all";
			value = "";
		}
		if (order == null) {
			order = "empId";
		}

		List<Employee> list = this.emloyeeDAO.list(key, value, order);
		request.setAttribute("list", list);
		request.setAttribute("totalcount", this.emloyeeDAO.totalcount());
		request.setAttribute("count", list.size());
		request.setAttribute("key", key);
		request.setAttribute("value", value);

		// ����) SELECT �׼� ó�� �Ŀ���
		// ������ �׼��� ���� ���� ��ȯ
		// ����) ����η� ����
		return "/WEB-INF/views/employee/employeelist.jsp";
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String employeeInsertForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ����, �μ�, ���� ������ �о���� ���� �߰�
		List<Region> regionList = this.regionDAO.list2();
		List<Department> departmentList = this.departmentDAO.list2();
		List<Job> positionList = this.jobDAO.list2();
		request.setAttribute("regionList", regionList);
		request.setAttribute("departmentList", departmentList);
		request.setAttribute("positionList", positionList);

		// ����) SELECT �׼� ó�� �Ŀ���
		// ������ �׼��� ���� ���� ��ȯ
		// ����) ����η� ����
		return "/WEB-INF/views/employee/employeeinsertform.jsp";
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String employeeInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request.setCharacterEncoding("UTF-8");
		// ->���ڵ� ���ͷ� ��ü

		String name_ = request.getParameter("name_");
		String ssn = request.getParameter("ssn");
		String phone = request.getParameter("phone");
		String hiredate = request.getParameter("hiredate");
		String regId = request.getParameter("regId");
		String deptId = request.getParameter("deptId");
		String jobId = request.getParameter("jobId");
		String basicpay = request.getParameter("basicpay");
		String extrapay = request.getParameter("extrapay");

		int result = 0;
		String txt = "fail";
		if (name_ != null) {
			result = this.emloyeeDAO.add(new Employee(name_, ssn, phone, java.sql.Date.valueOf(hiredate), regId, deptId, jobId,
					Integer.parseInt(basicpay), Integer.parseInt(extrapay)));
			if (result == 1) {
				txt = "success";
			}
		}

		// ����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/employee/list?result=%s", request.getContextPath(), txt);
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String employeeUpdateForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ������ȣ�� ������ �� Ư�� ������ �������� Ȯ���ϴ� ���� �߰�

		// request.setCharacterEncoding("UTF-8");
		// ->���ڵ� ���ͷ� ��ü

		String empId = request.getParameter("empId");

		/*
		 * Employee emp = new Employee("EMP001", "ȫ�浿" , "901010-1122345",
		 * "010-1111-2222" , Date.valueOf("2010-11-10") , "REG02", "DEPT01", "JOB01" ,
		 * 1000000, 1000000);
		 */
		Employee emp = this.emloyeeDAO.employeeInfo(empId);
		request.setAttribute("emp", emp);

		// ����, �μ�, ���� ������ �о���� ���� �߰�
		List<Region> regionList = this.regionDAO.list2();
		List<Department> departmentList = this.departmentDAO.list2();
		List<Job> positionList = this.jobDAO.list2();
		request.setAttribute("regionList", regionList);
		request.setAttribute("departmentList", departmentList);
		request.setAttribute("positionList", positionList);

		// ����) SELECT �׼� ó�� �Ŀ���
		// ������ �׼��� ���� ���� ��ȯ
		// ����) ����η� ����
		return "/WEB-INF/views/employee/employeeupdateform.jsp";
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String employeeUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ������ �ʿ��� �ڷ� ������ �� �����ͺ��̽����� ���� �׼� ����

		// request.setCharacterEncoding("UTF-8");
		// ->���ڵ� ���ͷ� ��ü

		String empId = request.getParameter("empId");
		String name_ = request.getParameter("name_");
		String ssn = request.getParameter("ssn");
		String phone = request.getParameter("phone");
		String hiredate = request.getParameter("hiredate");
		String regId = request.getParameter("regId");
		String deptId = request.getParameter("deptId");
		String jobId = request.getParameter("jobId");
		String basicpay = request.getParameter("basicpay");
		String extrapay = request.getParameter("extrapay");

		int result = 0;
		String txt = "fail";
		if (empId != null) {
			result = this.emloyeeDAO.modify(new Employee(empId, name_, ssn, phone, java.sql.Date.valueOf(hiredate), regId,
					deptId, jobId, Integer.parseInt(basicpay), Integer.parseInt(extrapay)));
			if (result == 1) {
				txt = "success";
			}
		}

		// ����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/employee/list?result=%s", request.getContextPath(), txt);

	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String employeeDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request.setCharacterEncoding("UTF-8");
		// ->���ڵ� ���ͷ� ��ü

		String empId = request.getParameter("empId");

		int result = 0;
		String txt = "fail";
		if (empId != null) {
			// ����) ���� ���� ������ ���� ������ ���� �����ȴ�.
			// ->ON DELETE CASCADE �ɼ� ����
			result = this.emloyeeDAO.remove(empId);
			if (result == 1) {
				txt = "success";
			}
		}

		// ����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/employee/list?result=%s", request.getContextPath(), txt);
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String getMinBasicpay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ������ȣ�� ���Źް�,
		// Ư�� ������ �ּұ⺻�� Ȯ��
		// �ּұ⺻���� Ajax �������� ó��

		String jobId = request.getParameter("jobId");
		Job result = this.jobDAO.list3(jobId);
		int minbasicpay = result.getMin_basicPay();
		String json = "{\"minbasicpay\":" + minbasicpay + "}";
		System.out.println(json);
		request.setAttribute("minbasicpay", json);

		// ����) getminbasicpay.jsp ���������� ���信 �ʿ��� ��� �ܿ��� �ƹ��͵� �ۼ����� �ʾƾ� �Ѵ�.
		return "/WEB-INF/views/employee/getminbasicpay.jsp";
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String regionList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Region> list = this.regionDAO.list2();
		request.setAttribute("list", list);


		// ����) SELECT �׼� ó�� �Ŀ���
		// ������ �׼��� ���� ���� ��ȯ
		// ����) ����η� ����
		return "/WEB-INF/views/region/regionlist.jsp";
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String regionInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request.setCharacterEncoding("UTF-8");
		// ->���ڵ� ���ͷ� ��ü

		String reg_name = request.getParameter("reg_name");

		int result = 0;
		String txt = "fail";
		if (reg_name != null) {
			result = this.regionDAO.add(new Region(reg_name));
			if (result == 1) {
				txt = "success";
			}
		}

		// ����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/region/list?result=%s", request.getContextPath(), txt);
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String regionDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request.setCharacterEncoding("UTF-8");
		// ->���ڵ� ���ͷ� ��ü

		String regId = request.getParameter("regId");

		int result = 0;
		String txt = "fail";
		if (regId != null) {
			result = this.regionDAO.remove(new Region(regId, null));
			if (result == 1) {
				txt = "success";
			}
		}

		// ����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/region/list?result=%s", request.getContextPath(), txt);
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String regionUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request.setCharacterEncoding("UTF-8");
		// ->���ڵ� ���ͷ� ��ü

		String regId = request.getParameter("regId");
		String reg_name = request.getParameter("reg_name");

		int result = 0;
		String txt = "fail";
		if (regId != null) {
			result = this.regionDAO.modify(new Region(regId, reg_name));
			if (result == 1) {
				txt = "success";
			}
		}

		// ����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/region/list?result=%s", request.getContextPath(), txt);
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String departmentList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Department> list = this.departmentDAO.list2();
		request.setAttribute("list", list);

		// ����) SELECT �׼� ó�� �Ŀ���
		// ������ �׼��� ���� ���� ��ȯ
		// ����) ����η� ����
		return "/WEB-INF/views/department/departmentlist.jsp";
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String departmentInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request.setCharacterEncoding("UTF-8");
		// ->���ڵ� ���ͷ� ��ü

		String dept_name = request.getParameter("dept_name");

		int result = 0;
		String txt = "fail";
		if (dept_name != null) {
			result = this.departmentDAO.add(new Department(dept_name));
			if (result == 1) {
				txt = "success";
			}
		}

		// ����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/department/list?result=%s", request.getContextPath(), txt);
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String departmentDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request.setCharacterEncoding("UTF-8");
		// ->���ڵ� ���ͷ� ��ü

		String deptId = request.getParameter("deptId");

		int result = 0;
		String txt = "fail";
		if (deptId != null) {
			result = this.departmentDAO.remove(new Department(deptId, null));
			if (result == 1) {
				txt = "success";
			}
		}

		// ����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/department/list?result=%s", request.getContextPath(), txt);
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String departmentUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request.setCharacterEncoding("UTF-8");
		// ->���ڵ� ���ͷ� ��ü

		String deptId = request.getParameter("deptId");
		String dept_name = request.getParameter("dept_name");

		int result = 0;
		String txt = "fail";
		if (deptId != null) {
			result = this.departmentDAO.modify(new Department(deptId, dept_name));
			if (result == 1) {
				txt = "success";
			}
		}

		// ����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/department/list?result=%s", request.getContextPath(), txt);
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String positionList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Job> list = this.jobDAO.list2();
		request.setAttribute("list", list);

		// ����) SELECT �׼� ó�� �Ŀ���
		// ������ �׼��� ���� ���� ��ȯ
		// ����) ����η� ����
		return "/WEB-INF/views/position/positionlist.jsp";
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String positionInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request.setCharacterEncoding("UTF-8");
		// ->���ڵ� ���ͷ� ��ü

		String job_title = request.getParameter("job_title");
		String minBasicPay = request.getParameter("minBasicPay");

		int result = 0;
		String txt = "fail";
		if (job_title != null) {
			result = this.jobDAO.add(new Job(job_title, Integer.parseInt(minBasicPay)));
			if (result == 1) {
				txt = "success";
			}
		}

		// ����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/position/list?result=%s", request.getContextPath(), txt);
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String positionDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request.setCharacterEncoding("UTF-8");
		// ->���ڵ� ���ͷ� ��ü

		String jobId = request.getParameter("jobId");

		int result = 0;
		String txt = "fail";
		if (jobId != null) {
			result = this.jobDAO.remove(new Job(jobId, null, 0));
			if (result == 1) {
				txt = "success";
			}
		}

		// ����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/position/list?result=%s", request.getContextPath(), txt);
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String positionUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// request.setCharacterEncoding("UTF-8");
		// ->���ڵ� ���ͷ� ��ü

		String jobId = request.getParameter("jobId");
		String job_title = request.getParameter("job_title");
		String minBasicPay = request.getParameter("minBasicPay");

		int result = 0;
		String txt = "fail";
		if (job_title != null) {
			result = this.jobDAO.modify(new Job(jobId, job_title, Integer.parseInt(minBasicPay)));
			if (result == 1) {
				txt = "success";
			}
		}

		// ����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/position/list?result=%s", request.getContextPath(), txt);
	}

	// employee.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String fileUpload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ������ Ȯ��
		// String path = request.getContextPath();

		// --------------------------
		// ���� ���ε� �׼� ó��

		// request.setCharacterEncoding("UTF-8");
		// ->���ڵ� ���ͷ� ��ü

		PictureDAO dao = new PictureDAO();
		String txt = "fail";

		File file;

		// ���� ���ε� ���� �뷮(byte����)
		// 1024byte -> 1K byte
		// 1024K byte -> 1M byte
		// 1024M byte -> 1G byte
		int maxFileSize = 5 * 1024 * 1024; // 5M byte
		int maxMemSize = 5 * 1024 * 1024;
		/*
		 * ServletContext context = pageContext.getServletContext(); String filePath =
		 * context.getInitParameter("file-upload");
		 */
		// ������ ������ ���� ��� Ȯ��
		String filePath = request.getServletContext().getRealPath("resources//pictures");

		// Verify the content type
		String contentType = request.getContentType();

		if ((contentType.indexOf("multipart/form-data") >= 0)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// maximum size that will be stored in memory
			factory.setSizeThreshold(maxMemSize);

			// Location to save data that is larger than maxMemSize.
			factory.setRepository(new File("c:\\temp"));

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// maximum file size to be uploaded.
			upload.setSizeMax(maxFileSize);

			try {
				// Parse the request to get file items.
				List<FileItem> fileItems = upload.parseRequest(request);

				// Process the uploaded file items
				Iterator<FileItem> i = fileItems.iterator();

				String newFileName = "";
				String empId = "";
				String content = "";
				String pictureCount = "";

				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();

					if (fi.isFormField()) {
						String fieldName = fi.getFieldName();
						if (fieldName.equals("empId")) {
							empId = fi.getString("UTF-8");
						} else if (fieldName.equals("content")) {
							content = fi.getString("UTF-8");
						} else if (fieldName.equals("pictureCount")) {
							pictureCount = fi.getString("UTF-8");
						}
					} else {
						// Get the uploaded file parameters
						// String fieldName = fi.getFieldName();

						String fileName = fi.getName();
						System.out.println("original FileName:" + fileName + "<br>");

						// boolean isInMemory = fi.isInMemory();
						// long sizeInBytes = fi.getSize();

						String type = fi.getContentType();
						System.out.println("ContentType:" + type);

						// Write the file
						/*
						 * if (fileName.lastIndexOf("\\") >= 0) { file = new File(filePath +
						 * "\\" + fileName.substring(fileName.lastIndexOf("\\"))); } else { file = new
						 * File(filePath + "\\" + fileName.substring(fileName.lastIndexOf("\\") + 1)); }
						 */
						String ext = fileName.substring(fileName.lastIndexOf("."));
						newFileName = Util.randomFileName() + ext;

						file = new File(filePath + "\\" + newFileName);
						System.out.println("new FilePath + FileName:" + (filePath + "\\" + newFileName));

						// ����) ������ Ư�� ����ҿ� ������ ������ ����ȴ�.
						// ����) ������ �����̸��� ���� ������ ��� ����Ⱑ �ȴ�.
						// -> ����ũ ���� �̸� ����
						// -> Ȯ���ڴ� �״�� �����ؾ� �Ѵ�.
						// -> "���������������̸�.Ȯ����"
						fi.write(file);

					}

				}

				// �����ͺ��̽��� ���� ���� ���� ���� �׼� ����
				// ->���������̸�(original, new), �������ϻ�����(byte ����), ��������, �����
				int result = 0;

				if (pictureCount.equals("0")) {
					// ���� ���
					result = dao.pictureAdd(new Picture(newFileName, content, empId));
				} else {
					// ���� ����
					result = dao.pictureModify(new Picture(newFileName, content, empId));
				}
				if (result == 1) {
					txt = "success";
				}

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		// --------------------------

		// ����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/employee/list?result=%s", request.getContextPath(), txt);
	}

	public String pwupdateForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ������ �׼��� ���� ���� ��ȯ
		// ����) ����η� ����
		return "/WEB-INF/views/admin/adminpw.jsp";
	}

	public String pwupdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id_");
		String oldpw = request.getParameter("oldpw");
		String newpw = request.getParameter("newpw");
	
		int result = 0;
		String txt = "fail";
		if (newpw != null && oldpw != null) {
			result = this.loginDAO.modify(new Login(id, oldpw, newpw));
			if (result == 1) {
				txt = "success";
			}
		}
		

		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/admin/pwupdateForm?result=%s", request.getContextPath(), txt);
	}
}