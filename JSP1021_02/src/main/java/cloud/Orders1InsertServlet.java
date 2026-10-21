package cloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import cloud.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orders1Insert")
public class Orders1InsertServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		
		// html에서 문자열로 받으니까
		String order_num = req.getParameter("order_num");
		String order_date = req.getParameter("order_date");
		String cust_id = req.getParameter("cust_id");
		
		
		resp.setContentType("text/html; charset=UTF-8");
		//resp 서버가 브라우저에 보낼 응답 (문자를 한ㅁ글로, 그리고 형식은 html형식으로 할것이다.)
		PrintWriter out = resp.getWriter();
		//응답 출력도구resp.getWriter();를 가져와서 out으로 브라우저에 보낼거임
		
		out.print("<html><body>");
			Connection conn = null;
			PreparedStatement pstmt =null;
			
			String query = "INSERT INTO orders1 (order_num,order_date,cust_id) VALUES (?,?,?)";
			
			try {
				conn = DBUtil.getConnection();
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, Integer.parseInt(order_num));
				pstmt.setString(2, order_date);
				pstmt.setString(3, cust_id);
				
				
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
				DBUtil.close(pstmt, conn);
				}
			
			
			out.print("</body></html>");
			}
			
		
		
		
	}

	
	
	

