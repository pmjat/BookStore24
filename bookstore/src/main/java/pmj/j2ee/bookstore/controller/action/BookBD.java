package pmj.j2ee.bookstore.controller.action;

import java.util.ArrayList;

import pmj.j2ee.bookstore.model.BookBean;
import pmj.j2ee.bookstore.service.BookService;
import pmj.j2ee.bookstore.service.BookServiceFactory;
import pmj.j2ee.bookstore.service.BookTO;
public class BookBD {	

	private BookService service;

	private BookBD() {
		if (service == null)
			service = BookServiceFactory.getService();
	}
	
	private static BookBD _this;
	
	public static BookBD getInstance() {
		if ( _this == null )
			_this = new BookBD();
		return _this;
	}	
	
	public ArrayList<BookBean> getStock() {
	    return Cast2Bean( service.getStock() );       
	}
	
	public BookBean getBook(String isbn) {
	    return TO2Bean( service.getBook(isbn) );       
	}
	
	public void addBook(BookBean book) {		
		BookTO bookt = Bean2TO( book );
        service.addBook(bookt);
	}
	
	public void saveBook(BookBean book) {		
		BookTO bookt = Bean2TO( book );
        service.updateBook(bookt);
	}
	
	public void removeBook(String isbn) {
		service.removeBook(isbn);       
	}
	
	private ArrayList<BookBean> Cast2Bean(ArrayList<BookTO> bookst) {
		ArrayList<BookBean> books = new ArrayList<>();
		for(BookTO bkt : bookst) {
			books.add( TO2Bean(bkt) );
		}
		return books;
	}
	
	private BookBean TO2Bean(BookTO bkt) {
		BookBean book = new BookBean(bkt.getIsbn(), bkt.getTitle(), 
				bkt.getPrice(), bkt.getStock());
		return book;
	}
	
	private BookTO Bean2TO(BookBean bk) {
		BookTO book = new BookTO(bk.getIsbn(), bk.getTitle(), 
				bk.getPrice(), bk.getStock());
		return book;
	}	
	
	//private static BookService service = BookServiceFactory.getService();	

}
