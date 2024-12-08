package alexa;

import java.io.InputStream;
import java.util.Scanner;

public class ResourceUtil {

    public static String getFileContent(String resourceFilePath) {
        InputStream resourceAsStream = AlexaDriver.class.getResourceAsStream(resourceFilePath);
        if (resourceAsStream == null) {
            throw new IllegalArgumentException("Resource not found: " + resourceFilePath);
        }
        return new Scanner(resourceAsStream, "UTF-8").useDelimiter("\\A").next();
    }
}
