package lomtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class DBconnectivity {
    public static void jdbcCon() throws SQLException {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.print("check db connectivity");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/expt",
                    "root", "root");

            ResultSet rs = conn.createStatement().executeQuery("select * from expt.student");
//        ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
//            for ( int i=1; i <= rsmd.getColumnCount(); i++){;
                System.out.print(" "+rs.getString("name")+ "\t\t" + rs.getString("rollno"));
//            }

                System.out.println();
            }
            conn.close();
//            statementObj.close();
            rs.close();
//            System.out.print("check db connectivity" + rs);
        }
        catch (Exception e){
            System.out.print(" error in sqql"+e);
        }
    }

    public static void insertData(String s) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/expt",
                    "root", "root");
            String query ="INSERT INTO student " + "VALUES ('" +s+ "',20)";
            conn.createStatement().executeUpdate(query);
        }catch(Exception e){

        }

    }

    public static String getData(int rollno) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/expt",
                    "root", "root");
            String getQuery="select * from expt.student where rollno="+rollno+"";
            ResultSet rs = conn.createStatement().executeQuery(getQuery);

//            String query ="INSERT INTO student " + "VALUES ('" +s+ "',20)";
//            conn.createStatement().executeUpdate(query);
            rs.next();
            return rs.getString("name");
        }
        catch(Exception e){
            System.out.print("error while getting"+e);
            return "0";
        }


    }


}
