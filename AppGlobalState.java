public class AppGlobalState {

    private static AppGlobalState instance;

    private String cityKey;

    public String getCityKey() {
        return cityKey;
    }

    public void setCityKey(String cityKey) {
        this.cityKey = cityKey;
    }

    public String getApiKey() {
        return "jjyfwlEoDWxRceWtERCDBPyHAickSbsZ";
    }

    private AppGlobalState() {
    }

    public static AppGlobalState getInstance() {
        if (instance == null) {
            instance = new AppGlobalState();
        }
        return instance;
    }
}