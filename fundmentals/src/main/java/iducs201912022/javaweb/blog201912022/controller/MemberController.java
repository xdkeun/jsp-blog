package iducs201912022.javaweb.blog201912022.controller;

import iducs201912022.javaweb.blog201912022.model.Blog;
import iducs201912022.javaweb.blog201912022.model.Member;
import iducs201912022.javaweb.blog201912022.repository.MemberDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MemberController", urlPatterns =
        {"/members/members", "/members/member-create",
                "/members/member-detail", "/members/member-login", "/members/member-logout", "/members/update.do", "/members/delete.do", "/members/get.do"})
public class MemberController extends HttpServlet {
    MemberDAOImpl memberDAOImpl = new MemberDAOImpl();
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); //이거 해야 한글 안깨짐, request 객체의 인코딩을 설정
        HttpSession session = request.getSession(); // 세션 객체를 가져옴
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf('/')+1);

        if (action.equals("members")){
            List<Member> memberList = new ArrayList<Member>();
            if((memberList = memberDAOImpl.readList()) != null) { // null이 아니면
                request.setAttribute("members", memberList);
                request.getRequestDispatcher("result.jsp").forward(request, response); // 정상
            }
            else
                request.getRequestDispatcher("error.jsp"); // 비정상
            // response.sendRedirect("error.jsp"); // 이렇게도 쓴다고 합니다

            request.setAttribute("list", memberList);
        }
        else if (action.equals("member-create")){

            int ret = 0;
            Member m = new Member();
            m.setEmail(request.getParameter("email"));
            m.setPw(request.getParameter("pw"));
            m.setName(request.getParameter("name"));
            m.setPhone(request.getParameter("phone"));
            m.setAddress(request.getParameter("address"));

            if((ret = memberDAOImpl.create(m)) > 0 ) { // create 하면 필드가 하나 추가되므로 반드시 0보다 커야함, 0이면 추가가 안됐다는 뜻임
                
                request.getRequestDispatcher("../main/index.jsp").forward(request, response); // 정상적으로 추가됐을 떄
            }
            else {
                request.setAttribute("message", "회원 등록 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response); // 비정상, 추가가 안됐을 때
            }

            request.getRequestDispatcher("member-view.jsp").forward(request, response);
        }
        else if (action.equals("update.do")){

            int ret = 0;
            Member m = new Member();
            m.setEmail(request.getParameter("email"));
            m.setName(request.getParameter("name"));
            m.setPhone(request.getParameter("phone"));
            m.setAddress(request.getParameter("address"));

            if((ret = memberDAOImpl.update(m)) > 0 ) { // create 하면 필드가 하나 추가되므로 반드시 0보다 커야함, 0이면 추가가 안됐다는 뜻임
                request.setAttribute("member", m);
                request.getRequestDispatcher("../members/member-detail-form.jsp").forward(request, response); // 정상적으로 추가됐을 떄
            }
            else {
                request.setAttribute("message", "회원정보 수정에 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response); // 비정상, 추가가 안됐을 때
            }

            //request.getRequestDispatcher("member-view.jsp").forward(request, response);
        }
        else if (action.equals("delete.do")){

            int ret = 0;
            Member m = new Member();
            m.setEmail(request.getParameter("email"));
            m.setPw(request.getParameter("pw"));

            if((ret = memberDAOImpl.delete(m)) > 0 ) { // create 하면 필드가 하나 추가되므로 반드시 0보다 커야함, 0이면 추가가 안됐다는 뜻임
                session.invalidate();
                request.getRequestDispatcher("../main/index.jsp").forward(request, response); // 정상적으로 추가됐을 떄
            }
            else {
                request.setAttribute("message", "회원탈퇴에 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response); // 비정상, 추가가 안됐을 때
            }
        }
        else if (action.equals("member-detail")) {
            String email = request.getParameter("email");
            // String email = (String) session.getAttribute("logined");
            //String pw = request.getParameter("pw");
            Member member = new Member();
            member.setEmail(email);
            //member.setPw(pw);
            Member retMember = null;
            if ((retMember = memberDAOImpl.read(member)) != null) {
                //로그인 성공
                session.setAttribute("member", retMember); //DB로부터 가져온 정보를 Member 객체로 반환 후 속성으로 설정
                request.getRequestDispatcher("../members/member-detail-form.jsp").forward(request, response);
            } else {
                //로그인 실패
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }
        else if (action.equals("member-login")){
            String email = request.getParameter("email");
            String pw = request.getParameter("pw");
            Member member = new Member();
            member.setEmail(email);
            member.setPw(pw);
            Member retMember = null;
            if((retMember = memberDAOImpl.login(member)) != null){
                //로그인 성공
                session.setAttribute("logined", retMember.getEmail());
                session.setAttribute("name", retMember.getName());
                request.setAttribute("message", retMember.getName() + "님 환영합니다.");
                request.getRequestDispatcher("../main/index.jsp").forward(request, response);
            }
            else {
                //로그인 실패
                request.setAttribute("message", "로그인에 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }
        else if (action.equals("member-logout")){
            session.invalidate(); // session 객체에 저장된 모든 속성들을 무효화됨
            response.sendRedirect("../main/index.jsp");
            //request.getRequestDispatcher("../main/index.jsp").forward(request, response);
        }
        else if(action.equals("get.do")) {
            List<Member> memberList = new ArrayList<Member>();
            if((memberList = memberDAOImpl.readList()) != null) {
                request.setAttribute("members", memberList);
                request.getRequestDispatcher("../members/list-view.jsp").forward(request, response);
            }

            else {
                request.setAttribute("message", "멤버 목록 조회 실패");
                request.getRequestDispatcher("../status/fail.jsp").forward(request, response);
            }
        }

        }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
}
