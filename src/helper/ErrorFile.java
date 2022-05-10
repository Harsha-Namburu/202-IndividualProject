package helper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ErrorFile implements OutputFile{
    private ArrayList<String> fileContent;

    @Override
    public void writeToFile(ArrayList<String> content) {
        this.fileContent = content;
    }

    @Override
    public void save(Path path) throws IOException {
        FileWriter errorDetails = new FileWriter(path.getParent().toString()+"/ErrorLog_"+new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".txt");
        for(String line: fileContent)
            errorDetails.write(line+"\n");
        errorDetails.close();
    }
}
