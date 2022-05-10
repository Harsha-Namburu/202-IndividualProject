package helper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CheckoutFile implements OutputFile{
    private ArrayList<String> fileContent;

    @Override
    public void writeToFile(ArrayList<String> content) {
        this.fileContent = content;
    }

    @Override
    public void save(Path path) throws IOException {
        FileWriter newFile = new FileWriter(path.getParent().toString()+"/OrderSuccessfulFile_"+new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".csv");
        for(String line: fileContent)
            newFile.write(line+"\n");
        newFile.close();
    }
}
