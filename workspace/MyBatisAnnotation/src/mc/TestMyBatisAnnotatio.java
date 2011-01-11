package mc;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import sun.jdbc.odbc.ee.DataSource;

public class TestMyBatisAnnotatio {

	public static void main(String[] args) throws IOException {
		SqlSessionFactory myBatis = new Config().getSqlSessionFactory();
		SqlSession session = myBatis.openSession();
		session.getConfiguration();
		try {
			AutoreMapper mapperA = session.getMapper(AutoreMapper.class);
			PostMapper mapperP = session.getMapper(PostMapper.class);
			CommentoMapper mapperC = session.getMapper(CommentoMapper.class);

			mapperA.createAuthorTable();
			Autore luigi = new Autore(10L, "luigi", "luigi@inter.net");
			Autore pippo = new Autore(20L, "pippo", "pippo@inter.net");
			Autore pluto = new Autore(30L, "pluto", "pluto@inter.net");
			mapperA.insertAuthor(luigi);
			mapperA.insertAuthor(pluto);
			mapperA.deleteAuthor(pippo.getId());
			Autore autore = mapperA.selectAuthor(luigi.getId());
			System.out.println(autore.getEmail());

			List<Autore> autori = mapperA.selectAllAuthors();
			for (Autore author : autori) {
				System.out.println(author.getEmail() + ":" + author.getName());
			}

			mapperP.createPostTable();
			Post luigiPost = new Post(10L, luigi.getId(), "primo post di luigi");
			mapperP.insertPost(luigiPost);
			luigiPost = new Post(20L, luigi.getId(), "secondo post di luigi");
			Post plutoPost = new Post(30L, pluto.getId(), "primo post di pluto");
			mapperP.insertPost(luigiPost);
			mapperP.insertPost(plutoPost);
			mapperP.deletePost(pluto.getId());
			Post post = mapperP.selectPost(20L);
			System.out.println(post.getText());

			List<Post> pList = mapperP.selectAllPost();
			for (Post post2 : pList) {
				System.out.println(post2.getId() + " " + post2.getText());
			}
			
			mapperC.createCommentoTable();
			Commento luigiCommento = new Commento(10L, luigi.getId(),pippo.getId(), "sono luigi e commento il post di pippo");
			mapperC.insertCommento(luigiCommento);
			luigiCommento = new Commento(20L, luigi.getId(),20L, "sono luigi e commento il mio secondo post");
			mapperC.insertCommento(luigiCommento);
			Commento plutoCommento = new Commento(30L, pluto.getId(),20L, "sono pluto e commento il secondo post di luigi");
			
			mapperC.insertCommento(plutoCommento);
			mapperC.deleteCommento(10L);
			
			List<Commento> cList = mapperC.selectAllComments();
			for (Commento commento : cList) {
				System.out.println(commento.getId() + " " + commento.getText());
			}
			//fine CRUD di ogni tabella

		} finally {
			session.close();
		}

	}

}
