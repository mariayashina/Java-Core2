import java.io.IOException;

public interface ICityCodeProvider {
    void getCodeByCityName(String cityName) throws IOException;
}