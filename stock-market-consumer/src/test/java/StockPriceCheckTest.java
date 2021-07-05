import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class StockPriceCheckTest {
    private static final int PORT = 8080;
    String baseUrl = "/market";


    @Rule
    public PactProviderRuleMk2 provider =
            new PactProviderRuleMk2("StockService_Provider", "localhost", PORT, this);

    @Pact(consumer = "StockMarket_Consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap();
        headers.put("Content-Type", "application/json");


        DslPart priceResults = new PactDslJsonBody()
                .stringType("marketName","NSE")
                .stringType("stockName","ITC")
                .integerType("price",502)
                .asBody();

        return builder
                .given("This is ITC stock listed in NSE")
                .uponReceiving("A request to get the price for stock ITC from NSE.")
                .path(baseUrl + "/NSE/ITC")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(priceResults).toPact();

    }

    @Test
    @PactVerification()
    public void doTest() {
        System.setProperty("pact.rootDir","../pacts");  // Change output dir for generated pact-files
        Integer price = new StockPriceCheck(provider.getPort()).checkPrice("NSE", "ITC");
        System.out.println("According to test price="+price);
        assertTrue(price >= 500);
    }

}