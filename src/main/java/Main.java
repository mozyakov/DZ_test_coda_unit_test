import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[] products = {"Хлеб", "Квас", "Мясо"};
    static int[] prices = {10, 20, 30};

    static File saveFile = new File("basket.txt");

    public static void main(String[] args) throws FileNotFoundException {
        Basket basket = null;
        if (saveFile.exists()) {
            basket = Basket.loadFromTxtFile(saveFile);
        } else {
            basket = new Basket(products, prices);
        }

        while(true) {
            System.out.println("Список возможных товаров для покупки");
            for (int i = 0; i < products.length; i++) {
                System.out.println(products[i]  + " " + prices[i] + "руб/шт");
            }
            System.out.println("Выбери товар и через пробел выбери его кол-во или введи 'end'");
            String input = scanner.nextLine(); //юзер вводит данные
            if ("end".equals(input)) {
                break;
            }
            String[] parts = input.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1; //номер товара
            int productCount = Integer.parseInt(parts[1]); //кол-во товара
            basket.addToCart(productNumber, productCount);
            basket.saveTxtFile(saveFile);
        }
        basket.printCart();
        System.out.println();
        System.out.println("Средняя цена всего представленного ассортимента = " + basket.averagePrice());
    }


}
