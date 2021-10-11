package pmj.j2ee.bookstore.controller; 

import java.util.Properties;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import pmj.j2ee.bookstore.controller.action.ActionMessage;
import pmj.j2ee.util.ObjectCreator;

import javax.servlet.RequestDispatcher;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, 
    								HttpServletResponse response)
            throws ServletException, IOException {

        String theAction = request.getParameter("action");

        if ( theAction == null)
            theAction = "viewcat";
        
        Action action = CommandMapper.getInstance().getCommand( theAction );

        String view = action.perform(request, response);
        
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);    
    } 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
}