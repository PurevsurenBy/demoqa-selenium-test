import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private Properties properties;

    public Config() {
        this.properties = new Properties();
        try (InputStream input = new FileInputStream("src/test/resources/config.properties")) {
            this.properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }
}
