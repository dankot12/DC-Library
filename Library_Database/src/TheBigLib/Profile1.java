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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Profile1 extends HttpServlet{
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out=resp.getWriter();
		//req.getRequestDispatcher("navbar.html").include(req, resp);
		//req.getRequestDispatcher("down.html").include(req, resp);
		//req.getRequestDispatcher("search.html").include(req, resp);
		
		
		HttpSession session=req.getSession(false);
		if(session!=null){
		String uname=(String)session.getAttribute("uname");
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.println("<meta name=\"generator\" content=\"Mobirise v3.12, mobirise.com\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.println("<link rel=\"shortcut icon\" href=\"assets/images/mit-manipal-seal-128x114.png\" type=\"image/x-icon\">");
		out.println("<meta name=\"description\" content=\"Site Maker Description\">");
		out.println("<title>DC's Library</title>");
		out.println("<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Roboto:700,400&amp;subset=cyrillic,latin,greek,vietnamese\">");
		out.println("<link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap.min.css\">");
		out.println("<link rel=\"stylesheet\" href=\"assets/mobirise/css/style.css\">");
		out.println("<link rel=\"stylesheet\" href=\"assets/dropdown-menu/style.css\">");
		out.println("<link rel=\"stylesheet\" href=\"assets/mobirise/css/mbr-additional.css\" type=\"text/css\">");
		out.println("<link rel=\"stylesheet\" href=\"table.css\" type=\"text/css\">");
		out.println("</head>");
		out.println("<body>");
		//req.getRequestDispatcher("navbar.html").include(req, resp);
		out.println("<section id=\"dropdown-menu-5\">");
		out.println("<nav class=\"navbar navbar-dropdown navbar-fixed-top\">");
		out.println("<div class=\"container\">");
		out.println("<div class=\"navbar-brand\">");
		out.println("<a href=\"index.html#slider-7\" class=\"navbar-logo\"><img src=\"assets/images/mit-manipal-seal-143x128.png\" alt=\"Mobirise\"></a>");
		out.println("<a class=\"text-gray\" href=\"index.html#slider-7\">DC's Library</a>");
		out.println("</div>");
		out.println("<button class=\"navbar-toggler pull-xs-right hidden-md-up\" type=\"button\" data-toggle=\"collapse\" data-target=\"#exCollapsingNavbar\">");
		out.println("<div class=\"hamburger-icon\"></div>");
		out.println("</button>");
		out.println("<ul class=\"nav-dropdown collapse pull-xs-right navbar-toggleable-sm nav navbar-nav\" id=\"exCollapsingNavbar\"><li class=\"nav-item\"><a class=\"nav-link link\" href=\"index.html#slider-7\">Overview</a></li><li class=\"nav-item dropdown open\"><a class=\"nav-link link dropdown-toggle\" data-toggle=\"dropdown-submenu\" href=\"login.html\" aria-expanded=\"true\">Login/Join</a><div class=\"dropdown-menu\"><a class=\"dropdown-item\" href=\"login.html\">Login</a><a class=\"dropdown-item\" href=\"register.html\">Register</a></div></li><li class=\"nav-item\"><a class=\"nav-link link\" href=\"profile.html\">Search</a></li><li class=\"nav-item\"><a class=\"nav-link link\" href=\"http://localhost:4785/Library_Database/profile1\">Profile</a></li><li class=\"nav-item dropdown open\"><a class=\"nav-link link dropdown-toggle\" href=\"index.html\" aria-expanded=\"true\" data-toggle=\"dropdown-submenu\">Help</a><div class=\"dropdown-menu\"><a class=\"dropdown-item\" href=\"index.html#social-buttons2-f\">Contact us</a><a class=\"dropdown-item\" href=\"index.html#form1-d\">Ask For Books</a><a class=\"dropdown-item\" href=\"index.html#contacts2-g\">Address</a></div></li></ul>");
		out.println("</div>");
		out.println("</nav>");
		out.println("</section>");
		
		out.println("<section class=\"engine\"><a rel=\"external\" href=\"https://mobirise.com\">Mobirise</a></section><section class=\"mbr-section mbr-section--relative mbr-section--fixed-size mbr-after-navbar\" id=\"form1-u\" style=\"background-color: rgb(239, 239, 239);\">");
		out.println("<div class=\"mbr-section__container mbr-section__container--std-padding container\" style=\"padding-top: 93px; padding-bottom: 93px;\">");
		out.println("<div class=\"row\">");
		out.println("<div class=\"col-sm-12\">");
		out.println("<div class=\"row\">");
		out.println("<div class=\"col-sm-8 col-sm-offset-2\">");
		out.println("<div class=\"mbr-header mbr-header--center mbr-header--std-padding\">");
		out.println("<h2 class=\"mbr-header__text\">PROFILE: Books</h2>");
		out.println("</div>");
		
		
		try{  
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//step2 create  the connection object  
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");  
			con.setAutoCommit(true);
			//step3 create the statement object  
			Statement stmt=con.createStatement();  
			//step4 execute query  
			String query1 = "select count(*) from has where uname='"+uname+"'";
			ResultSet rs1 = stmt.executeQuery(query1);
			int count = -1;
			while(rs1.next())
			{
				count = rs1.getInt(1);
			}
			if(count>0)
			{
			String query = "select BID,title,topic,LINK_ISBN from has natural join books where Uname='"+uname+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			out.println("<table><thead><tr><th>BID</th><th>Title</th><th>Topic</th><th>ISBN</th><th>Return</th></tr></thead>");
			out.println("<tbody>");
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>"+rs.getString(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getString(4)+"</td>");
				out.println("<td><a href=\"http://localhost:4785/Library_Database/return?bid="+rs.getString(1)+"\">Return</a></td>");
				out.println("</tr>");
			}
			out.println("</tbody></table>");
			}
			else
			{
				out.println("<h4 style = \"text-align:center; color:green\"><strong>You do not have any book currently. Thank you, We would like to serve you again.</strong></h4>");
				
			}
			//step5 close the connection object  
			stmt.close();
			con.close();  
			  
			}catch(Exception e){ resp.getWriter().println(e);}
		
		
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</section>");
		out.println("<section class=\"engine\"><a rel=\"external\" href=\"https://mobirise.com\">Mobirise</a></section><section class=\"mbr-section mbr-section--relative mbr-section--fixed-size mbr-after-navbar\" id=\"form1-u\" style=\"background-color: rgb(239, 239, 239);\">");
		out.println("<div class=\"mbr-section__container mbr-section__container--std-padding container\" style=\"padding-top: 93px; padding-bottom: 93px;\">");
		out.println("<div class=\"row\">");
		out.println("<div class=\"col-sm-12\">");
		out.println("<div class=\"row\">");
		out.println("<div class=\"col-sm-8 col-sm-offset-2\">");
		out.println("<div class=\"mbr-header mbr-header--center mbr-header--std-padding\">");
		out.println("<h2 class=\"mbr-header__text\">Write a Description.</h2>");
		out.println("</div>");
		out.println("<div data-form-alert=\"true\">");
		out.println("<div class=\"hide\" data-form-alert-success=\"true\">Thanks for filling out form!</div>");
		out.println("</div>");
		out.println("<form action=\"describe\" method=\"post\">");
		out.println("<div class=\"form-group\">");
		out.println("<input type=\"text\" class=\"form-control\" name=\"bid\" required=\"\" placeholder=\"Book ID*\">");
		out.println("</div>");
		out.println("<div class=\"form-group\">");
		out.println("<textarea class=\"form-control\" name=\"message\" rows=\"7\" placeholder=\"Write a description about the book. About what you felt.\" data-form-field=\"Message\"></textarea>");
		out.println();
		out.println("</div>");
		out.println("<div class=\"mbr-buttons mbr-buttons--right\"><button type=\"submit\" class=\"mbr-buttons__btn btn btn-lg btn-danger\">SUBMIT</button></div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</section>");
		out.println("<section class=\"mbr-section mbr-section--relative mbr-section--fixed-size\" id=\"contacts2-t\" style=\"background-color: rgb(60, 60, 60);\">");
		out.println("<div class=\"mbr-section__container container\">");
		out.println("<div class=\"mbr-contacts mbr-contacts--wysiwyg row\" style=\"padding-top: 45px; padding-bottom: 45px;\">");
		out.println("<div class=\"col-sm-6\">");
		out.println("<figure class=\"mbr-figure mbr-figure--wysiwyg mbr-figure--full-width mbr-figure--no-bg\">");
		out.println("<div class=\"mbr-figure__map mbr-figure__map--short\"><iframe frameborder=\"0\" style=\"border:0\" src=\"https://www.google.com/maps/embed/v1/place?key=AIzaSyA0Dx_boXQiwvdz8sJHoYeZNVTdoWONYkU&amp;q=place_id:ChIJt-3E0qekvDsRH4YduE-NWI0\" allowfullscreen=\"\"></iframe></div>");
		out.println("</figure>");
		out.println("</div>");
		out.println("<div class=\"col-sm-6\">");
		out.println("<div class=\"row\">");
		out.println("<div class=\"col-sm-5 col-sm-offset-1\">");
		out.println("<p class="+"mbr-contacts__text"+"><strong>ADDRESS</strong><br>MIT Manipal, Udupi-576102,<br>Karnataka, India.<br><br>");
		out.println("<strong>CONTACTS</strong><br>");
		out.println("Email: support@DClibrary.com<br>");
		out.println("Phone: +571 8347 9348<br>");
		out.println("Fax: +48 3489 3498</p>");
		out.println("</div>");
		out.println("<div class="+"col-sm-6"+"><p><strong>Creators</strong></p><p><strong>Dhanraj Kotian</strong></p><p><strong>Craig Lewis</strong></p></div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</section>");
		out.println("<script src=\"assets/web/assets/jquery/jquery.min.js\"></script>");
		out.println("<script src=\"assets/bootstrap/js/bootstrap.min.js\"></script>");
		out.println("<script src=\"assets/smooth-scroll/smooth-scroll.js\"></script>");
		out.println("<script src=\"assets/cookies-alert-plugin/cookies-alert-core.js\"></script>");
		out.println("<script src=\"assets/cookies-alert-plugin/cookies-alert-script.js\"></script>");
		out.println(" <!--[if lte IE 9]>");
		out.println("<script src=\"assets/jquery-placeholder/jquery.placeholder.min.js\"></script>");
		out.println("<![endif]-->");
		out.println("<script src=\"assets/mobirise/js/script.js\"></script>");
		out.println("<script src=\"assets/dropdown-menu/script.js\"></script>");
		out.println("<script src=\"assets/formoid/formoid.min.js\"></script>");
		out.println("<input name=\"cookieData\" type=\"hidden\" data-cookie-text=\"We use cookies to give you the best experience. Read our <a href='privacy.html'>cookie policy</a>.\">");
		out.println("</body>");
		out.println("</html>");
		}
		else{
			req.getRequestDispatcher("booklogin.html").include(req, resp);
		}
		
		out.close();
	}
}