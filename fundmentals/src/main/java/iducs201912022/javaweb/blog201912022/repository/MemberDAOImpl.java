package iducs201912022.javaweb.blog201912022.repository;

import iducs201912022.javaweb.blog201912022.model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl extends OracleDAOImpl implements MemberDAO{
    // 연관 객체 선언
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public MemberDAOImpl(){ //생성자 : 객체 생성 시 초기화

    }

    @Override
    public int create(Member m) {
        int ret = 0;
        String sql = "insert into m201912022 values(seq_m.nextval, ?, ?, ?, ?, ?)";
        try {
            conn = getConnection(); // DB 연결 객체 생성
            // stmt = conn.createStatement(); //createStatement는 위에서 SQL문을 안넣어주기때문에 Update에서 매개변수 있음
            pstmt = conn.prepareStatement(sql); // prepareStatement는 위에서 SQL문을 넣어주기때문에 Update에서 매개변수 없음
            pstmt.setString(1, m.getEmail());
            pstmt.setString(2, m.getPw());
            pstmt.setString(3, m.getName());
            pstmt.setString(4, m.getPhone());
            pstmt.setString(5, m.getAddress());
            ret = pstmt.executeUpdate(); // SQL 실행 후 결과를 rs에 반환, SQL 실행으로 영향받은 row 수를 반환
            if(ret > 0) { // 결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
                ret = 1;
            }
        } catch(SQLException e) {
            System.out.println("dao impl " + e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return ret;
        }
    }


    @Override
    public Member read(Member m) {
        Member retMember = null;
        String sql = "select * from m201912022 where email=?";
        try {
            conn = getConnection(); //DB 연결 객체 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, m.getEmail());
            rs = pstmt.executeQuery(); //SQL 실행 후 결과를 rs에 반환

            while(rs.next()){ //결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
                retMember = new Member();
                retMember.setEmail(rs.getString("email"));
                retMember.setPw(rs.getString("pw"));
                retMember.setName(rs.getString("name"));
                retMember.setPhone(rs.getString("phone"));
                retMember.setAddress(rs.getString("address"));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return retMember;
        }
    }

    @Override
    public List<Member> readList() {
        List<Member> memberList = null;
        Member retMember = null;
        String sql = "select * from m201912022";
        try {
            conn = getConnection(); //DB 연결 객체 생성
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql); //SQL 실행 후 결과를 rs에 반환
            memberList = new ArrayList<Member>();
            while(rs.next()){ //결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
                retMember = new Member();
                retMember.setId(rs.getLong("id"));
                retMember.setEmail(rs.getString("email"));
                retMember.setPw(rs.getString("pw"));
                retMember.setName(rs.getString("name"));
                retMember.setPhone(rs.getString("phone"));
                retMember.setAddress(rs.getString("address"));
                memberList.add(retMember);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return memberList;
        }
    }

    @Override
    public int update(Member m) {
        int ret = 0;
        String sql = "update m201912022 set name=?, phone=?, address=? where email=?";
        try {
            conn = getConnection(); // DB 연결 객체 생성
            // stmt = conn.createStatement(); //createStatement는 위에서 SQL문을 안넣어주기때문에 Update에서 매개변수 있음
            pstmt = conn.prepareStatement(sql); // prepareStatement는 위에서 SQL문을 넣어주기때문에 Update에서 매개변수 없음
            pstmt.setString(1, m.getName());
            pstmt.setString(2, m.getPhone());
            pstmt.setString(3, m.getAddress());
            pstmt.setString(4, m.getEmail());
            ret = pstmt.executeUpdate(); // SQL 실행 후 결과를 rs에 반환, SQL 실행으로 영향받은 row 수를 반환
            if(ret > 0) { // 결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
                ret = 1;
            }
        } catch(SQLException e) {
            System.out.println("dao impl " + e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return ret;
        }
    }

    @Override
    public int delete(Member m) {
        int ret = 0;
        String sql = "delete from m201912022 where email=? and pw=?";
        try {
            conn = getConnection(); // DB 연결 객체 생성
            // stmt = conn.createStatement(); //createStatement는 위에서 SQL문을 안넣어주기때문에 Update에서 매개변수 있음
            pstmt = conn.prepareStatement(sql); // prepareStatement는 위에서 SQL문을 넣어주기때문에 Update에서 매개변수 없음
            pstmt.setString(1, m.getEmail());
            pstmt.setString(2, m.getPw());
            ret = pstmt.executeUpdate(); // SQL 실행 후 결과를 rs에 반환, SQL 실행으로 영향받은 row 수를 반환
            if(ret > 0) { // 결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
                ret = 1;
            }
        } catch(SQLException e) {
            System.out.println("dao impl " + e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return ret;
        }
    }

    @Override
    public Member login(Member m) {
        Member retMember = null;
        String sql = "select * from m201912022 where email=? and pw=?";
        try {
            conn = getConnection(); //DB 연결 객체 생성
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, m.getEmail());
            pstmt.setString(2, m.getPw());
            rs = pstmt.executeQuery(); //SQL 실행 후 결과를 rs에 반환

            while(rs.next()){ //결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
                retMember = new Member();
                retMember.setEmail(rs.getString("email"));
                retMember.setName(rs.getString("name"));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return retMember;
        }
    }

}
