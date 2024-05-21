package br.com.service.meuProjeto;

import java.util.Map;

public class ConversorMoeda {

    public static double converterMoeda(double quantia, int escolha, Map<String, Double> taxas) {
        String chaveConversao = obterChaveConversao(escolha);
        Double taxa = taxas.get(chaveConversao);

        // Se a taxa não for encontrada, calcular a taxa inversa
        if (taxa == null) {
            String chaveInversa = obterChaveInversa(chaveConversao);
            Double taxaInversa = taxas.get(chaveInversa);
            if (taxaInversa != null) {
                taxa = 1 / taxaInversa;
            }
        }

        if (taxa == null) {
            return -1; // Opção inválida
        }
        return quantia * taxa;
    }

    private static String obterChaveConversao(int escolha) {
        switch (escolha) {
            case 1:
                return "USD/ARS";
            case 2:
                return "ARS/USD";
            case 3:
                return "USD/BRL";
            case 4:
                return "BRL/USD";
            case 5:
                return "USD/COP";
            case 6:
                return "COP/USD";
            default:
                return "";
        }
    }

    private static String obterChaveInversa(String chaveConversao) {
        String[] moedas = chaveConversao.split("/");
        if (moedas.length == 2) {
            return moedas[1] + "/" + moedas[0];
        }
        return "";
    }
}
