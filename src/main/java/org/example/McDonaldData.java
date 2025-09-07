package org.example;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class McDonaldData {

    static public void run() {
        List<Product> products = new ArrayList<>();
        List<String> urls = FileReader.read("scraperURLs/mcDonalds.txt");
        ListIterator iterator = urls.listIterator();

        while (iterator.hasNext()) {
            products = McDonaldData.findProducts(iterator.next());

        }
        System.out.println("products" + products);
        Product.writeProductsToJson(products, "mcDonalds");
    }

    public static List<Product> findProducts(Object url) {
        List <Product> products = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url.toString()).get();
            Elements productHref = doc.select(".entry-title").select("a[href]");
            productHref.stream().limit(5).forEach(href -> {
               products.add(McDonaldData.findNutrition(href.attr("href")));
            });
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
        return products;
    }

    public static Product findNutrition(String url) {

        System.out.println(url);
        try {
            Document doc = Jsoup.connect(url).get();
            Elements NutritionValuesTableRow = doc.select(".nutritional-information-table")
                    .select(".detailed")
                    .select(".detailed-row");

            String name = doc.selectFirst("h1").text();
            try {
                String kcal = NutritionValuesTableRow.asList().get(2).child(2).text() + "kcal";
                String fat = NutritionValuesTableRow.asList().get(3).child(2).text()+ "g";
                String cb = NutritionValuesTableRow.asList().get(5).child(2).text()+ "g";
                String sugars = NutritionValuesTableRow.asList().get(6).child(2).text()+ "g";
                String protein = NutritionValuesTableRow.asList().get(8).child(2).text() + "g";
                String salt = NutritionValuesTableRow.asList().get(9).child(2).text()+ "g";

                return new Product(name,kcal,fat,cb,sugars,protein,salt);
            }
            catch ( IndexOutOfBoundsException e) {
                // ignore indexoutofbounds error
            }

        } catch (IOException e) {
            System.out.println("Error " + e);
        }

        return null;
    }
}
