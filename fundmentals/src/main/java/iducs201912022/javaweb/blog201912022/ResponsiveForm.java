package iducs201912022.javaweb.blog201912022;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResponsiveForm", value = "/clean-blog/ResponsiveForm")
public class ResponsiveForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        // request : 요청 객체, getParameter() : 전달된 매개변수의 값을 가져오는 메소드
        out.println("<h1>" + request.getParameter("param") + "</h1>");
        out.println("<img src=\"../img/1.jpg\" width=\"200px\">");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.getRequestDispatcher("clean-blog/index.jsp").forward(request, response);
    }
}
