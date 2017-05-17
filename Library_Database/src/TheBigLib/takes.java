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

public class takes extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out=resp.getWriter();
		
		HttpSession session=req.getSession(false);
		if(session!=null){
		String uname=(String)session.getAttribute("uname");
		String bid = req.getParameter("bid");
		try{  
			//step1 load the driver class  
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//step2 create  the connection object  
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");  
			con.setAutoCommit(true);
			resp.getWriter().println("Hello");
			//step3 create the statement object  
			Statement stmt=con.createStatement();  
			resp.getWriter().println("Hello");
			ResultSet rs = stmt.executeQuery("select stock from books where bid='"+bid+"'");
			int count = -1;
			while(rs.next())
			{
				count = rs.getInt(1);
			}
			if(count>0)
			{
			String query2 = "insert into has values('"+uname+"','"+bid+"')";
			stmt.executeUpdate(query2);
			resp.getWriter().println("Hello"+count);
			String query1 = "insert into withdraw values('"+uname+"','"+bid+"',current_timestamp)";
			stmt.executeUpdate(query1);
			
			//step4 execute query  
			
			//step5 close the connection object  
			
			stmt.close();
			con.close();  
			resp.sendRedirect("http://localhost:4785/Library_Database/profile1");  
			}
			else
		{
			req.getRequestDispatcher("outofstock.html").include(req, resp);
		}
		}
		catch(Exception e){ resp.getWriter().println(e);}  
	
		}
		else
		{
			req.getRequestDispatcher("login.html").include(req, resp);
		}
	}
}
