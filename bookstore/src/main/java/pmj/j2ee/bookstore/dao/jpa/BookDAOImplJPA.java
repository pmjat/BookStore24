package pmj.j2ee.bookstore.dao.jpa;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import pmj.j2ee.bookstore.dao.BookDAO;
import pmj.j2ee.bookstore.dao.DAOException;
import pmj.j2ee.bookstore.dao.BookNotFound;
import pmj.j2ee.bookstore.service.BookTO;

public class BookDAOImplJPA implements BookDAO {

    private final int page_size = 3;
	private EntityManager em;	
	
	public BookDAOImplJPA() {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("BookPU");
        em = emf.createEntityManager();		
	}
    @Override
    public BookTO find(String isbn) throws BookNotFound, DAOException {
        Book book = em.find(Book.class, isbn);
        if (book == null)
        	throw new BookNotFound();
        BookTO bk = new BookTO();
        BookEntity2TO(book, bk);
        return bk;
    }

    @Override
    public ArrayList<BookTO> getAll() throws DAOException {
    	String qstr = "select book from Book book";
    	TypedQuery<Book> qt = em.createQuery(qstr, Book.class);
        List<Book> books_ent = qt.getResultList();
        
        ArrayList<BookTO> books = new ArrayList<BookTO>();
        BookTO book;
        for(Book bk : books_ent) {
            book = new BookTO();
            BookEntity2TO(bk, book);
            books.add(book);
        }
        return books;                
    }

    @Override
    public ArrayList<BookTO> getAllPaginated(int page) throws DAOException {
    	String qstr = "select book from Book book";
    	TypedQuery<Book> qt = em.createQuery(qstr, Book.class);
    	qt.setFirstResult((page-1)*page_size);
    	qt.setMaxResults(page_size);
        List<Book> books_ent = qt.getResultList();
        
        ArrayList<BookTO> books = new ArrayList<BookTO>();
        BookTO book;
        for(Book bk : books_ent) {
            book = new BookTO();
            BookEntity2TO(bk, book);
            books.add(book);
        }
        return books;                
    }

    @Override
    public void update(BookTO bk) throws DAOException {
    	Book book = new Book();
    	BookTO2Entity(book, bk);
        em.getTransaction().begin();
        try {
            em.merge(book);
            em.getTransaction().commit();
        } 
        catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException( "Updated Failed: " + e.getMessage() );
        }
    }
    
    @Override
    public void insert(BookTO bk) throws DAOException {
    	Book book = new Book();
    	BookTO2Entity(book, bk);
        em.getTransaction().begin();
        try {
            em.persist(book);
            em.getTransaction().commit();
        } 
        catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException( "Insert Failed: " + e.getMessage() );
        }
    }

	@Override
    public void delete(String isbn) throws BookNotFound, DAOException {
		
        em.getTransaction().begin();
        Book book = null; 
        try {
            book = em.find(Book.class, isbn);
            if (book == null)
            	throw new BookNotFound("Book Could notfound for deletetion!");
            em.remove(book);
            em.getTransaction().commit();
        } 
        catch (Exception e) {
            em.getTransaction().rollback();
            throw new DAOException( "Delete Failed: " + e.getMessage() );
        }
    }

    private static void BookTO2Entity(Book book_entity, BookTO book_to) {
        book_entity.setIsbn( book_to.getIsbn() );
        book_entity.setTitle( book_to.getTitle() );
        book_entity.setPrice( new BigDecimal(book_to.getPrice()) );
        book_entity.setStock( book_to.getStock() );
    }
    private static void BookEntity2TO(Book book_entity, BookTO book_to) {
        book_to.setIsbn( book_entity.getIsbn() );
        book_to.setTitle( book_entity.getTitle() );
        book_to.setPrice( book_entity.getPrice().doubleValue() );
        book_to.setStock( book_entity.getStock() );
    }
		
}
