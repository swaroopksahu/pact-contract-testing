package beans;

public class StockInfo {
    String marketName;
    String stockName;
    int price;

    public StockInfo(String marketName, String stockName, Integer price) {
        this.marketName = marketName;
        this.stockName = stockName;
        this.price = price;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
