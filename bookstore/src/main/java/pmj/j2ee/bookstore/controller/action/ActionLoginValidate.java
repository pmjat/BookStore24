package pmj.j2ee.bookstore.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pmj.j2ee.bookstore.controller.Action;

public class ActionLoginValidate implements Action {

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse resp) {

    	//read user credentials from web.xml
        String user = req.getServletContext().getInitParameter("user");
        String pwd = req.getServletContext().getInitParameter("password");        
        
    	//read user credentials from login form
        String user_in = req.getParameter("user");
        String pwd_in = req.getParameter("password");
        
        String view = null;
        if ( user_in.equals(user) && pwd_in.equals(pwd) ) {
        	//if user credentials are valid, put user name in session
            req.getSession().setAttribute("user", user_in);
            req.setAttribute("stock", BookBD.getInstance().getStock());
            view = "catalogue_view.jsp";
        }
        else { //if not get back to the user with login advise
            req.getSession().setAttribute("user", null);
            view = "login_failure.jsp";
        }

        return view;
        
    }
    
}
