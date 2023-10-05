package iducs201912022.javaweb.blog201912022.repository;
import iducs201912022.javaweb.blog201912022.model.Blog;
import java.util.List;

public interface BlogDAO {
    int create(Blog blog);
    Blog read(Blog blog);
    List<Blog> readList();
    int update(Blog blog);
    int delete(Blog blog);

}
