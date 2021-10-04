package pmj.j2ee.bookstore.dao;

public class BookNotFound extends Exception {
    private static final long serialVersionUID = 1L;
	public BookNotFound() {
        super();
    }
	public BookNotFound(String message) {
        super( message );
    }
}
