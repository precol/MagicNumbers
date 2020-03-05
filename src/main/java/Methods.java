import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Methods {

    public ArrayList reader() throws IOException {

        ArrayList<String[]> list = new ArrayList<>();
        String line = "";
        String[] toList;
        BufferedReader bReader = new BufferedReader(new FileReader());

        while((line = bReader.readLine()) != null){
            toList =line.split(";");
            list.add(toList);
        }

        return list;
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
