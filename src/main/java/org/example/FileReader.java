package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    static List<String> read(String file){
        System.out.println(file);
        List<String> urls = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
            String line;
            while((line = reader.readLine()) != null){
                urls.add(line);
            }
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }

        return urls;
    }
}
