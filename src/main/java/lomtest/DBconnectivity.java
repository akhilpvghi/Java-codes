package lomtest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DBconnectivity {
    public static void jdbcCon() throws SQLException {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            ResultSet rs = getConnection().createStatement().executeQuery("select * from expt.student");
            while (rs.next()) {
                System.out.print(" "+rs.getString("name")+ "\t\t" + rs.getString("rollno"));
                System.out.println();
            }
            getConnection().close();
            rs.close();
        }
        catch (Exception e){
            System.out.print(" error in sqql"+e);
        }
    }

    public static void insertData(String s) {
        try{
            String query ="INSERT INTO student " + "VALUES ('" +s+ "',90)";
            getConnection().createStatement().executeUpdate(query);
        }catch(Exception e){

        }

    }

    public static String getData(int rollno) {
        try{
            String getQuery="select * from expt.student where rollno="+rollno+"";
            ResultSet rs = getConnection().createStatement().executeQuery(getQuery);
            rs.next();
            return rs.getString("name");
        }
        catch(Exception e){
            System.out.print("error while getting"+e);
            return "0";
        }


    }

    public static Connection getConnection(){
        GetConfiguration gc =new GetConfiguration();
        Connection conn = null;
        try {
             conn = DriverManager.getConnection(gc.getConfig().get(ConfigConstant.DBURL),
                    gc.getConfig().get(ConfigConstant.USERNAME), gc.getConfig().get(ConfigConstant.PASSWORD));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conn;
    }


}
