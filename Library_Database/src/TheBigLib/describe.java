package TheBigLib;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import oracle.jdbc.driver.OracleDriver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class describe extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().println("Please be patient");
		HttpSession session=req.getSession(false);
		if(session!=null){
		String uname=(String)session.getAttribute("uname");
		
		try{  
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//step2 create  the connection object  
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");  
			con.setAutoCommit(true);
			//step3 create the statement object  
			Statement stmt=con.createStatement();  
			//step4 execute query  
			String bid=req.getParameter("bid");
			String review = req.getParameter("message");
			int count = -1;
			String query = "select count(*) from withdraw where uname='"+uname+"' and bid='"+bid+"'";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				count = rs.getInt(1);
			}
			if(count>0)
			{
				String query1 = "insert into description values('"+uname+"','"+bid+"','"+review+"')";
				stmt.executeUpdate(query1);
				resp.sendRedirect("http://localhost:4785/Library_Database/profile1");
			}
			else
			{
				resp.getWriter().println(count);
				resp.getWriter().println(uname);
				resp.getWriter().println(bid);
				resp.getWriter().println(review);
				//req.getRequestDispatcher("messagedescribe.html").include(req, resp);
			}
			}catch(Exception e){ resp.getWriter().println(e);}  
	}
		else
		{
			req.getRequestDispatcher("messagelogin.html").include(req, resp);
		}
	}
	
}