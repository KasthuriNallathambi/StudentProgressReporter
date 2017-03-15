package org.rec.studentdetails.report;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rec.studentdetails.pojo.CommonDetails;
import org.rec.studentdetails.pojo.Student;
import org.rec.studentdetails.sender.MailSender;
import org.rec.studentdetails.sender.SMSSender;

/**
 * Servlet implementation class ReportSender
 */
@WebServlet("/MarksheetReportSender")
public class MarksheetReportSender extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MarksheetReportSender() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<Student> students = (List<Student>) request.getSession().getAttribute("marksheet");

		CommonDetails commonDetails = new CommonDetails();
		
		if (request.getParameter("department")!=null && !request.getParameter("department").isEmpty() && request.getParameter("department").length()>0 ) {
			commonDetails.department= request.getParameter("department").trim();
		}
		if (request.getParameter("year")!=null && !request.getParameter("year").isEmpty() && request.getParameter("year").length()>0 ) {
			commonDetails.year=request.getParameter("year").trim();
		}
		if (request.getParameter("semester")!=null && !request.getParameter("semester").isEmpty() && request.getParameter("semester").length()>0 ) {
			commonDetails.semester=request.getParameter("semester").trim();
		}
		if (request.getParameter("section")!=null && !request.getParameter("section").isEmpty() && request.getParameter("section").length()>0 ) {
			commonDetails.section=request.getParameter("section").trim();
		}
		if (request.getParameter("subject")!=null && !request.getParameter("subject").isEmpty() && request.getParameter("subject").length()>0 ) {
			commonDetails.subject=request.getParameter("subject").trim();
		}
		if (request.getParameter("faculty")!=null && !request.getParameter("faculty").isEmpty() && request.getParameter("faculty").length()>0 ) {
			commonDetails.faculty=request.getParameter("faculty").trim();
		}
		
		if ("Mail".equals(request.getParameter("Mail"))) {
			new MailSender().sendMails(commonDetails, students, true);
		}

		if ("SMS".equals(request.getParameter("SMS"))) {
			new SMSSender().sendMessage(commonDetails, students, true);
		}

	//	if ("HOD".equals(request.getParameter("HOD"))) {
			new MailSender().sendMail(commonDetails, students,org.rec.studentdetails.Utils.getvalue("hod_mail_id"), true);
	//	}
		
		if (request.getParameter("facultyMail")!=null && !request.getParameter("facultyMail").isEmpty() && request.getParameter("facultyMail").length()>0 ) {
			new MailSender().sendMail(commonDetails, students,request.getParameter("facultyMail"), true);
		}
		
		request.setAttribute("message", "Marksheet");
		getServletContext().getRequestDispatcher("/Thankyou.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}