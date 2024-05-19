package meuProjeto;


import java.util.Map;

public class ConversorMoeda {
    public static double converterMoeda(double quantia, int escolha, Map<String, Double> taxas) {
        switch (escolha) {
            case 1:
                return quantia * taxas.get("ARS");
            case 2:
                return quantia / taxas.get("ARS");
            case 3:
                return quantia * taxas.get("BRL");
            case 4:
                return quantia / taxas.get("BRL");
            case 5:
                return quantia * taxas.get("COP");
            case 6:
                return quantia / taxas.get("COP");
            default:
                return -1;
        }
    }
}
