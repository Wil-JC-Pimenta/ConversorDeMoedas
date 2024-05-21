package br.com.model.meuProjeto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class TaxaConversaoService {

    public static Map<String, Double> buscarTaxas() {
        String jsonString = "{\n" +
                "    \"USD_ARS\": 97.04,\n" +
                "    \"USD_BOB\": 6.93,\n" +
                "    \"USD_BRL\": 5.26,\n" +
                "    \"USD_CLP\": 756.02,\n" +
                "    \"USD_COP\": 3834.5,\n" +
                "    \"USD_USD\": 1\n" +
                "}";

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

        Map<String, Double> taxas = new HashMap<>();
        taxas.put("USD/ARS", jsonObject.get("USD_ARS").getAsDouble());
        taxas.put("USD/BOB", jsonObject.get("USD_BOB").getAsDouble());
        taxas.put("USD/BRL", jsonObject.get("USD_BRL").getAsDouble());
        taxas.put("USD/CLP", jsonObject.get("USD_CLP").getAsDouble());
        taxas.put("USD/COP", jsonObject.get("USD_COP").getAsDouble());
        taxas.put("USD/USD", jsonObject.get("USD_USD").getAsDouble());

        return taxas;
    }
}
