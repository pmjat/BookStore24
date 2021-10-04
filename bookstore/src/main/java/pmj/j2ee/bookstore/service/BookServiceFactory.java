package pmj.j2ee.bookstore.service;

public class BookServiceFactory {
	
	public static BookService getService() {
		
		BookService service = null;
		try {
			service = new BookServiceImplDAO(); //Relational Table					
		}
		catch(Exception e) {
			throw new RuntimeException("Data Source Access Error");
		}
		
		return service;
		
	}
	
}
