package meuProjeto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ConversorComBusca {
    public static void main(String[] args, Object ValorAtual) {
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

        double ars = jsonObject.get("USD_ARS").getAsDouble();
        double bob = jsonObject.get("USD_BOB").getAsDouble();
        double brl = jsonObject.get("USD_BRL").getAsDouble();
        double clp = jsonObject.get("USD_CLP").getAsDouble();
        double cop = jsonObject.get("USD_COP").getAsDouble();
        double usd = jsonObject.get("USD_USD").getAsDouble();

        System.out.println("ARS - Peso argentino: " + ars);
        System.out.println("BOB - Boliviano boliviano: " + bob);
        System.out.println("BRL - Real brasileiro: " + brl);
        System.out.println("CLP - Peso chileno: " + clp);
        System.out.println("COP - Peso colombiano: " + cop);
        System.out.println("USD - Dólar americano: " + usd);
    }
}
