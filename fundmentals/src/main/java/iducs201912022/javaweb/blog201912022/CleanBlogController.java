package iducs201912022.javaweb.blog201912022;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

// contact.jsp의 form의(65line) action 이 contacts로 되어있기 때문에 value도 contacts, /는 webapp과 같은 level
@WebServlet(name = "CleanBlogController", value = "/contacts")
public class CleanBlogController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // contact.jsp -> CleanBlogController(url로는 contacts임) -> result.jsp -> 사용자에게 보여줌
        // 지정한 페이지(result.jsp)에 요청을 전달함
        request.getRequestDispatcher("result.jsp").forward(request, response);


//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + "email : " + request.getParameter("email") + "</h1>");
//        out.println("<h1>" + "name : " + request.getParameter("name") + "</h1>");
//        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
