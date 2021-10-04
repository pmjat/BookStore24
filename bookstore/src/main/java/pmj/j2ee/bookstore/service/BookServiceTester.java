package pmj.j2ee.bookstore.service;

import java.util.ArrayList;

import pmj.j2ee.bookstore.dao.BookDAO;
import pmj.j2ee.bookstore.dao.DAOException;
import pmj.j2ee.bookstore.dao.DAOFactory;
import pmj.j2ee.bookstore.service.BookTO;

public class BookServiceTester {

    public static void main(String[] args) throws Exception {
        
        System.out.println("Book Service Tester runs ...");
        
        try {
            
            BookService service = BookServiceFactory.getService();

            //BookTO bk1 = dao.find("1001");
            //System.out.println( bk1.getIsbn() + "," + bk1.getTitle());
            
            
            ArrayList<BookTO> books = service.getStock();
            for(BookTO bk : books) {
                System.out.println( bk.getIsbn() + "," + bk.getTitle());
            }

        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage() );
            throw e;
        }
    }       
}