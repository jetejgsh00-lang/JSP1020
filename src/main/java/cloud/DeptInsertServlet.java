package cloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deptInsert") //url
public class DeptInsertServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//입력폼
		
		req.setCharacterEncoding("UTF-8"); //페이지를 요청
		
		
		//웹에서 입력받은 값을 저장함 ->  DB로 넘겨줄려고 
		int deptno = Integer.parseInt(req.getParameter("deptno")); //Integer.parseInt 문자를 숫자로
		String dname = req.getParameter("dname");
		String loc = req.getParameter("loc");    //웹에서의 요청임
		//웹에서 입력받은 모든값은 string
		//웹에서 전송하는 보든 값을 파라미터 라고함
		
		resp.setContentType("text/html; charset=UTF-8");//언어 타입이 맞지않으면 다깨짐
		PrintWriter out = resp.getWriter();
		out.print("<html><body>");
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/cloud1";
		String username = "root";
		String password = System.getenv("JSP1020_DB_PASSWORD");
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			
			String query = "INSERT INTO dept (deptno, dname, loc) VALUES(?, ?, ?)";
			
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, username, password);
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, deptno); //위에 값을 정의한거 변수가져옴
				pstmt.setString(2, dname);
				pstmt.setString(3, loc);
				
				int result = pstmt.executeUpdate();
				
				if (result == 1) {
					out.print("insert()실행성공");
				} else {
					out.print("insert()실행실패");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				try {
					if (pstmt!=null)pstmt.close();
					if (conn!=null)conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
			
			
		
		out.print("<body><html>");
	}
	

}
