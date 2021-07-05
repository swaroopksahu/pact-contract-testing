import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class StockPriceCheck {
    private static int PORT = 8080;
    private static final String baseUrl = "/market";

    StockPriceCheck() {
        // Will use default port.
        System.out.println("Default port " + PORT);
    }

    StockPriceCheck(int port) {
        this.PORT = port;
        System.out.println("Custom port " + PORT);
    }

    public static void main(String[] args) {
        Integer price = new StockPriceCheck().checkPrice("NSE", "ITC");
        System.out.println("price = " + price);
    }

    public Integer checkPrice(String marketName, String stockName) {
        try {
            String url = String.format("http://localhost:%d/market/%s/%s", PORT, marketName, stockName);
            System.out.println("using url: " + url);
            HttpResponse response = Request.Get(url).execute().returnResponse();
            String json = EntityUtils.toString(response.getEntity());
            System.out.println("json=" + json);
            JSONObject jsonObject = new JSONObject(json);
            String price = jsonObject.get("price").toString();
            return new Integer(price);
        } catch (Exception e) {
            System.out.println("Unable to get price, e=" + e);
            return null;
        }
    }
}