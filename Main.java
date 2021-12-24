import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String args[]) throws UnsupportedEncodingException, IOException {
        String filePath = "/Users/dmikhalchuk/development/java_labs/src/lab6/task.txt";

        FileHelper helper = new FileHelper(filePath);
        helper.runApp();
    }
}

