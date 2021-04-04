import okhttp3.OkHttpClient;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Weather {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .build();

        MediaType JSON = MediaType.parse("JSON");

        String authBodyString = "{" +
                "\"username\": \"maria.yashina.16\", " +
                "\"password\": \"Test12345\"" + "}";
        System.out.println(authBodyString);
        RequestBody authBody = RequestBody.create(authBodyString, JSON);

        Request request = new Request.Builder()
                .url("http://dataservice.accuweather.com/forecasts/v1/daily/5day/295212?apikey=H6ldpZ8Uej2NF2cOGdMmZa5wo1wWr0vq")
                .post(authBody)
                .build();

        Response responseWithToken = client.newCall(request).execute();
        String responseBody = responseWithToken.body().string();

        System.out.println(responseBody);

        String token = responseBody.split(":")[1];
        token = token.replaceAll("[\"}]", "");
        System.out.println(token);
    }

}
