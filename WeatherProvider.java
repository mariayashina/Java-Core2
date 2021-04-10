import java.io.IOException;

public class WeatherProvider implements IWeatherProvider{
    String city;
    String weatherType;
    String humidity;
    int temperature;

    public String toString() {
        return "Weather[city= " + city + ", weatherType=" + weatherType
                + ", temperature= " + temperature + ",humidity= " + humidity
                + "]";
    }

    @Override
    public void getCurrentWeather(String cityKey) throws IOException {
    }

    @Override
    public void getFiveDaysWeather(String cityKey) throws IOException {

    }
}