package pmj.j2ee.bookstore.controller.action;

import pmj.j2ee.bookstore.controller.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionRemove implements Action {

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {

    	BookBD.getInstance().removeBook( request.getParameter("isbn") );
            request.setAttribute("stock", BookBD.getInstance().getStock());            

            return "catalogue_view.jsp";        
    }

}
