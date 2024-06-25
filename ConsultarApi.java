import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarApi {
    public Monedas buscarModedas(String moneda, String convertir, double monto) {

        URI direccion = URI.create
                ("https://v6.exchangerate-api.com/v6/6dca235a0a64aa3b87e25d36/pair/"
                        + moneda + "/" + convertir + "/" + monto);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Monedas.class);
        } catch (Exception e) {
            throw new RuntimeException("No encontre ese tipo de moneda");
        }
    }


}


