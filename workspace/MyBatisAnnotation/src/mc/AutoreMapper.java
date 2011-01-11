package mc;

import java.util.List;

import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface AutoreMapper {
	@Insert("create table Autore(id INTEGER PRIMARY KEY,email VARCHAR(20)NOT NULL, name VARCHAR(15) NOT NULL)")
	void createAuthorTable();

	@Insert("insert into Autore(id,name,email) values( #{id}, #{name}, #{email})")
	void insertAuthor(Autore autore);

    @Delete("DELETE FROM Autore WHERE id = #{id}")
    void deleteAuthor(Long id);

    @Select("SELECT * FROM Autore WHERE id = #{id}")
    Autore selectAuthor(Long id);

    @Select("SELECT * FROM Autore")
    List<Autore> selectAllAuthors();


}
