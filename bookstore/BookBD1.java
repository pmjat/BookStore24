package pmj.j2ee.bookstore.controller.action;

import java.util.ArrayList;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import pmj.j2ee.bookstore.model.BookBean;
import pmj.j2ee.bookstore.service.BookService;
import pmj.j2ee.bookstore.service.BookServiceFactory;
import pmj.j2ee.bookstore.service.BookTO;

public class BookBD1 {	

	private BookService service;
	private ResteasyClient client;

	private BookBD1() {
		if (service == null)
			service = BookServiceFactory.getService();
		
		UriBuilder path = UriBuilder.fromPath("http://127.0.0.1:8082/bookstore");
		
		 client = (ResteasyClient)ClientBuilder.newClient();
		 ResteasyWebTarget target = client.target(path);
		 service = target.proxy(BookService.class);
	
	}
	
	private static BookBD1 _this;
	
	public static BookBD1 getInstance() {
		if ( _this == null )
			_this = new BookBD1();
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