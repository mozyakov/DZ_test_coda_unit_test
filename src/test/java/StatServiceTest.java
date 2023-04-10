import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions.*;


class StatServiceTest {
    @Test                           //всегда начинать с аннотации
    public void testAverage() {     //всегда такие модификаторы доступа ниже три этапа теста
        StatService service = new StatService();      //1 - подготовка
        int[] sales = {1, 2, 3, 4, 5, 6, 7, 8, 9};    //1 - подготовка

        int actual = service.averageSale(sales);           //2 - шаги теста
        int expected = (1+2+3+4+5+6+7+8+9) / sales.length; //2 - шаги теста

        Assertions.assertEquals(expected, actual);    //3 - сравнение ожидаемого с получившемся
    }
}
