import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/c969eba07a254061f800ef0d/latest/USD";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> rates = buscarTaxas();
        if (rates == null) {
            System.out.println("Falha ao buscar taxas de câmbio. Saindo.");
            return;
        }

        while (true) {
            System.out.println("\n**********************************");
            System.out.println("Seja bem-vindo/a ao Conversor de Moeda");
            System.out.println("1) Dólar ==> Peso argentino");
            System.out.println("2) Peso argentino ==> Dólar");
            System.out.println("3) Dólar ==> Real brasileiro");
            System.out.println("4) Real brasileiro ==> Dólar");
            System.out.println("5) Dólar ==> Peso colombiano");
            System.out.println("6) Peso colombiano ==> Dólar");
            System.out.println("7) Sair");
            System.out.println("**********************************");
            System.out.print("Escolha uma opção válida: ");
            int escolha = scanner.nextInt();
            if(escolha < 1 || escolha > 7){
                System.out.println("Opção inválida. Tente novamente!");
                break;

            }
            if (escolha == 7) {
                System.out.println("Saindo...");
                break;
            }

            System.out.print("Digite a quantia: ");
            double quantia = scanner.nextDouble();
            double quantiaConvertida = converterMoeda(quantia, escolha, rates);
            if (quantiaConvertida == -1) {
                System.out.println("Opção inválida selecionada.");
            } else {
                System.out.printf("Quantia convertida: %.2f%n", quantiaConvertida);
            }
        }
    }

    private static Map<String, Double> buscarTaxas() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return null;
            }

            JsonObject jsonobj = JsonParser.parseString(response.body()).getAsJsonObject();

            if (!"success".equals(jsonobj.get("result").getAsString())) {
                return null;
            }

            JsonObject ratesObject = jsonobj.getAsJsonObject("conversion_rates");
            Map<String, Double> rates = new HashMap<>();
            rates.put("USD", 1.0);
            rates.put("ARS", ratesObject.get("ARS").getAsDouble());
            rates.put("BRL", ratesObject.get("BRL").getAsDouble());
            rates.put("COP", ratesObject.get("COP").getAsDouble());

            return rates;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static double converterMoeda(double quantia, int escolha, Map<String, Double> rates) {
        switch (escolha) {
            case 1:
                return quantia * rates.get("ARS");
            case 2:
                return quantia / rates.get("ARS");
            case 3:
                return quantia * rates.get("BRL");
            case 4:
                return quantia / rates.get("BRL");
            case 5:
                return quantia * rates.get("COP");
            case 6:
                return quantia / rates.get("COP");
            default:
                return -1;
        }
    }
}
