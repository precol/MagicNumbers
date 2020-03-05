import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Methods {

    private String extension;
    private String filePath = "src\\main\\resources\\iggy.jpg";
    private String docPath;


    private ArrayList reader(String docPath) throws IOException {

        ArrayList<String[]> list = new ArrayList<>();
        String line = "";
        String[] toList;
        BufferedReader bReader = new BufferedReader(new FileReader(docPath));

        while((line = bReader.readLine()) != null){
            toList =line.split(";");
            list.add(toList);
        }

        return list;
    }

    private String byteGetter(String path) throws IOException {

        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
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

        if(extension.equals("gif") || extension.equals("jpeg") || extension.equals("jpg") || extension.equals("txt")){
            docPath = "src\\main\\resources\\Important.csv";
        }
        else{
            docPath = "src\\main\\resources\\Zeszyt1.csv";
        }

        ArrayList<String[]> list = reader(docPath);

        for(int i = 0; i < list.size(); i++){
            String[] temp = list.get(i);

            if(temp[0].equals(extension)){
                int offset = Integer.parseInt(temp[1]);
                String bytes = byteGetter(filePath);

                for(int j = 2; j < temp.length; j++){
                    if(compare == true) break;

                    for(int k = offset; k < temp[j].length(); k++){
                        Character a = temp[j].charAt(k - offset);
                        Character b = bytes.charAt(k);

                        if(a.equals(b)){
                            if(temp[j].length() + offset -1 == k) compare = true;
                        }
                        else break;
                    }
                }
            }
        }

        return compare;
    }

    private String getFileExtension(String filePath){
        String path = filePath;
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
