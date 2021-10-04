package pmj.j2ee.bookstore.model;

import javax.servlet.ServletRequest;

public class BookBeanUtils {

	public static void populateBean(ServletRequest request, 
			BookBean book) {
		
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        double price = Double.parseDouble(request.getParameter("price"));
        int qty = Integer.parseInt(request.getParameter("stock"));

        book.setIsbn(isbn);
        book.setTitle(title);
        book.setPrice(price);
        book.setStock(qty);
	}

}
