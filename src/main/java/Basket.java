import java.io.*;
import java.util.Arrays;

public class Basket {
    private String[] goods;
    private int[] prices;
    private int[] quantities;

    public String[] getGoods() {
        return goods;
    }

    public int[] getPrices() {
        return prices;
    }

    public int[] getQuantities() {
        return quantities;
    }

    public Basket() {
    }

    public Basket(String[] goods, int[] prices) {
        this.goods = goods;
        this.prices = prices;
        this.quantities = new int[goods.length];
    }
    public int averagePriceAssortment() { //средняя цена представленного ассортимента
        int totalPrice = 0;
        for (int price : prices) {
            totalPrice += price;
        }
        return totalPrice / prices.length;
    }

    public void addToCart(int productNum, int amount) { //метод добавления amount(штук продукта) номер productNum в корзину;
        quantities[productNum] += amount;}

    public void printCart() { //метод вывода на экран покупательской корзины.
        int totalPrice = 0;
        System.out.println("Список покупок:");
        for(int i = 0; i < goods.length; i ++) {
            if(quantities[i] > 0) {
                int currentPrice = prices[i] * quantities[i];
                totalPrice += currentPrice;
                System.out.printf("%15s%4d p/шт%4d шт%6d p%n", goods[i], prices[i], quantities[i], currentPrice);
            }
        }
        System.out.printf("Итого: %dp", totalPrice);
    }

    public void saveTxtFile(File textFile) throws FileNotFoundException { //метод сохранения корзины в текстовый файл;
        try(PrintWriter out = new PrintWriter(textFile)) {
            //for (String good : goods) { //проход по товарам  ниже вариант без циклов for each
            //    out.print(good + " ");
            //}
            //out.println();
            //for (int price : prices) { //проход по ценам
            //    out.print(price + " ");
            //}
            //out.println();
            //for (int quantity : quantities) { //проход по кол-ву
            //    out.print(quantity + " ");
            //}

            out.println(String.join(" ", goods)); //собрать строку, записать её в файл

            out.println(String.join(" ", Arrays.stream(prices) //преобразовать массив интов в массив строк
                    .mapToObj(String::valueOf) //строки переводим в числа
                    .toArray(String[]::new))); //конструктор массива строк
            out.println(String.join(" ", Arrays.stream(quantities)
                    .mapToObj(String::valueOf)
                    .toArray(String[]::new)));
        }
    }

    public static Basket loadFromTxtFile(File textFile) { //статический(!) метод восстановления объекта корзины из текстового файла
        Basket basket = new Basket();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {
            String goodsStr = bufferedReader.readLine();
            String pricesStr = bufferedReader.readLine();
            String quantitiesStr = bufferedReader.readLine();

            basket.goods = goodsStr.split(" ");
            basket.prices = Arrays.stream(pricesStr.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
            basket.quantities = Arrays.stream(quantitiesStr.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }


}





