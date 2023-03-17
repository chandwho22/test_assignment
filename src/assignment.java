import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class assignment{
    public static void main(String[] args) throws Exception {
        String url = "https://www.staples.com/Computer-office-desks/cat_CL210795/663ea?icid=BTS:2020:STUDYSPACE:DESKS";
        Document doc = Jsoup.connect(url).get();
        Elements products = doc.select(".product");

        FileWriter csvWriter = new FileWriter("top_10_products.csv");
        csvWriter.append("Product Name,Product Price,Item Number/SKU/Product Code,Model Number,Product Category,Product Description\n");

        int count = 0;
        for (Element product : products) {
            String name = product.select(".product-title").text();
            String price = product.select(".final-price").text();
            String itemNumber = product.select(".product-sku").text();
            String modelNumber = product.select(".product-model").text();
            String category = product.select(".breadcrumbs").select("a").last().text();
            String description = product.select(".product-description").text();

            csvWriter.append(name + "," + price + "," + itemNumber + "," + modelNumber + "," + category + "," + description + "\n");

            count++;
            if (count == 10) {
                break;
            }
        }

        csvWriter.flush();
        csvWriter.close();
    }
}
