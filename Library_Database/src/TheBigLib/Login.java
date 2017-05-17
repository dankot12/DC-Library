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

public class Login extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().println("Please be patient");
		int count = -1;
		try{  
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//step2 create  the connection object  
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");  
			con.setAutoCommit(true);
			//step3 create the statement object  
			Statement stmt=con.createStatement();  
			//step4 execute query  
			String uname=req.getParameter("uname");
			String pass=req.getParameter("password");
			
			resp.getWriter().println("Hello"+uname+" "+pass);
			String query = "Select count(*) from usesDC where uname = '"+uname+"' and password = '"+generateHash(pass)+"'";
			resp.getWriter().println("Hello");
			ResultSet rs = stmt.executeQuery(query);
			resp.getWriter().println("Hello");
			while(rs.next())
			{
				count = rs.getInt(1);
			}
			if(count == 1)
			{
				HttpSession session=req.getSession();
				session.setAttribute("uname",uname);
				resp.sendRedirect("http://localhost:4785/Library_Database/profile");
			}
			else
				resp.sendRedirect("http://localhost:4785/Library_Database/messagelogin");
			 
			//step5 close the connection object  
			stmt.close();
			con.close();  
			  
			}catch(Exception e){ resp.getWriter().println(e);}  
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

}