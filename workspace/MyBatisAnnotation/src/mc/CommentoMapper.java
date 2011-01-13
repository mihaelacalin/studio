package mc;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface CommentoMapper {
	@Insert("create table Commento(id INTEGER PRIMARY KEY,authorId INTEGER NOT NULL, postId INTEGER NOT NULL,text VARCHAR(50) NOT NULL)")
	void createCommentoTable();

	@Insert("insert into Commento(id,authorId,postId,text) values( #{id}, #{authorId}, #{postId},#{text})")
	void insertCommento(Commento commento);

	@Delete("DELETE FROM Commento WHERE id = #{id}")
	void deleteCommento(Long id);

	@Select("SELECT * FROM Commento WHERE id = #{id}")
	Commento selectCommento(Long id);

	@Select("SELECT * FROM Commento")
	List<Commento> selectAllComments();

	@Select("SELECT * FROM Commento,Autore WHERE Commento.authorId=Autore.id AND Autore.id=#{id} ")
	List<Commento> selectAllCommentsOfAuthor(Long id);

	@Select("SELECT * FROM Commento,Post WHERE Commento.postId=Post.id AND Post.id=#{id}")
	List<Commento> selectAllCommentsOfPost(Long id);
}
