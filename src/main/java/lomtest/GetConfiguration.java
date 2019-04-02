package lomtest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.io.InputStream;

import static lomtest.ConfigConstant.*;

public class GetConfiguration {
    String url;
    HashMap<ConfigConstant, String> configMap = new HashMap<ConfigConstant, String>();
    InputStream inputStream;
    public  HashMap<ConfigConstant,String> getConfig() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            configMap.put(DBURL,prop.getProperty("url"));
            configMap.put(USERNAME,prop.getProperty("username"));
            configMap.put(PASSWORD,prop.getProperty("password"));

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return configMap;
    }
}
