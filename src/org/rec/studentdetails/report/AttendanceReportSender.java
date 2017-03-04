package org.rec.studentdetails.report;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rec.studentdetails.pojo.Student;
import org.rec.studentdetails.sender.MailSender;
import org.rec.studentdetails.sender.SMSSender;

/**
 * Servlet implementation class AttendanceReportSender
 */
@WebServlet("/AttendanceReportSender")
public class AttendanceReportSender extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendanceReportSender() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		@SuppressWarnings("unchecked")
		List<Student> students = (List<Student>) request.getSession().getAttribute("attendance");
		if("Mail".equals(request.getParameter("Mail"))){			
			new MailSender().sendMails(students, false);
		}
		
		if("SMS".equals(request.getParameter("SMS"))){			
			new SMSSender().sendMessage(students,false);
		}
	//	if ("HOD".equals(request.getParameter("HOD"))) {
			new MailSender().sendMail(students,org.rec.studentdetails.Utils.getvalue("hod_mail_id"), false);
	//	}
		
		if (request.getParameter("faculty")!=null && !request.getParameter("faculty").isEmpty() && request.getParameter("faculty").length()>0 ) {
			new MailSender().sendMail(students,request.getParameter("faculty"), false);
		}
		
		request.setAttribute("message", "Attendance");
		getServletContext().getRequestDispatcher("/Thankyou.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
