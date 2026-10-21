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

@WebServlet("/empDelete")
public class EmpDeleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub


		//req는 브라우저에서 받은요청 
		// resp는 브라우저에 보낼 응답을 다루는 객체
		//PreparedStatement는 SQL문을 준비, 실행할때 사용하는 인터페이스
		
		
	resp.setContentType("text/html; charset=UTF-8");
	PrintWriter out = resp.getWriter();
	//브라우져에서 응답내용을 출력할도구를 받아 변수out에 넣음
	
	String deptno = req.getParameter("deptno"); //
	
	
	out.print("<html><body>");
		Connection conn = null; //DB연결 객체를 담을 변수(아직초깃값)
		PreparedStatement pstmt = null; // SQL 실행객체를 담을 변수(아직초깃값)
		
		String query = "DELETE FROM emp WHERE deptno = ?";
	
		
		try {
			conn = DBUtil.getConnection(); //DB연결
			pstmt = conn.prepareStatement(query);
			// DB 연결(conn)을 이용해 SQL 실행 객체를 만들고 pstmt에 담는다.
			
			pstmt.setInt(1, Integer.parseInt(deptno)); //문자열을 정수형으로 바꿈
			
			int result = pstmt.executeUpdate();
			//준비한 sql을 실행,영향받은 행수를 result에 저장
			//executeUpdate 는 INSERT·UPDATE·DELETE SQL을 실행하는 메서드
			
			if (result == 1) {
				out.print("delete()성공");
			} else {
				out.print("delete()실패");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.close(null, pstmt, conn);
		}
	
	out.print("</body></html>");
	
	
	
	
	}
}
