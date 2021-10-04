package pmj.j2ee.bookstore.dao;

import pmj.j2ee.bookstore.dao.sql.BookDAOImplSQL;
import pmj.j2ee.bookstore.dao.jpa.BookDAOImplJPA;

public class DAOFactory {
    
    public static BookDAO getBookDAO() throws DAOException {

        BookDAO dao = null;
        //read persistance type from config file, and so
        int persistanceType = DAOFactory.JPA;
        
        switch (persistanceType) {
            case DAOFactory.SQL:
            try {
                dao = new BookDAOImplSQL();
            }
            catch(Exception e) {
                throw new DAOException( "Error: constructing SQL DAO!!" );
            }
            break;
            case DAOFactory.JPA:
            try {
                dao = new BookDAOImplJPA();
            }
            catch(Exception e) {
                throw new DAOException( "Error: constructing JPA DAO!!" );
            }
            break;
            case DAOFactory.NOSQL:
                //dao = new BookDAOImplNOSQL();
        }
        return dao;
    }

    private final static int SQL = 1;
    private final static int JPA = 2;
    private final static int NOSQL = 3;

}
