package iducs201912022.javaweb.blog201912022.repository;
import iducs201912022.javaweb.blog201912022.model.Blog;
import iducs201912022.javaweb.blog201912022.model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAOImpl extends OracleDAOImpl implements BlogDAO{

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public BlogDAOImpl() {
        this.conn = getConnection();
    }

    @Override
    public int create(Blog blog) {
        String query = "insert into b201912022 values(seq_b201912022.nextval, ?, ?, ?, ?, ?)";
        int rows = 0;
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, blog.getName());
            pstmt.setString(2, blog.getEmail());
            pstmt.setString(3, blog.getTitle());
            pstmt.setString(4, blog.getContent());
            pstmt.setString(5, blog.getRegdate());
//            pstmt.setTimestamp(5, blog.getRegDateTime());

            rows = pstmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return rows;
    }


    @Override
    public Blog read(Blog blog) {
        Blog ret = null;
//        String sql = "select * from b201912022 where id='" + blog.getId() + "'";
        String sql = "select * from b201912022 where id='" + blog.getId() + "'";
        try {
            conn = getConnection(); //DB 연결 객체 생성
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            //pstmt.setLong(1,blog.getId());
            System.out.println(blog.getId());
            if(rs.next()){ //결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
                ret = new Blog();
                ret.setId(rs.getLong("id"));
                ret.setTitle(rs.getString("title"));
                ret.setContent(rs.getString("content"));
                ret.setName(rs.getString("name"));
                ret.setEmail(rs.getString("email"));
                ret.setRegdate(rs.getString("regdate"));

//                retBlog = new Blog();
//                retBlog.setId(rs.getInt(1)); // rs.getString("<field_name>"); 필드이름로도 가능
//                retBlog.setTitle(rs.getString(2)); // 생성 시 필드 순서
//                retBlog.setContent(rs.getString(3));
//                retBlog.setFilepath(rs.getString(4));
//                retBlog.setBlogger(rs.getString(5));;
//                retBlog.setRegDateTime(rs.getTimestamp(6));

            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return ret;
        }
    }

    @Override
    public List<Blog> readList() {
        List<Blog> blogList = null;
        Blog ret = null;
        String sql = "select * from b201912022 order by regdate desc";
        try {
            conn = getConnection(); //DB 연결 객체 생성
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql); //SQL 실행 후 결과를 rs에 반환

            blogList = new ArrayList<Blog>();
            while(rs.next()){ //결과 집합에서 다음 결과가 있는 지 확인, 있으면 true
                ret = new Blog();
                ret.setId(rs.getLong("id"));
                ret.setTitle(rs.getString("title"));
                ret.setContent(rs.getString("content"));
                ret.setName(rs.getString("name"));
                ret.setEmail(rs.getString("email"));
                ret.setRegdate(rs.getString("regdate"));
                blogList.add(ret);

//                retBlog = new Blog();
//                retBlog.setId(rs.getInt(1)); // rs.getString("<field_name>"); 필드이름로도 가능
//                retBlog.setTitle(rs.getString(2)); // 생성 시 필드 순서
//                retBlog.setContent(rs.getString(3));
//                retBlog.setFilepath(rs.getString(4));
//                retBlog.setBlogger(rs.getString(5));;
//                retBlog.setRegDateTime(rs.getTimestamp(6));
//                blogList.add(retBlog);

            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResources(conn, stmt, pstmt, rs);
            return blogList;
        }
    }

    @Override
    public int update(Blog blog) {
        int ret = 0;
        String sql = "update b201912022 set name=?, email=?, title=?, content=? where id=?";
        try {
            conn = getConnection(); // DB 연결 객체 생성
            // stmt = conn.createStatement(); //createStatement는 위에서 SQL문을 안넣어주기때문에 Update에서 매개변수 있음
            pstmt = conn.prepareStatement(sql); // prepareStatement는 위에서 SQL문을 넣어주기때문에 Update에서 매개변수 없음
            pstmt.setString(1, blog.getName());
            pstmt.setString(2, blog.getEmail());
            pstmt.setString(3, blog.getTitle());
            pstmt.setString(4, blog.getContent());
            pstmt.setLong(5, blog.getId());
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
    public int delete(Blog blog) {
        int ret = 0;
        String sql = "delete from b201912022 where id=?";
        try {
            conn = getConnection(); // DB 연결 객체 생성
            // stmt = conn.createStatement(); //createStatement는 위에서 SQL문을 안넣어주기때문에 Update에서 매개변수 있음
            pstmt = conn.prepareStatement(sql); // prepareStatement는 위에서 SQL문을 넣어주기때문에 Update에서 매개변수 없음
            pstmt.setLong(1, blog.getId());
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
}
