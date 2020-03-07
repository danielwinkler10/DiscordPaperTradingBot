package player;

import net.dv8tion.jda.api.entities.User;
import stock.Stock;
import stock.StockUtil;

import java.util.HashMap;

public class DiscordUser {

    private double balance;
    private User user;

    private StockUtil utils = new StockUtil();

    public HashMap<String, Stock> stocks = new HashMap<>();

    public boolean buyStock(String symbol, int amount){
        try {
            double stockValue = utils.getStockValue(symbol);
            if (stockValue > 0.0) {
                if ((stockValue * amount) <= balance) {
                    Stock s = new Stock(symbol, stockValue, amount);
                    stocks.put(symbol, s);
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // ------- //

    public DiscordUser(User author){
        user = author;
    }

    public void setBalance(double bal){
        balance = bal;
    }

    public void reduceBalance(double amout){
        balance = balance - amout;
    }

    public void addBalance(double amout){
        balance = balance + amout;
    }

    public double getBalance(){
        return balance;
    }

    public String getId() {
        return user.getId();
    }


}

