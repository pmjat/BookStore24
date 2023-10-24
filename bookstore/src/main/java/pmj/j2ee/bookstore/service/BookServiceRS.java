package pmj.j2ee.bookstore.service;

import javax.ws.rs.core.Response;


public interface BookServiceRS {
    
    public Response getStock();

    public Response getBook(String isbn);
    
    public Response addBook(BookTO bk);

    public Response updateBook(BookTO bk);
    
    public Response removeBook(String isbn);

}
