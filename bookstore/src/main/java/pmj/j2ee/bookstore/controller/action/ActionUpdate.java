package pmj.j2ee.bookstore.controller.action;

import pmj.j2ee.bookstore.controller.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmj.j2ee.bookstore.model.BookBean;
import pmj.j2ee.bookstore.model.BookBeanUtils;

public class ActionUpdate implements Action {

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {
    	
    	BookBean book = new BookBean();
    	BookBeanUtils.populateBean(request, book);
    	BookBD.getInstance().saveBook( book );
        request.setAttribute("stock", BookBD.getInstance().getStock());
        
        return "catalogue_view.jsp";        
    }
}
