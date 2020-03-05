import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Methods {

    public ArrayList reader(){

    }

    public String byteGetter(String path) throws IOException {
        String filePath = path;
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        StringBuilder builder =new StringBuilder();

        for(byte b : bytes){
            builder.append(String.format("%02X",b));
        }
        return builder.toString();
    }

    public boolean compareBytes(){

    }

    public String getFileExtension(){

    }

}
