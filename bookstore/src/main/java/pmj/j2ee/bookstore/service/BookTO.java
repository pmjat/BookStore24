package pmj.j2ee.bookstore.service;

public class BookTO implements Cloneable {

    public BookTO() {
    	book = new Book();
    }
    
    public BookTO(String isbn, String title, double price, int qty) {
    	book = new Book(isbn, title, price, qty);
    }
    
    public String getIsbn() {
        return book.getIsbn();
    }

    public void setIsbn(String isbn) {
        book.setIsbn(isbn);
    }

    public double getPrice() {
        return book.getPrice();
    }

    public void setPrice(double price) {
        book.setPrice(price);
    }

    public String getTitle() {
        return book.getTitle();
    }

    public void setTitle(String title) {
        book.setTitle(title);
    }

    public int getStock() {
        return book.getStock();
    }

    public void setStock(int stock) {
        book.setStock(stock);
    }

    @Override
    public BookTO clone() {
        BookTO book = null;
        try {
            book = (BookTO) super.clone();
            book.book = this.book.clone();            
        }
        catch(CloneNotSupportedException e) {}
        return book;
    }
    
    void setBook(Book bk) {
    	book = bk;
    }
    
    Book getBook() {
    	return book.clone();
    }    

    @Override
    public String toString() {
        return book.toString();
    }
    
    private Book book;
}
