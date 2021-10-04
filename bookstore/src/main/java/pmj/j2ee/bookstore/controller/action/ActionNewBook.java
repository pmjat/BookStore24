package pmj.j2ee.bookstore.controller.action;

import pmj.j2ee.bookstore.controller.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionNewBook  implements Action {

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {
            request.setAttribute("book", null);
            return "book_new.jsp";
    }

}
