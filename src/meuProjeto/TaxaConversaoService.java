package meuProjeto;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class TaxaConversaoService {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/c969eba07a254061f800ef0d/latest/USD";

    public static Map<String, Double> buscarTaxas() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return null;
            }

            String responseBody = response.body();

            // Salvar a resposta JSON em um arquivo
            try (FileWriter file = new FileWriter("taxasConversao.json")) {
                file.write(responseBody);
            } catch (IOException e) {
                e.printStackTrace();
            }

            JsonObject jsonobj = JsonParser.parseString(responseBody).getAsJsonObject();

            if (!"success".equals(jsonobj.get("result").getAsString())) {
                return null;
            }

            JsonObject ratesObject = jsonobj.getAsJsonObject("conversion_rates");
            Map<String, Double> taxas = new HashMap<>();
            taxas.put("USD", 1.0);
            taxas.put("ARS", ratesObject.get("ARS").getAsDouble());
            taxas.put("BRL", ratesObject.get("BRL").getAsDouble());
            taxas.put("COP", ratesObject.get("COP").getAsDouble());

            return taxas;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
