package lomtest;
import static spark.Spark.get;
import static spark.Spark.post;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class test {
    static Lomtest lt = new Lomtest();
    public static void main(String[] args){
        DBconnectivity db = new DBconnectivity();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Akhil\", \"age\":22}";
        try{
            db.jdbcCon();
            Student student = mapper.readValue(jsonString, Student.class);
            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
            System.out.print(jsonString);
        }
        catch(Exception e){
            System.out.print(e.getStackTrace());
        }

        System.out.println("hello");
        lt.setLomtestvar(10);
        System.out.println("Lomtestvar"+ lt.getLomtestvar());
        multiSelector();

        get("/getData", (req,res) -> {
            System.out.println("request headers"+req.headers());
            return "go away";
        });
        post("/postData", (req,res) -> {


            System.out.println("request headers"+req.headers()+"instanceOF()"+ req.body().getClass().getName());
//            res.status(401);
            JSONObject json = new JSONObject(req.body());
            if (json.isNull("name")){
                res.status(400);
                System.out.print("name is null");
                return "name is empty";
            }
            else{
            db.insertData(json.getString("name"));
                res.status(200);
            return "Name " + json.getString("name") + "is added in database";
            }
        });
        get("/getData/:rollno", (req,res) -> {
            System.out.println("request headers"+req.headers());
            int rn=Integer.parseInt(req.params(":rollno"));
            return "hello "+ db.getData(rn);
        });


//        pattern("upper",5);
//        pattern("lower",5);
    }

    public static void multiSelector(){
        ArrayList<String> ar = new ArrayList<>();
        ar.add("apple");
        ar.add("mango");
        ar.add("pineapple");
        ar.add("papaya");
        System.out.print("arrayListElement"+ar);
        System.out.print("enter elements ");
    }

    public static void pattern(String s,int n){
//        System.out.print("inside pattern");
        for(int i=0;i<n;i++){
            int k=0;
            if (s.equals("upper")) {
                for(int j=0;j<n-i;j++) {
                    System.out.print(" ");
                }
            }
            else{
                for(int j=0;j<i;j++) {
                    System.out.print(" ");
                }
            }


            if(s.equals("upper")) {
                while (k < (2 * i + 1)) {
                    System.out.print("*");
                    k++;
                }


            }
            else{
                System.out.print(" ");
                while (k < (2 * n -(2*i)-1)) {
                    System.out.print("*");
                    k++;
                }

            }

            System.out.print("\n");
        }
    }

}
