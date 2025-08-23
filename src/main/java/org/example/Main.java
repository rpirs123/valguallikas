package org.example;


import java.util.List;

public class Main {
    public static void main(String[] args) {


        System.out.println("available processors:");
        System.out.println(Runtime.getRuntime().availableProcessors());
        Main.startScraper();

    }

    static public void startScraper(){
        System.out.println("started");
        List<String> urls = FileReader.read("scraperURLs/mcDonalds.txt");
        System.out.println(urls);
    }
}