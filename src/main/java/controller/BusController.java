package controller;

import beans.BusInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class BusController {

    @RequestMapping("/bus/{station}/{nr}")
    public BusInfo bus(@PathVariable String station, @PathVariable String nr){
        int eta = getEta();
        BusInfo b = new BusInfo(station, nr, eta);
        return b;
    }

    private int getEta(){
        Random rn = new Random();
        int min = 0;
        int max = 5;
        return rn.nextInt(max-min+1) + min;
    }
}
