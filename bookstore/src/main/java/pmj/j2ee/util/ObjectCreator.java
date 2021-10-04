package pmj.j2ee.util;

//import pmj.j2ee.bookstore.controller.Action;

public class ObjectCreator {
    public static Object createObject(String class_name) {
        Object obj = null;
        try {
            Class name =  Class.forName( class_name );
            obj = name.newInstance();
        }
        catch(Exception e) { 
            System.err.println("Error");
        }
        return obj;        
    }
}
