import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

        int[] actual = basket.getQuantities();
        int[] expected = {1, 1, 1};

        Assertions.assertArrayEquals(expected, actual); //3 - сверка ожидаемого с получившемся

    }

}