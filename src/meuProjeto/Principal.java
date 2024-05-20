package meuProjeto;

import java.util.Map;
import java.util.Scanner;

import static meuProjeto.TaxaConversaoService.buscarTaxas;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> taxas = buscarTaxas();
        if (taxas == null) {
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
            if (escolha < 1 || escolha > 7) {
                System.out.println("Opção inválida. Tente novamente!");
                continue;
            }

            if (escolha == 7) {
                System.out.println("Saindo...");
                break;
            }

            String descricaoOpcao = obterDescricaoOpcao(escolha);
            System.out.println("Opção escolhida: " + descricaoOpcao);
            System.out.print("Digite a quantia: ");
            double quantia = scanner.nextDouble();
            double quantiaConvertida = ConversorMoeda.converterMoeda(quantia, escolha, taxas);
            if (quantiaConvertida == -1) {
                System.out.println("Opção inválida selecionada.");
            } else {
                String moedaOrigem = obterMoedaOrigem(escolha);
                String moedaDestino = obterMoedaDestino(escolha);
                System.out.printf("Valor: %.2f %s, valor convertido: %.2f %s%n", quantia, moedaOrigem, quantiaConvertida, moedaDestino);
                break;
            }
        }
    }

    private static String obterDescricaoOpcao(int escolha) {
        switch (escolha) {
            case 1:
                return "Dólar ==> Peso argentino";
            case 2:
                return "Peso argentino ==> Dólar";
            case 3:
                return "Dólar ==> Real brasileiro";
            case 4:
                return "Real brasileiro ==> Dólar";
            case 5:
                return "Dólar ==> Peso colombiano";
            case 6:
                return "Peso colombiano ==> Dólar";
            default:
                return "Opção inválida";
        }
    }

    private static String obterMoedaOrigem(int escolha) {
        switch (escolha) {
            case 1:
            case 3:
            case 5:
                return "USD";
            case 2:
                return "ARS";
            case 4:
                return "BRL";
            case 6:
                return "COP";
            default:
                return "";
        }
    }

    private static String obterMoedaDestino(int escolha) {
        switch (escolha) {
            case 1:
                return "ARS";
            case 2:
                return "USD";
            case 3:
                return "BRL";
            case 4:
                return "USD";
            case 5:
                return "COP";
            case 6:
                return "USD";
            default:
                return "";
        }
    }
}
