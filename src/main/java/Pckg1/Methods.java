import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Methods {

    private String extension;
    private String filePath;
    private String docPath;


    private ArrayList<String[]> reader(String docPath) throws IOException {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String line = "";
        String[] toList;
        BufferedReader bReader = new BufferedReader(new FileReader(docPath));
        while((line = bReader.readLine()) != null){

            toList =line.split(";");
            list.add(toList);
        }

        return list;
    }

    private String byteGetter() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        StringBuilder builder =new StringBuilder();
        for(byte b : bytes){

            builder.append(String.format("%02X",b));
        }
        return builder.toString();
    }

    public boolean mainComparator() throws IOException {
        setPath();
        extension = getFileExtension(filePath);

        if(extension.equals("gif") || extension.equals("jpeg") || extension.equals("jpg") || extension.equals("txt")){
            docPath = "src\\main\\resources\\Important.csv";
        }
        else{
            docPath = "src\\main\\resources\\Zeszyt1.csv";
        }

        ArrayList<String[]> list = reader(docPath);

        return comparator(list);
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

    private void setPath(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set set path to the file you wish to check.");
        filePath = scanner.next();
    }

    private boolean comparator(ArrayList<String[]> list) throws IOException {
        boolean compare = false;
        boolean output = false;

        for(int i = 0; i < list.size(); i++){

            String[] temp = list.get(i);
            if(temp[0].equals(extension)){

                int offset = Integer.parseInt(temp[1]);
                String bytes = byteGetter();
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

                    if(j == temp.length - 1 && compare == false) {
                        output = true;
                        System.out.println(extension + " isn't a " + extension + " file.");
                    }
                }
            }

            if(i == list.size() - 1 && compare == false && output == false) System.out.println("Wrong file extension");
        }

        if(compare == true) System.out.println("File " + extension + " is a " + extension + " file.");

        return compare;
    }

}
