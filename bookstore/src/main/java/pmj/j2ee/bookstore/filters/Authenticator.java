package pmj.j2ee.bookstore.filters;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.Filter;
//import javax.servlet.http.HttpFilter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class Authenticator implements Filter {
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            
        HttpServletRequest req = (HttpServletRequest) request;
        String action = req.getParameter("action");
        String user = (String) req.getSession().getAttribute("user");

        if ( action == null)
            action = "viewcat";

        //if ( (user != null && !user.equals("")) 
        if ( user != null  
                || action.equals("login") 
                || action.equals("loginvalidate") )
            //proceeds
            chain.doFilter(request, response);
        else {
            req.getSession().setAttribute("user", null);
            RequestDispatcher rd = req.getRequestDispatcher("login_failure.jsp");
            rd.forward(request, response);
        }
    }

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        //this.filterConfig = null;    
    }
    //private FilterConfig filterConfig; }
}