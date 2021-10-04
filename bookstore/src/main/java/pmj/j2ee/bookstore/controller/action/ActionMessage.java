package pmj.j2ee.bookstore.controller.action;

import pmj.j2ee.bookstore.controller.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pmj.j2ee.bookstore.model.BookBean;
import pmj.j2ee.bookstore.model.BookBeanUtils;

public class ActionMessage implements Action {

	
	public ActionMessage(String msg) {
		message = msg;
	}
	
    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {
    	request.setAttribute("message", message);    	
        return "message.jsp";        
    }
    
    public void setMessage(String mesg) {
    	message = mesg;
    }
    
    private String message;
}
