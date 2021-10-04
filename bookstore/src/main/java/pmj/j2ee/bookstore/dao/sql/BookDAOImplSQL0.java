package pmj.j2ee.bookstore.dao.sql;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pmj.j2ee.util.jdbc.DBCon;
import pmj.j2ee.bookstore.dao.BookDAO;
import pmj.j2ee.bookstore.dao.BookNotFound;
import pmj.j2ee.bookstore.dao.DAOException;
import pmj.j2ee.bookstore.service.BookTO;

public class BookDAOImplSQL0 implements BookDAO {
	
	public BookDAOImplSQL0() throws DAOException {
		try {
			con = DBCon.getConnection();
			con.setSchema("books");
		}
        catch(SQLException e) {
            throw new DAOException( e.getMessage() );
        }
	}

    @Override
    public BookTO find(String isbn) throws BookNotFound, DAOException {
        BookTO bk = null;
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM book WHERE isbn ='" + isbn + "'";
            ResultSet rs = stmt.executeQuery( sql );
            if ( rs.next() ) {
                bk = new BookTO();
                bk.setIsbn( isbn );
                bk.setTitle(rs.getString("title"));
                bk.setPrice(rs.getDouble("price"));
                bk.setStock(rs.getInt("stock"));            
            }
        }
        catch(SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return bk;
    }

    @Override
    public ArrayList<BookTO> getAll() throws DAOException {
        ArrayList<BookTO> book_list = new ArrayList<>();
        BookTO bk;
        Statement stmt;
        try {
            stmt = con.createStatement();        
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK");
            while ( rs.next() ) {
                bk = new BookTO();
                bk.setIsbn( rs.getString("isbn") );
                bk.setTitle(rs.getString("title"));
                bk.setPrice(rs.getDouble("price"));
                bk.setStock(rs.getInt("stock"));            
                book_list.add( bk );
            }        
        }
        catch(SQLException e) {
            throw new DAOException(e.getMessage());
        }
        
        return book_list;

    }

    @Override
    public ArrayList<BookTO> getAllPaginated(int page) throws DAOException {
        ArrayList<BookTO> book_list = new ArrayList<>();
        BookTO bk;
        Statement stmt;
        try {
            stmt = con.createStatement();        
            //use offset and limit to return required page
            int offset = (page-1)*page_size;
            String sql = "SELECT * FROM BOOK limit " + page_size + " offset " + offset;
            ResultSet rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                bk = new BookTO();
                bk.setIsbn( rs.getString("isbn") );
                bk.setTitle(rs.getString("title"));
                bk.setPrice(rs.getDouble("price"));
                bk.setStock(rs.getInt("stock"));            
                book_list.add( bk );
            }        
        }
        catch(SQLException e) {
            throw new DAOException(e.getMessage());
        }
        
        return book_list;

    }

    @Override
    public void update(BookTO bk) throws BookNotFound, DAOException {
        //pre-condition: isbn exists
        int n=0;
        try {
            Statement stmt = con.createStatement();
            String sql;
            sql = "UPDATE book set " +
                "title = '" +  bk.getTitle() + "', " +
                "price = " + bk.getPrice() + ", " +
                "stock = " + bk.getStock() + 
                " WHERE isbn = '" + bk.getIsbn() + "'; ";
            n = stmt.executeUpdate( sql );
        }
        catch(SQLException e) {
            throw new DAOException(e.getMessage());
        }
        if ( n == 0)
        	throw new DAOException( "Book Could not be Updated!" );        
    }
    
    @Override
    public void insert(BookTO bk) throws DAOException {
        int n=0;
        try {
            Statement stmt = con.createStatement();
            String sql;
            sql = "INSERT INTO book (isbn, title, price, stock) VALUES(" +
                "'" + bk.getIsbn() + "', " +
                "'" + bk.getTitle() + "', " +
                bk.getPrice() + ", " +
                bk.getStock() + "); ";
            n = stmt.executeUpdate( sql );
        }
        catch(SQLException e) {
            throw new DAOException(e.getMessage());
        }
        if ( n == 0)
        	throw new DAOException( "Book Could not be Inserted!" );        
    }

    @Override
    public void delete(String isbn) throws BookNotFound, DAOException {
        String sql = "DELETE FROM book WHERE isbn = '" + isbn + "'; ";
        int n=0;
        try {
            Statement stmt = con.createStatement();
            n = stmt.executeUpdate( sql );
        }
        catch(SQLException e) {
            throw new DAOException(e.getMessage());
        }
        
        if ( n == 0)
        	throw new BookNotFound( "Book not found for deletion!" );        
    }
    
    
    private final int page_size = 3;
    private Connection con;
    
}
