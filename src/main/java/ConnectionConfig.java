import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionConfig {
    public static Properties LoadProperties(String resourseFileName) throws IOException {
        Properties configuration = new Properties();
        InputStream inputStream = ConnectionConfig.class
                .getClassLoader()
                .getResourceAsStream(resourseFileName);
        configuration.load(inputStream);
        if (inputStream != null) {
            inputStream.close();
        }
     return configuration;
    }
    public static Connection getConnection() {
        Properties properties;
        Connection connection;
        try {
            properties = LoadProperties("application.properties");
            String user = properties.getProperty("datasourse.username");
            String password = properties.getProperty("datasourse.password");
            String url = properties.getProperty("datasourse.url");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | IOException e) {
            throw new RuntimeException( e);
        }
        return connection;
    }
}

