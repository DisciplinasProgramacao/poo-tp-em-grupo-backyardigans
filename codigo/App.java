import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    static Scanner sc;


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

    public static void adicionarVeiculo() throws FileNotFoundException{
        String nomeArq = "veiculos";
        int opcao = -1;
        Veiculo veiculo = null;
        System.out.println("Digite a placa do veículo: ");
        String placa = sc.nextLine();
        System.out.println();
        while (opcao != 0) {
            limparTela();
            opcao = menu(nomeArq);
            switch (opcao) {
                case 1:
                    System.out.println("");
                    break;

                default:
                    System.out.println("teste2");
                    break;
            }        
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
    
        sc = new Scanner(System.in);
        String nomeArq = "menu";
        int opcao = -1;

        while (opcao != 0) {
            limparTela();
            opcao = menu(nomeArq);
            switch (opcao) {
                case 1:
                    adicionarVeiculo();
                    break;

                default:
                    System.out.println("teste2");
                    break;
            }
        }

        sc.close();
    }
}