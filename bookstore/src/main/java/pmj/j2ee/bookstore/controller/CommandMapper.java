package pmj.j2ee.bookstore.controller;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;

import pmj.j2ee.util.ObjectCreator;

public class CommandMapper {
	
	
	private CommandMapper() {
		
	}
	
    public static CommandMapper getInstance() {
        if (instance == null )
            instance = new CommandMapper();
        return instance;
    }	
	
    public Action getCommand( String theAction ) 
            throws ServletException, IOException{
        Properties map = new Properties();
        map.load( this.getClass().getClassLoader().getResourceAsStream( ACTION_MAPPING ));
       
        String action_class = map.getProperty(theAction.toLowerCase() );      
        Action action = (Action) ObjectCreator.createObject(action_class);

        return action;
        
    }	

    private static CommandMapper instance;
    private final static String ACTION_MAPPING = "ActionMapping.properties"; 
    
}

//return new ActionMessage(action_class);
