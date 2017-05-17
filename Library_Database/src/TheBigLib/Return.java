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

public class Return extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out=resp.getWriter();
		
		HttpSession session=req.getSession(false);
		if(session!=null){
		String uname=(String)session.getAttribute("uname");
		String bid= req.getParameter("bid");
		try{  
			//step1 load the driver class  
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//step2 create  the connection object  
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");  
			con.setAutoCommit(true);
			//step3 create the statement object  
			Statement stmt=con.createStatement();  
			String query1 = "delete from has where uname='"+uname+"' and bid='"+bid+"'";
			String query2 = "insert into return values('"+uname+"','"+bid+"',current_timestamp)";
			//step4 execute query  
			stmt.executeUpdate(query1);
			stmt.executeUpdate(query2);
			//step5 close the connection object  
			stmt.close();
			con.close();  
			resp.sendRedirect("http://localhost:4785/Library_Database/profile1");
			}catch(Exception e){ resp.getWriter().println("Cant connect to the database.");}  
	
		}
		else
		{
			req.getRequestDispatcher("login.html").include(req, resp);
		}
		
	}
}
