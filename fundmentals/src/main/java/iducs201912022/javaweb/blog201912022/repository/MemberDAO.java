package iducs201912022.javaweb.blog201912022.repository;

import iducs201912022.javaweb.blog201912022.model.Member;
import java.util.List;

public interface MemberDAO {
    int create(Member m);
    Member read(Member m);
    List<Member> readList();
    int update(Member m);
    int delete(Member m);

    Member login(Member m);
}
