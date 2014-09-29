package student;

import java.sql.*;

final class GetConnection {

    /**
     * Uses DriverManager.
     */
    static Connection getSimpleConnection() {
        //See your driver documentation for the proper format of this string :
        String DB_CONN_STRING = "jdbc:mysql://localhost:3306/registrar";
        //Provided by your driver documentation. In this case, a MySql driver is used : 
        String DRIVER_CLASS_NAME = "org.gjt.mm.mysql.Driver";
        String USER_NAME = "abt1s";
        String PASSWORD = "abt1s";
                

        Connection result = null;
        try {
            Class.forName(DRIVER_CLASS_NAME).newInstance();
        } catch (Exception ex) {
            log("Check classpath. Cannot load db driver: " + DRIVER_CLASS_NAME);
        }

        try {
            result = DriverManager.getConnection(DB_CONN_STRING, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            log("Driver loaded, but cannot connect to db: " + DB_CONN_STRING);
        }
        return result;
    }

    private static void log(Object aObject) {
        System.out.println(aObject);
    }
}
