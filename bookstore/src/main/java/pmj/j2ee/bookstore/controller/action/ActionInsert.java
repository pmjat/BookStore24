package pmj.j2ee.bookstore.controller.action;

import pmj.j2ee.bookstore.controller.Action;
import pmj.j2ee.bookstore.model.BookBean;
import pmj.j2ee.bookstore.model.BookBeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionInsert  implements Action {

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {
    	
    	BookBean book = new BookBean();
    	BookBeanUtils.populateBean(request, book);
    	BookBD.getInstance().addBook( book );
        request.setAttribute("stock", BookBD.getInstance().getStock());

        return "catalogue_view.jsp";        
    }
    
}
