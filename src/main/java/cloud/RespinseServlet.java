package cloud;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Response")
public class RespinseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html; charset=UTF-8"); //MIME 타입설정//charset 언어인코딩
										//언어를 UTF-8로 응답
		PrintWriter out = resp.getWriter(); //응답
		
		out.print("<html><body>");
		out.print("<h3>ResponseServlet 요청</h3>");
		out.print("<body><html>");
	} 
	
	
}
