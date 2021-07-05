package controller;

import beans.StockInfo;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
public class StockController {
    private static final String baseUrl = "/market";

    @RequestMapping(baseUrl + "/{marketName}/{stockName}")
    public StockInfo stockInfo(@PathVariable String marketName, @PathVariable String stockName){
        int price = getPrice();
        StockInfo stockInfo = new StockInfo(marketName, stockName, price);
        return stockInfo;
    }

    private int getPrice(){
        Random random = new Random();
        int min = 500;
        int max = 600;
        return random.nextInt(max-min+1) + min;
    }
}
