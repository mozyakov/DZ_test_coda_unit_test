import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.File;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[] products = {"Хлеб", "Квас", "Мясо"};
    static int[] prices = {10, 20, 30};

    static File saveFile = new File("basket.json");

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        XMLSettingsReader settings = new XMLSettingsReader(new File("shop.xml"));
        File loadFile = new File(settings.loadFile);
        File saveFile = new File(settings.saveFile);
        File logFile = new File(settings.logFile);


        Basket basket = createBasket(loadFile, settings.isLoad, settings.loadFormat);
        ClientLog log = new ClientLog();

        while(true) {
            System.out.println("Список возможных товаров для покупки");
            for (int i = 0; i < products.length; i++) {
                System.out.println(products[i]  + " " + prices[i] + "руб/шт");
            }
            System.out.println("Выбери товар и через пробел выбери его кол-во или введи 'end'");
            String input = scanner.nextLine(); //юзер вводит данные
            if ("end".equals(input)) {
                if(settings.isLog) {
                    log.exportAsCSV(logFile);
                }
                break;
            }
            String[] parts = input.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1; //номер товара
            int productCount = Integer.parseInt(parts[1]); //кол-во товара
            basket.addToCart(productNumber, productCount);
            if(settings.isLog) {
                log.log(productNumber, productCount);
            }
            if(settings.isSave) {
                switch (settings.saveFormat) {
                    case "json" -> basket.saveJSON(saveFile);
                    case "txt" -> basket.saveTxt(saveFile);
                }
            }
        }
        basket.printCart();
    }

    private static Basket createBasket(File loadFile, boolean isLoad, String loadFormat) {
        Basket basket;   //создан объект переменная где будет храниться результат
        if(isLoad && loadFile.exists()) {   //проверка необходимости загрузки файла и есть ли он вообще
            switch (loadFormat) {
                case "json":
                    basket = Basket.loadFromJSONFile(loadFile);
                    break;
                case "txt":
                    basket = Basket.loadFromTxtFile(loadFile);
                    break;
                default:
                    basket = new Basket(products, prices);
            }
        } else {
            basket = new Basket(products, prices);
        }
        return basket;
    }

}
