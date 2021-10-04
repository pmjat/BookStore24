package pmj.j2ee.bookstore.service;

class Book implements Cloneable {

    public Book() {
        this("","",0,0);
    }
    public Book(String isbn, String title, double price, int qty) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStock() {
        return qty;
    }

    public void setStock(int qty) {
        this.qty = qty;
    }

    @Override
    public Book clone() {
        Book book = null;
        try {
            book = (Book) super.clone();
        }
        catch(CloneNotSupportedException e) {}
        return book;
    }
    
    @Override
    public String toString() {
        return "Book{ isbn=" + isbn
                + ", title=" + title 
                + ", price=" + price
                + ", stock=" + qty
                + '}';
    }

    private String isbn = "";
    private String title = "";
    private double price = 0;
    private int qty = 0;

}
