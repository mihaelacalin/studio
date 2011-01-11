package mc;

import javax.sql.DataSource;

import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class Config {
	public SqlSessionFactory getSqlSessionFactory() {
		try {
			DataSource dataSource=new PooledDataSource();
			((PooledDataSource) dataSource).setDriver("org.hsqldb.jdbc.JDBCDriver");
			((PooledDataSource)dataSource).setUrl("jdbc:hsqldb:mem:Mydb");
			TransactionFactory transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment("development",
					transactionFactory, dataSource);
			Configuration configuration = new Configuration(environment);
			
			configuration.setLazyLoadingEnabled(true);
			configuration.getTypeAliasRegistry().registerAlias(Autore.class);
			configuration.getTypeAliasRegistry().registerAlias(Post.class);
			configuration.getTypeAliasRegistry().registerAlias(Commento.class);
			configuration.addMapper(AutoreMapper.class);
			configuration.addMapper(PostMapper.class);
			configuration.addMapper(CommentoMapper.class);
			return new SqlSessionFactoryBuilder().build(configuration);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlSessionFactory. Cause: " + e, e);
		}
	}

}
