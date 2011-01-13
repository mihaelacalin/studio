package mc;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface PostMapper {
	@Insert("create table Post(id INTEGER PRIMARY KEY,authorId INTEGER NOT NULL, text VARCHAR(50) NOT NULL)")
	void createPostTable();

	@Insert("insert into Post(id,authorId,text) values( #{id}, #{authorId}, #{text})")
	void insertPost(Post post);

    @Delete("DELETE FROM Post WHERE id = #{id}")
    void deletePost(Long id);

    @Select("SELECT * FROM Post WHERE id = #{id}")
    Post selectPost(Long id);

    @Select("SELECT * FROM Post")
    List<Post> selectAllPost();
    
    @Select("SELECT * FROM Post,Autore WHERE Post.authorId=Autore.id AND Autore.id=#{id}")
    List<Post> selectAllPostOfAuthor(Long id);
    
    @Select("SELECT * FROM Post,Autore WHERE Post.authorId=Autore.id AND Autore.name=#{name}")
    List<Post> selectAllPostOfAuthor1(String name);
    
}
