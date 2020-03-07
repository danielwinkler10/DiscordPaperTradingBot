package stock;

import org.patriques.AlphaVantageConnector;
import org.patriques.TimeSeries;
import org.patriques.input.timeseries.Interval;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.Daily;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.data.StockData;
import util.ReadToken;

import java.util.List;

public class StockUtil {

    private String apiKey = ReadToken.getStockToken();
    private int timeout = 3000;
    private AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
    private TimeSeries stockTimeSeries = new TimeSeries(apiConnector);

    public void getDailyData(String symbol){
        try{
            Daily response = stockTimeSeries.daily(symbol);

            List<StockData> stockData = response.getStockData();
            stockData.forEach(stock -> {
                System.out.println("date:   " + stock.getDateTime());
                System.out.println("open:   " + stock.getOpen());
                System.out.println("high:   " + stock.getHigh());
                System.out.println("low:    " + stock.getLow());
                System.out.println("close:  " + stock.getClose());
                System.out.println("volume: " + stock.getVolume());
            });
        } catch (AlphaVantageException e) {
            System.out.println("something went wrong");
        }
    }

    public double getStockValue(String symbol){

        try{
            IntraDay response = stockTimeSeries.intraDay(symbol, Interval.ONE_MIN);
            List<StockData> stockData = response.getStockData();
            return stockData.get(0).getClose();

        } catch (AlphaVantageException e) {
            System.out.println("something went wrong");
            return 0.0;
        }
    }

}
