package TheBigLib;

import java.io.IOException;
import java.sql.*;
import oracle.jdbc.driver.OracleDriver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Connector extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().println("Please be patient");
		int count = 0;
		try{
		//step1 load the driver class  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		  
		//step2 create  the connection object  
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");  
		con.setAutoCommit(true);
		//step3 create the statement object  
		Statement stmt=con.createStatement();  
		//step4 execute query  
		
		String name=req.getParameter("name");
		
		String uname=req.getParameter("uname");
		
		String passw = req.getParameter("passw");
		String hashpassw = generateHash(passw);
		
		String email=req.getParameter("email");
		
		String phonestr=req.getParameter("phone");
		if(uname.contains(" "))
		{
			resp.sendRedirect("http://localhost:4785/Library_Database/messageusername");
		}
		if(!isNumeric(phonestr))
		{
			resp.sendRedirect("http://localhost:4785/Library_Database/messagephone");
		}
		if(phonestr.length()<8)
		{
			resp.sendRedirect("http://localhost:4785/Library_Database/messagephone");
		}
		
		String agestr = req.getParameter("age");
		
		if(!isNumeric(agestr))
		{
			resp.sendRedirect("http://localhost:4785/Library_Database/messageage");
		}
		int age = Integer.parseInt(agestr);
		if(age>120)
		{
			resp.sendRedirect("http://localhost:4785/Library_Database/messageage");
		}
		String address = req.getParameter("address");
		
		long phone = Long.parseLong(phonestr);
		resp.getWriter().println("Please be patient"+uname);
		ResultSet rs = stmt.executeQuery("select count(*) from personX where uname ='"+uname+"'");
		while(rs.next())
		{
		if(rs.getInt(1)==1)
		{
		count = rs.getInt(1);
		resp.sendRedirect("http://localhost:4785/Library_Database/messageregister");
		}
		}
		resp.getWriter().println("Please be patient"+count);
		if(count==0)
		{
		String query1 = "insert into personX values('"+uname+"','"+name+"',"+phone+",'"+email+"',"+age+",'"+address+"')";
		String query2 = "insert into UsesDC values('"+uname+"','"+hashpassw+"')";
		stmt.executeUpdate(query1); 
		stmt.executeUpdate(query2); 
		
		resp.getWriter().println("Please be patient");
		HttpSession session=req.getSession();
		session.setAttribute("uname",uname);
		resp.sendRedirect("http://localhost:4785/Library_Database/profile");
		}
		resp.getWriter().println("Please be patient");
		//step5 close the connection object  
		stmt.close();
		con.close();  
		  
		}catch(Exception e){ resp.getWriter().println("Cant connect to the database. " + e );}
		
	}
	public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}
