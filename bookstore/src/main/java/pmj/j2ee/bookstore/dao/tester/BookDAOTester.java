package pmj.j2ee.bookstore.dao.tester;

import java.util.ArrayList;

import pmj.j2ee.bookstore.dao.BookDAO;
import pmj.j2ee.bookstore.dao.DAOException;
import pmj.j2ee.bookstore.dao.DAOFactory;
import pmj.j2ee.bookstore.service.BookTO;

public class BookDAOTester {

    public static void main(String[] args) throws Exception {
        
        System.out.println("DAO Tester runs ...");
        
        try {
            
            BookDAO dao = DAOFactory.getBookDAO();

            //BookTO bk1 = dao.find("1001");
            //System.out.println( bk1.getIsbn() + "," + bk1.getTitle());
            
            
            ArrayList<BookTO> books = dao.getAll();
            for(BookTO bk : books) {
                System.out.println( bk.getIsbn() + "," + bk.getTitle());
            }

            /*
            System.out.println("page: 2");
            books = dao.getAllPaginated(1);
            for(BookTO bk : books) {
                System.out.println( bk.getIsbn() + "," + bk.getTitle());
            }
            
            BookTO bk2 = new BookTO();
            bk2.setIsbn("101");
            bk2.setTitle("Maven");
            dao.insert(bk2);            
            //bk2.setTitle("Maven");
            //dao.update(bk2);

            //dao.delete("101");
            */
        }
        catch(DAOException e) {
            System.out.println("Error: " + e.getMessage() );
            throw e;
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage() );
            throw e;
        }
    }       
}