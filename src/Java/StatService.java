public class StatService {
    public int averageSale(int[] sales) {
        int totalPrice = 0;
        for (int sale : sales) {
            totalPrice += sale;
        }
        return totalPrice / sales.length;
    }
}
