package mc;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Set;

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
			Autore zorro = new Autore(40L, "zorro", "zorro@inter.net");
			mapperA.insertAuthor(luigi);
			mapperA.insertAuthor(pippo);
			mapperA.insertAuthor(pluto);
			mapperA.insertAuthor(zorro);
			System.out.println("stampa la tabella Autore");
			stampaAutori(mapperA);
			System.out.println("cancello Pippo dalla tabella");
			mapperA.deleteAuthor(pippo.getId());
			stampaAutori(mapperA);
			System.out.println("quale sarà l'email di luigi?");
			Autore autore = mapperA.selectAuthor(luigi.getId());
			System.out.println(autore.getEmail());
			System.out.println();

			mapperP.createPostTable();
			Post luigiPost = new Post(10L, luigi.getId(), "primo post di luigi");
			mapperP.insertPost(luigiPost);
			luigiPost = new Post(20L, luigi.getId(), "secondo post di luigi");
			Post plutoPost = new Post(30L, pluto.getId(), "primo post di pluto");
			Post zorroPost = new Post(40L, zorro.getId(), "primo post di zorro");
			mapperP.insertPost(luigiPost);
			mapperP.insertPost(plutoPost);
			mapperP.insertPost(zorroPost);
			System.out.println("stampa la tabella Post");
			stampaPost(mapperP);
			System.out.println("pluto cancella il suo post");
			mapperP.deletePost(pluto.getId());
			stampaPost(mapperP);
			System.out.println("select e stampa del post 20");
			Post post20 = mapperP.selectPost(20L);
			System.out.println(post20.getText());
			System.out.println();
			System.out.println("stampa tutti i post di luigi");
			stampaPost(mapperP, luigi);
			System.out.println("stampa tutti i post di zorro");
			List<Post> posts = mapperP.selectAllPostOfAuthor1("zorro");

			for (Post p : posts)
				System.out.println(p.getAuthorId() + " " + p.getText());
			

			mapperC.createCommentoTable();
			Commento luigiCommento = new Commento(10L, zorroPost.getId(),luigi.getId(), "sono luigi e commento il post di zorro");
			mapperC.insertCommento(luigiCommento);
			luigiCommento = new Commento(20L, luigiPost.getId(), luigi.getId(),
					"sono luigi e commento il mio secondo post");
			mapperC.insertCommento(luigiCommento);
			Commento plutoCommento = new Commento(30L,  luigiPost.getId(),pluto.getId(),
					"sono pluto e commento il secondo post di luigi");
			mapperC.insertCommento(plutoCommento);
			System.out.println("stampa la tabella Commento");
			stampaCommento(mapperC);
			System.out.println("cancella il commento 10");
			mapperC.deleteCommento(10L);
			stampaCommento(mapperC);
			
			System.out.println("stampa tutti i commenti fatti da luigi");
			stampaCommentsOfAuthor(mapperC, luigi);
			
			System.out.println("stampa tutti i commenti fatti da pluto");
			stampaCommentsOfAuthor(mapperC, pluto);
			
			System.out.println("stampa tutti i commenti del post di luigi");
			stampaCommentsOfPost(mapperC, luigiPost);
			

		} finally {
			session.close();
		}

	}

	public static void stampaAutori(AutoreMapper mapperA) {
		List<Autore> autori = (List<Autore>) mapperA.selectAllAuthors();

		for (Autore autore : autori)
			System.out.println(autore.getId()+""+autore.getEmail() + " " + autore.getName());

		System.out.println();
	}

	public static void stampaPost(PostMapper mapperP) {
		List<Post> posts = (List<Post>) mapperP.selectAllPost();

		for (Post post : posts)
			System.out.println(post.getId() + " " + post.getText()+" "+post.getAuthorId());

		System.out.println();
	}

	public static void stampaPost(PostMapper mapperP, Autore a) {
		List<Post> posts = mapperP.selectAllPostOfAuthor(a.getId());

		for (Post post : posts)
			System.out.println(a.getName() + " " + post.getText());

		System.out.println();
	}

	public static void stampaCommento(CommentoMapper mapperC) {
		List<Commento> comments = mapperC.selectAllComments();

		for (Commento commento : comments)
			System.out.println(commento.getId() + " " + commento.getPostId()+ " "+commento.getAuthorId()+" "+commento.getText());

		System.out.println();
	}
	public static void stampaCommentsOfAuthor(CommentoMapper mapperC,Autore a) {
		List<Commento> commenti = mapperC.selectAllCommentsOfAuthor(a.getId());

		for (Commento commento : commenti)
			System.out.println(a.getName()+" " + commento.getText());

		System.out.println();
	}
	public static void stampaCommentsOfPost(CommentoMapper mapperC, Post p) {
		List<Commento> comments = mapperC.selectAllCommentsOfPost(p.getId());

		for (Commento commento : comments)
			System.out.println(p.getId() + " " +p.getText()+" il commento a questo post è: "+ commento.getText());

		System.out.println();
	}

}
