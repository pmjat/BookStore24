package pmj.j2ee.util.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import org.postgresql.ds.PGConnectionPoolDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import java.sql.DriverManager;

public class DBCon {

    public static Connection getJDBCConnection() 
    		throws ClassNotFoundException, SQLException {

        Class.forName( DBConfig.DB_URL ); //JDBC Driver Name
        Connection con = DriverManager.getConnection (
        		DBConfig.DB_URL,
        		DBConfig.DB_USER,
        		DBConfig.DB_PWD
        );   	
        
        return con;

    }

    public static Connection getConnection() 
    		throws SQLException {

    	//using postgre specific DataSource class
    	//Can use Factory strategy for hiding this concrete information
    	//makes it bit subtle that DB URL has to match with Concrete 
    	//Data Source object
    	PGSimpleDataSource source = new PGSimpleDataSource();
    	source.setUrl( DBConfig.DB_URL );
    	source.setUser( DBConfig.DB_USER );
    	source.setPassword( DBConfig.DB_PWD );
    	
        return source.getConnection();    	
        
    }
    
    public static Connection getPooledConnection() 
    		throws SQLException {

    	PGConnectionPoolDataSource source = new PGConnectionPoolDataSource();

    	source.setUrl( DBConfig.DB_URL );
    	source.setUser( DBConfig.DB_USER );
    	source.setPassword( DBConfig.DB_PWD );
    	
        return source.getConnection();    	
        
    }
    
}
