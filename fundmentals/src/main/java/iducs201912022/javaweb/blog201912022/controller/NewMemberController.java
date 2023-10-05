////11.14 교수님은 MemberController로 만들었는데
////나한테는 MemberController가 이미 있어서 NewMemberController로 만듬
////교수님 MemberController == 내 NewMemberController
//package iducs201912022.javaweb.fundmentals.controller;
//
//import iducs201912022.javaweb.fundmentals.model.Member;
//import iducs201912022.javaweb.fundmentals.repository.MemberDAOImpl;
//import iducs201912022.javaweb.fundmentals.repository.MemberDAO;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "NewMemberController", value = "/member-list")
//public class NewMemberController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        MemberDAOImpl memberDAOImpl = new MemberDAOImpl();
//        List<Member> memberList = memberDAOImpl.readList();
//        for(Member m : memberList)
//            System.out.println(m.getTname());
//        // request.setAttribute("list", memberList);
//        // request.getRequestDispatcher("member-list.jsp");
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//}
