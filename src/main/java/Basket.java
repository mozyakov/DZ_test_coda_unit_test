import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;

public class Basket implements Serializable {
    private static final long serialVersionUID = 1L;
    private String[] goods;
    private int[] prices;
    private int[] quantities;

    public Basket() {
    }
    public Basket(String[] goods, int[] prices) {
        this.goods = goods;
        this.prices = prices;
        this.quantities = new int[goods.length];
    }
    public void addToCart(int productNum, int amount) {quantities[productNum] += amount;}

    public void printCart() {
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

    public void saveTxt(File textFile) throws FileNotFoundException {
        try(PrintWriter out = new PrintWriter(textFile)) {
            for (String good : goods) { //проход по товарам
                out.print(good + " ");
            }
            out.println();
            for (int price : prices) { //проход по ценам
                out.print(price + " ");
            }
            out.println();
            for (int quantity : quantities) { //проход по кол-ву
                out.print(quantity + " ");
            }
        }
    }

    public static Basket loadFromTxtFile(File textFile) {
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

    public void saveBin(File file) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Basket loadFromBinFile(File file) {
        Basket basket = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            basket = (Basket) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }

    public void saveJSON(File file) {
        try(PrintWriter writer = new PrintWriter(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); //чтобы разбивать строку на столбики
            String json = gson.toJson(this);
            writer.print(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Basket loadFromJSONFile(File file) {
        Basket basket = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            String line = null;   //важно объявить переменную до первого применения
            while((line = reader.readLine()) != null) {
                builder.append(line); //добавление очередной строки
            }
            Gson gson = new Gson(); //создал объект Gson
            basket = gson.fromJson(builder.toString(), Basket.class);   //де сериализация объекта
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }

}





