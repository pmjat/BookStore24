package pmj.j2ee.bookstore.dao.jpa;

import java.util.List;
import pmj.j2ee.bookstore.service.BookTO;

public class JPADAOTester {

	public static void main(String args[]){

        BookDAOImplJPA dao = new BookDAOImplJPA();        
        try {
        	
        	//Display all books
            System.out.println( "All Books-1" );
        	List<BookTO> books = dao.getAll();
        	printBooks(books);

            //What is the book with ISBN = 1001
        	//BookTO book = dao.find("1001");
            //System.out.println( "   " + book );
            
            //book.setTitle("Database Systems");        
            //dao.update(book);
        	//BookTO book1 = dao.find("1001");
            //System.out.println( "   " + book1 );

        	//BookTO book2 = new BookTO("1011", "Core J2EE� Patterns - new", 1250.0, 5);
            //dao.insert(book2);
            
            //dao.delete("1011");

        	//List<BookTO> books = dao.getAll();
        	//printBooks(books);
            
        	 
        }
        catch(Exception e) {
        	System.out.println( e.getMessage());
        }

    }
	
	public static void printBooks(List<BookTO> books) {
    	System.out.println( "List of books in database:");
        for(BookTO bk : books)
            System.out.println( "   " + bk );		
	}

}
