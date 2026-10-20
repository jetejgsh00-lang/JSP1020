package cloud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/HellowServlet") //요청URL이된다
public class HellowServlet extends HttpServlet { //상속을 하나받는다.
	private static final long serialVersionUID = 1L;
       
   
	
    public HellowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet()실행됨요"); //실행
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
		System.out.println("init()실행됨요"); //준비
			
		}
	
	@Override
		public void destroy() { //종료
			// TODO Auto-generated method stub
		System.out.println("destroy()실행됨요"); //종료
			
		}

}
