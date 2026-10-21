package cloud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cloud.util.DBUtil;


@WebServlet("/empServlet")
public class EmpSelectServlet extends HttpServlet {

	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
       
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		out.print("<html><body>");
		
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String query = "SELECT * FROM emp";
			
			try {
				conn = DBUtil.getConnection();
				pstmt = conn.prepareStatement(query);
				 
				rs = pstmt.executeQuery(); //쿼리가 실행된것을 읽음
				
				out.print("----------------------------사원정보----------------------------"+"<br>");
				while (rs.next()) { //읽으려고
					//필드에맞는 변수처리
					//가독성
					int empno = rs.getInt("empno");
					String ename = rs.getString("ename");
					String job = rs.getString("job");
					int mgr = rs.getInt("mgr");
					String hiredate = rs.getString("hiredate");
					int sal = rs.getInt("sal");
					int comm = rs.getInt("comm");
					int deptno = rs.getInt("deptno");
					
					
					out.print(empno+ "\t"+ ename + "\t" + job + "\t" + mgr + 
							"\t" + hiredate+"\t" + sal + "\t" + comm +"\t"+deptno +"<br>");
					
				}
				out.print("------------------------------------------------------------");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				DBUtil.close(rs, pstmt, conn); //util클래스에서 가져옴
			}
		
		
		out.print("<body><html>");
	}



}
