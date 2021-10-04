package pmj.j2ee.bookstore.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pmj.j2ee.bookstore.controller.Action;

public class ActionLoginForm implements Action {

    @Override
    public String perform(HttpServletRequest req, HttpServletResponse res) {
        String view = "login.jsp";
        return view;
    }
    
}
