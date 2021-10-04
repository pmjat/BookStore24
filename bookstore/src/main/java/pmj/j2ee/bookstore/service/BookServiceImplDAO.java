package pmj.j2ee.bookstore.service;

import java.util.ArrayList;

import pmj.j2ee.bookstore.dao.BookDAO;
import pmj.j2ee.bookstore.dao.DAOException;
import pmj.j2ee.bookstore.dao.DAOFactory;

class BookServiceImplDAO implements BookService {

	BookServiceImplDAO() throws DAOException {
		dao = DAOFactory.getBookDAO();
	}
	
    @Override
    public ArrayList<BookTO> getStock() {
        ArrayList<BookTO> book_list = null;        
        try {        	
        	book_list = dao.getAll();        	
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }        
        return book_list;    	    	
    }

    @Override
    public BookTO getBook(String isbn) {
        BookTO bk = null;
        try {
        	bk = dao.find(isbn);
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return bk;
    }
    
    @Override
    public void addBook(BookTO bk) {
        try {
        	dao.insert(bk);
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateBook(BookTO bk) {
        try {
        	dao.update(bk);
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @Override
    public void removeBook(String isbn) {
        try {
        	dao.delete(isbn);
        }
        catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
   }    

    private BookDAO dao;
    
}
