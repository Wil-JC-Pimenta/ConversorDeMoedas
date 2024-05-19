package meuProjeto;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Map;
import java.util.Scanner;

import static meuProjeto.TaxaConversaoService.*;

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

            System.out.print("Digite a quantia: ");
            double quantia = scanner.nextDouble();
            double quantiaConvertida = ConversorMoeda.converterMoeda(quantia, escolha, taxas);
            if (quantiaConvertida == -1) {
                System.out.println("Opção inválida selecionada.");
            } else {
                System.out.printf("Quantia convertida: %.2f%n", quantiaConvertida);
            }
        }
    }
}
