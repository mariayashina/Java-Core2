import java.io.IOException;

public class Controller implements IController {

    ICityCodeProvider codeProvider = new AccuWeatherCityCodeProvider();
    IWeatherProvider weatherProvider = new WeatherProvider();


    @Override
    public void onCityInput(String city) throws IOException {
        if (city.length() == 1) {
            throw new IOException("Не знаю такого города...");
        }
        codeProvider.getCodeByCityName(city);
    }

    @Override
    public void onCommandChosen(int selectedCommand) throws IOException {
        switch (selectedCommand) {
            case 1: {
                weatherProvider.getCurrentWeather(AppGlobalState.getInstance().getCityKey());
                break;
            }
            case 2: {
                weatherProvider.getFiveDaysWeather(AppGlobalState.getInstance().getCityKey());
                break;
            }
            default: {
                throw new IOException("Метод не реализован\n");
            }
        }
    }
}
