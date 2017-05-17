package TheBigLib;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import oracle.jdbc.driver.OracleDriver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Profile extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out=resp.getWriter();
		
		HttpSession session=req.getSession(false);
		if(session!=null){
		String uname=(String)session.getAttribute("uname");
		req.getRequestDispatcher("profile.html").include(req, resp);
		}
		else
		{
			req.getRequestDispatcher("login.html").include(req, resp);
		}
		
		try{  
			//step1 load the driver class  
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//step2 create  the connection object  
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");  
			con.setAutoCommit(true);
			//step3 create the statement object  
			Statement stmt=con.createStatement();  
			
			//step4 execute query  
			
			//step5 close the connection object  
			stmt.close();
			con.close();  
			  
			}catch(Exception e){ resp.getWriter().println("Cant connect to the database.");}  
	
	}
}
