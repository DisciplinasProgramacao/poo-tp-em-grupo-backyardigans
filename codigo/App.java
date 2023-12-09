import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    static Scanner sc;
    static Frota frota;

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int menu(String nomeArquivo) throws FileNotFoundException {
        limparTela();
        File arqMenu = new File(nomeArquivo);
        Scanner leitor = new Scanner(arqMenu, "UTF-8");
        System.out.println(leitor.nextLine());
        System.out.println("=====================================");
        int contador = 1;
        while (leitor.hasNextLine()) {
            System.out.println(contador + " - " + leitor.nextLine());
            contador++;
        }
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(sc.nextLine());
        leitor.close();
        return opcao;
    }

    public static void adicionarVeiculo() throws FileNotFoundException {
        String nomeArq = "veiculos";
        System.out.println("Digite a placa do veículo:");
        String placa = sc.nextLine();
        System.out.println("Escolha o tipo de veículo:");
        int opcaoVeiculo = menu(nomeArq);
        // System.out.println("Escolha o tipo do combustivel:");

        switch (opcaoVeiculo) {
            case 1:
                
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;

            default:
                break;
        }

    }

    // public static void adicionarVeiculo() throws FileNotFoundException {
    // String nomeArq = "veiculos";
    // int opcao;
    // System.out.println("Digite a placa do veículo: ");
    // String placa = sc.nextLine();
    // System.out.println("Escolha o tipo de veículo: ");

    // do {
    // limparTela();
    // opcao = menu(nomeArq);
    // String combustivel = pegarCombustivel();
    // if (combustivel.equals(null)) {
    // switch (opcao) {
    // case 1:
    // Veiculo caminhao = new Veiculo(placa, "CAMINHAO", combustivel);
    // frota.adicionarVeiculo(caminhao);
    // break;
    // case 2:

    // Veiculo carro = new Veiculo(placa, "CARRO", combustivel);

    // frota.adicionarVeiculo(carro);

    // break;
    // case 3:

    // Veiculo furgao = new Veiculo(placa, "FURGAO", combustivel);

    // frota.adicionarVeiculo(furgao);

    // break;
    // case 4:

    // Veiculo van = new Veiculo(placa, "VAN", combustivel);

    // frota.adicionarVeiculo(van);

    // break;
    // default:
    // System.out.println("teste2");
    // break;
    // }

    // }
    // } while (opcao != 0);
    // }

    // private static String pegarCombustivel() throws FileNotFoundException {
    // sc = new Scanner(System.in);
    // String nomeArq = "combustiveis";
    // int opcao;
    // String nomeCombustivel = new String();
    // limparTela();
    // opcao = menu(nomeArq);
    // switch (opcao) {
    // case 1:
    // nomeCombustivel = "ALCOOL";
    // break;
    // case 2:
    // nomeCombustivel = "DIESEL";
    // break;
    // case 3:
    // nomeCombustivel = "GASOLINA";
    // break;
    // case 0:
    // break;
    // default:
    // System.out.println("Opção inválida");
    // break;
    // }
    // return nomeCombustivel;
    // }

    public static void verificarGastoTotalDeUmVeiculo() {

        System.out.println("Digite a placa do veículo que deseja verificar o gasto total: ");
        String placa = sc.nextLine();
        System.out.println("Digite o valor da manutenção da peça: ");
        double valorPeca = sc.nextDouble();
        System.out.println("Digite o valor da manutenção da períodica: ");
        double valorPeriodico = sc.nextDouble();
        // Frota f = new Frota();
        // Veiculo v = f.localizarVeiculo(placa);

        // System.out.println("O valor total do gasto do veículo foi: R$" +
        // v.gastoTotal(valorPeca, valorPeriodico));
    }

    public static void adicionarRota() {
    }

    public static void verificarQuilometragemDeUmVeiculo() {
    }

    public static void verificarQuilometragemTotalDeUmVeiculo() {
    }

    public static void relatorioDeRotasDeUmVeiculo() {
    }

    public static void main(String[] args) throws FileNotFoundException {

        sc = new Scanner(System.in);
        String nomeArq = "menu";
        int opcao = -1;
        frota = new Frota();
        while (opcao != 0) {
            limparTela();
            opcao = menu(nomeArq);
            switch (opcao) {
                case 1:
                    adicionarVeiculo();
                    break;

                case 2:
                    adicionarRota();
                    break;

                case 3:
                    verificarGastoTotalDeUmVeiculo();
                    break;

                case 4:
                    verificarQuilometragemDeUmVeiculo();
                    break;

                case 5:
                    verificarQuilometragemTotalDeUmVeiculo();
                    break;

                case 6:
                    relatorioDeRotasDeUmVeiculo();
                    break;

                default:
                    System.out.println("teste2");
                    break;
            }
        }

        sc.close();
    }
}