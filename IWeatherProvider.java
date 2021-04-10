import java.io.IOException;

public interface IWeatherProvider {

    void getCurrentWeather(String cityKey) throws IOException;
    void getFiveDaysWeather(String cityKey) throws IOException;
}