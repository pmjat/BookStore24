package pmj.j2ee.bookstore.controller.action;

import pmj.j2ee.bookstore.controller.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionShowBook implements Action {

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {
    	String isbn = request.getParameter("isbn");
    	request.setAttribute("book", BookBD.getInstance().getBook(isbn));
        return "book_show.jsp";
    }
}
