import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Methods {

    private String extension;
    private String filePath = "src\\main\\resources\\";


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

        byte[] bytes = Files.readAllBytes(Paths.get(path));
        StringBuilder builder =new StringBuilder();

        for(byte b : bytes){
            builder.append(String.format("%02X",b));
        }
        return builder.toString();
    }

    public boolean compareBytes() throws IOException {

        boolean compare = false;
        File file = new File(filePath);
        extension = getFileExtension(filePath);
        ArrayList<String[]> list = reader();

        for(int i = 0; i < list.size(); i++){
            String[] temp = list.get(i);

            if(temp[0].equals(extension)){
                int offset = Integer.parseInt(temp[1]);

            }
        }

    }

    public String getFileExtension(String path){

        if(path.lastIndexOf(".") != -1 && path.lastIndexOf(".") != 0){
            System.out.println(path.substring(path.lastIndexOf(".") + 1));
            extension = path.substring(path.lastIndexOf(".") + 1);
            return extension;
        }
        else return "";
    }

    public void setPath(String path){
        filePath = path;
    }

    public String getPath(){
        return filePath;
    }

}
