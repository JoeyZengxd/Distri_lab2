package lab1_data;

import lab1_data.Reg_Log;

import java.rmi.RemoteException;
import java.sql.*;

public class Reg_LogImpl implements Reg_Log {
//####################################################
    public int registration(String user, String pasw)           // input username and password, connect to the database and insert the information
            throws RemoteException{
        Statement stmt = null;
        try {
            Connection c = getConnection();         // set up connection with database
            stmt = c.createStatement();
            String sql = "insert into userlist VALUES ('" + user + "', '" + pasw +"');";            // insert the new information to database
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            return 1;           // if the insertion success, then return 1 to show that

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return 0;
    }


    public int login(String user, String pasw)          // input username and password, connect to the database and test the information
            throws RemoteException{
        Statement stmt = null;
        ResultSet rs;
        try {
            Connection c = getConnection();
            stmt = c.createStatement();
            String sql = "select * from userlist where username = '" + user + "';";         // select to see if the login information is correct
            rs = stmt.executeQuery(sql);
            //stmt.close();
            //c.close();
            if(rs.next()) {         // if there exists such user

                String real_pasw = rs.getString("password").toString();

                stmt.close();
                c.close();

                if (real_pasw.equals(pasw)) {
                    return 1;           // password correct, return 1
                }
                else{
                    return -1;          // password wrong, return -1
                }
            }
            else {

                stmt.close();
                c.close();

                return 0;           // no such user, return 0
            }


        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return 0;           //something went wrong, return 0


    }


    public Connection getConnection()throws Exception{          // a normal function to set up connection with database
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:./replacer.sqlite");     // the database is local
            //System.out.println("Opened database successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return c;
    }



}