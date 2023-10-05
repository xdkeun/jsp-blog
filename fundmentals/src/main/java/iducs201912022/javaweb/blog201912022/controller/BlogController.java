// Project Structure -> Artifacts -> Group, Artifact
package iducs201912022.javaweb.blog201912022.controller;

import iducs201912022.javaweb.blog201912022.model.Blog;
import iducs201912022.javaweb.blog201912022.model.Member;
import iducs201912022.javaweb.blog201912022.repository.BlogDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "BlogController", urlPatterns = {"/blogs/post-form.do", "/blogs/post.do",
        "/blogs/get.do", "/blogs/detail.do",
        "/blogs/update-form.do", "/blogs/update.do", "/blogs/delete.do"})

public class BlogController extends HttpServlet {

            private static final long serialVersionUID = 1L;
            public BlogController() {        super();    }

            BlogDAOImpl repository = null;
            Blog blog = null;
            List<Blog> blogList = null;

            private static final String SAVE_DIR = "files";
            private String partName = null;
            private String partValue = null;

    protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(); // 세션 객체를 가져옴
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length() + 1); // blogs/post.do, blog/list.do가 반환됨
        String action = command.substring(command.lastIndexOf("/") + 1); // post.do, list.do 반환

        request.setCharacterEncoding("UTF-8");
        repository = new BlogDAOImpl();


        BlogDAOImpl dao = new BlogDAOImpl();

        if (action.equals("post-form.do")) {

            Member member = new Member();
            member.setEmail((String) session.getAttribute("logined"));
            member.setName((String) session.getAttribute("name"));

            request.setAttribute("loginedEmail", member.getEmail()); //email로 조회한 회원 정보 객체를 member라는 키로 request에 attribute로 저장
            request.setAttribute("loginedName", member.getName()); //email로 조회한 회원 정보 객체를 member라는 키로 request에 attribute로 저장
            request.getRequestDispatcher("../blogs/blog-post-form.jsp").forward(request, response);

        }
        else if(action.equals("post.do")) {
            Blog blog = new Blog();
            blog.setName(request.getParameter("name"));
            blog.setEmail(request.getParameter("email"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));
            blog.setRegdate(request.getParameter("regdate"));

            if (dao.create(blog) > 0) {
                request.setAttribute("blog", blog);
                request.setAttribute("message", "블로그 작성 성공");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        }
        else if(action.equals("get.do") && request.getParameter("id") != null) {
            Blog ret = new Blog();
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            if((ret = dao.read(blog)) != null) {
                request.setAttribute("blog", ret);
                request.getRequestDispatcher("../blogs/detail-form.jsp").forward(request, response);
            }

            else {
                request.setAttribute("message", "블로그 조회 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }

        else if(action.equals("get.do")) {
            List<Blog> blogList = new ArrayList<Blog>();
            if((blogList = dao.readList()) != null) {
                request.setAttribute("blogs", blogList);
                request.getRequestDispatcher("../blogs/list-view.jsp").forward(request, response);
            }

            else {
                request.setAttribute("message", "블로그 목록 조회 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }

        else if(action.equals("update.do")){
            int ret = 0;
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            blog.setName(request.getParameter("name"));
            blog.setEmail(request.getParameter("email"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));
            blog.setRegdate(request.getParameter("regdate"));

            if((ret = dao.update(blog)) > 0 ){
                request.setAttribute("blog", blog);
                request.getRequestDispatcher("../status/success.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        } else if(action.equals("delete.do")) {
            int ret = 0;
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));

            if ((ret = dao.delete(blog)) > 0 ) {
                request.setAttribute("blog", blog);
                session.invalidate();
                request.getRequestDispatcher("../status/success.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }

    }


    @Override
    protected void doGet
            (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }
}
