package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileHelper {
    Path filePath;
    OutputFile newFile;

    private ArrayList<String> contentFile = new ArrayList<>();

    public FileHelper(String pathToFile) {
        this.filePath = Paths.get(pathToFile);
    }

    public void fileReader(boolean firstLine) throws Exception{
        if(Files.exists(filePath)){
            BufferedReader reader = new BufferedReader((new FileReader(filePath.toFile())));
            String line = "";

            while((line=reader.readLine())!=null){
                if(firstLine){
                    firstLine = false;
                    continue;
                }
                contentFile.add(line);
            }
        }else{
            throw new Exception();
        }
    }

    public ArrayList<String> getFile() {
        return contentFile;
    }

    public void writeOutput(ArrayList<String> message, boolean isError) throws IOException{
        if(isError){
            newFile = new ErrorFile();
        }else{
            newFile = new CheckoutFile();
        }
        newFile.writeToFile(message);
        newFile.save(filePath);
    }

}
