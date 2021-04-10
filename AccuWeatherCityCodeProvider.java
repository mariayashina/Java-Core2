import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class AccuWeatherCityCodeProvider implements ICityCodeProvider {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String LOCATIONS_SERVICE_PATH = "locations";
    private static final String API_VERSION = "v1";
    private static final String API_KEY = AppGlobalState.getInstance().getApiKey();

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void getCodeByCityName(String cityName) throws IOException, JsonMappingException {

        HttpUrl detectLocationUrl = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS_SERVICE_PATH)
                .addPathSegment(API_VERSION)
                .addPathSegment("cities")
                .addPathSegment("autocomplete")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", cityName)
                .build();

        Request detectLocationRequest = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(detectLocationUrl)
                .build();

        Response response = client.newCall(detectLocationRequest).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Ошибка сервера\n");
        }

        String jsonBody = response.body().string();

        if (objectMapper.readTree(jsonBody).size() < 1) {
            throw new IOException("Не знаю такого города...\n");
        }

        String cityTitle = objectMapper.readTree(jsonBody).get(0).at("/LocalizedName").asText();
        String countryTitle = objectMapper.readTree(jsonBody).get(0).at("/Country/LocalizedName").asText();

        String cityKey = objectMapper.readTree(jsonBody).get(0).at("/Key").asText();

        System.out.printf("Город %s в стране %s!\n", cityTitle, countryTitle);

        AppGlobalState.getInstance().setCityKey(cityKey);
    }
}