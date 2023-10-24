package pmj.j2ee.bookstore.service;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.enterprise.context.RequestScoped;

import pmj.j2ee.bookstore.dao.BookDAO;
import pmj.j2ee.bookstore.dao.DAOException;
import pmj.j2ee.bookstore.dao.DAOFactory;

@Path("books")
@RequestScoped
class BookServiceImplRS implements BookServiceRS {

	BookServiceImplRS() throws DAOException {
		dao = DAOFactory.getBookDAO();
	}
	
    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStock() {
        ArrayList<BookTO> book_list = null;  
        try {        	
        	book_list = dao.getAll();        	
        }
        catch(Exception e) {
        	Response.ok("Failed: " + e.getMessage()).build();
        }        
        
        return Response.ok(book_list).build();

    }

    @Override
    @GET
    @Path("/{isbn}")
    public Response getBook(@PathParam("isbn") String isbn) {
        BookTO bk = null;
        try {
        	bk = dao.find(isbn);
        }
        catch(Exception e) {
        	Response.ok("Failed: " + e.getMessage()).build();
        }
        return Response.ok(bk).build();
    }
       
    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(BookTO bk) {
        try {
        	dao.insert(bk);
        }
        catch(Exception e) {
        	Response.ok("Failed: " + e.getMessage()).build();
        }
	    return Response.ok("Successfully Added").build();
    }

    @Override
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(BookTO bk) {
        try {
        	dao.update(bk);
        }
        catch(Exception e) {
        	Response.ok("Failed: " + e.getMessage()).build();
        }
	    return Response.ok("Successfully Updated").build();
    }
    
    @Override
    @GET
    @Path("/{isbn}")
	public Response removeBook(@PathParam("isbn") String isbn) {
        try {
        	dao.delete(isbn);
        }
        catch(Exception e) {
        	Response.ok("Failed: " + e.getMessage()).build();
        }
        return Response.ok("Successfully Removed").build();
   }    

    private BookDAO dao;
    
}
