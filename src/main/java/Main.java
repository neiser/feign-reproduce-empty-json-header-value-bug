import feign.Feign;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public class Main {

    public static void main(String[] args) {
        TestClient testClient = Feign.builder().target(TestClient.class, "http://google.com");
        testClient.get(""); // works!
        testClient.get("{\"a\":  \"b\"}"); // works!
        testClient.get("{}"); // fails!
    }


    private interface TestClient {
        @Headers({
            "Some-Header-Key: {someHeaderValue}"
        })
        @RequestLine("GET /")
        void get(@Param("someHeaderValue") String someHeaderValue);
    }
}
