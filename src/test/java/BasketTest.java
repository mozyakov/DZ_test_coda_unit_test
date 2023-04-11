import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {
    @Test
    public void testAddToBasket() {
        String[] products = {"Хлеб", "Квас", "Мясо"}; //1 - подготовка
        int[] prices = {10, 20, 30};                  //1 - подготовка
        Basket basket = new Basket(products, prices);

        basket.addToCart(0, 1);  //2 - шаги теста, добавляем продукты.
        basket.addToCart(1, 1);
        basket.addToCart(2, 1);

        int[] actual = basket.getQuantities();   //полученный результат
        int[] expected = {1, 1, 1};             //ожидаемый

        Assertions.assertArrayEquals(expected, actual); //3 - сверка ожидаемого с получившемся
    }
    @Test
    public void testAddToCart() {
        int productNum = 0;
        int amount = 0;
        int quantities = 0;

        //int quantities[productNum] += amount;
        //basket.addToCart(0, 1);  //2 - шаги теста, добавляем продукты.




    }


    @Test
    public void testLoadFromTxtFile() {
        Basket basket = Basket.loadFromTxtFile(new File("src/test/resources/test_basket.txt"));
        String[] actualGoods = basket.getGoods();
        String[] expectedGoods = {"Хлеб", "Квас", "Мясо"};
        Assertions.assertArrayEquals(expectedGoods, actualGoods);

        int[] actualPrices = basket.getPrices();
        int[] expectedPrices = {10, 20, 30};
        Assertions.assertArrayEquals(expectedPrices, actualPrices);
    }





}