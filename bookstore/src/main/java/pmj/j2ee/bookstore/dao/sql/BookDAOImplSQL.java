package pmj.j2ee.bookstore.dao.sql;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pmj.j2ee.util.jdbc.DBCon;
import pmj.j2ee.bookstore.dao.BookDAO;
import pmj.j2ee.bookstore.dao.BookNotFound;
import pmj.j2ee.bookstore.dao.DAOException;
import pmj.j2ee.bookstore.service.BookTO;

public class BookDAOImplSQL implements BookDAO {
	
	public BookDAOImplSQL() throws DAOException {
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
            String sql = "SELECT * FROM book WHERE isbn = ?";
        	PreparedStatement stmt = con.prepareStatement( sql );
        	stmt.setString(1, isbn );
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
        if ( bk == null) {
        	throw new BookNotFound("No Book for ISBN: " +isbn);
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
            String sql = "UPDATE book set " +
            		"title = ?, price = ?, stock = ? " +
                    "WHERE isbn = ?;";            		
        	PreparedStatement stmt = con.prepareStatement( sql );
        	stmt.setString(1, bk.getTitle() );
        	stmt.setDouble(2, bk.getPrice() );
        	stmt.setInt(3, bk.getStock() );
        	stmt.setString(4, bk.getIsbn() );
            n = stmt.executeUpdate( sql );
        }
        catch(SQLException e) {
            throw new DAOException("Book Update Failed!" + e.getMessage());
        }
        if ( n == 0)
        	throw new BookNotFound( "No Book with ISBN" + bk.getIsbn() );
    }
    
    @Override
    public void insert(BookTO bk) throws DAOException {
        int n=0;
        try {
            String sql = "INSERT INTO book (isbn, title, price, stock) " +
            		"VALUES(?, ?, ?, ?)"; 
        	PreparedStatement stmt = con.prepareStatement( sql );
        	stmt.setString(1, bk.getIsbn() );
        	stmt.setString(2, bk.getTitle() );
        	stmt.setDouble(3, bk.getPrice() );
        	stmt.setInt(4, bk.getStock() );
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
        int n=0;
        try {
            String sql = "DELETE FROM book WHERE isbn = ?"; 
        	PreparedStatement stmt = con.prepareStatement( sql );
        	stmt.setString(1, isbn );
            n = stmt.executeUpdate( sql );
        }
        catch(SQLException e) {
            throw new DAOException("Book Delete Failed!" + e.getMessage());
        }
        
        if ( n == 0)
        	throw new BookNotFound( "No Book with ISBN" + isbn );
    }
    
    
    private final int page_size = 3;
    private Connection con;
    
}
