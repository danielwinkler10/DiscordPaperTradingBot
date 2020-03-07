package stock;

public class Stock {
    private String symbol;

    private double price;
    private int amount;

    public Stock( String symbol, double price, int amount){
        this.symbol = symbol;
        this.price = price;
        this.amount = amount;
    }
}
