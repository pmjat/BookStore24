package pmj.j2ee.bookstore.dao;

import java.util.ArrayList;
import pmj.j2ee.bookstore.service.BookTO;

public interface BookDAO {

   public void insert(BookTO bk) 
		   throws DAOException;
   public void update(BookTO bk) 
		   throws BookNotFound, DAOException;
   public BookTO find(String id) 
		   throws BookNotFound, DAOException;
   public void delete(String isbn) 
		   throws BookNotFound, DAOException;
   public ArrayList<BookTO> getAll() 
		   throws DAOException;
   public ArrayList<BookTO> getAllPaginated(int page) 
		   throws DAOException;   
    
}
