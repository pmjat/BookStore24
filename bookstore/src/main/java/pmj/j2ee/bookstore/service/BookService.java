package pmj.j2ee.bookstore.service;
import java.util.ArrayList;

public interface BookService {
    
    public ArrayList<BookTO> getStock();

    public BookTO getBook(String isbn);
    
    public void addBook(BookTO bk);

    public void updateBook(BookTO bk);
    
    public void removeBook(String isbn);

}
