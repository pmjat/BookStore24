package pmj.j2ee.bookstore.model;

public class BookBean implements Cloneable {

	public BookBean() {
        this("","",0,0);
    }
	
    public BookBean(String isbn, String title, double price, int qty) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.qty = qty;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
   
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return qty;
    }

    public void setStock(int qty) {
        this.qty = qty;
    }

    @Override
    public BookBean clone() {
        BookBean book = null;
        try {
            book = (BookBean) super.clone();
        }
        catch(CloneNotSupportedException e) {}
        return book;
    }
    
    private String isbn = "";
    private String title = "";
    private double price = 0;
    private int qty = 0;

}
