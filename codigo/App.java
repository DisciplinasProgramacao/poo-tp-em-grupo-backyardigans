import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class App {
    static Scanner sc;
    static Frota frota;
    static Random sorteador = new Random(42);
    static int mes = 12;
    static int ano = 2023;

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausa() {
        System.out.println("Enter para continuar.");
        sc.nextLine();
    }

    public static void lerEntradaTexto(String nomeArquivo) throws FileNotFoundException {
        

        File arquivo = new File(nomeArquivo);

        Scanner leitor = new Scanner(arquivo, "UTF-8");
        String[] info = new String[4];
        while (leitor.hasNextLine()) {
            String linha = leitor.nextLine();

            info = linha.split(";");
            String placa = info[0];
            String tipoVeiculo = info[1];
            String tipoCombustivel = info[2];
            Veiculo vEntrada = new Veiculo(placa, tipoVeiculo, tipoCombustivel);

            frota.adicionarVeiculo(vEntrada);

            double kmAleatorio = gerarKmAleatorio();

            LocalDate dataAleatoria = gerarDataAleatoria();

            int quantidadeAleatoriaRotas = sorteador.nextInt(30-15)+15;

            for(int i = 0; i<quantidadeAleatoriaRotas; i++){
                Rota rota = new Rota(kmAleatorio, dataAleatoria);

                vEntrada.addRota(rota);
            }
            vEntrada.rotas.forEach(r -> vEntrada.percorrerRota(r));
        }

        System.out.println(frota.toString());
        leitor.close();
    }

    public static double gerarKmAleatorio(){
            Double km = Math.random() * (1000 - 50) + 50;
            return km;
    }

    public static LocalDate gerarDataAleatoria(){
        int ano = sorteador.nextInt(2024 - 2023) + 2023;

        int mes = sorteador.nextInt(12) + 1;

        int dia = sorteador.nextInt(LocalDate.of(ano, mes, 1).lengthOfMonth()) + 1;

        LocalDate dataAleatoria = LocalDate.of(ano, mes, dia);

        return dataAleatoria;
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
        System.out.println("Digite a placa do veículo: ");
        String placa = sc.nextLine();
        System.out.println("Escolha o tipo de veículo: ");
        int opcaoVeiculo = menu(nomeArq);
        System.out.println("Escolha o tipo do combustivel: ");

        switch (opcaoVeiculo) {
            case 1:
                String opcaoCombustivel = pegarCombustivel();
                Veiculo caminhao = new Veiculo(placa, "CAMINHAO", opcaoCombustivel);
                frota.adicionarVeiculo(caminhao);
                break;
            case 2:
                opcaoCombustivel = pegarCombustivel();
                Veiculo carro = new Veiculo(placa, "CARRO", opcaoCombustivel);
                frota.adicionarVeiculo(carro);
                break;
            case 3:
                opcaoCombustivel = pegarCombustivel();
                Veiculo furgao = new Veiculo(placa, "FURGAO", opcaoCombustivel);
                frota.adicionarVeiculo(furgao);
                break;
            case 4:
                opcaoCombustivel = pegarCombustivel();
                Veiculo van = new Veiculo(placa, "VAN", opcaoCombustivel);
                frota.adicionarVeiculo(van);
                break;

            default:
                break;
        }

    }

    private static String pegarCombustivel() throws FileNotFoundException {
        sc = new Scanner(System.in);
        String nomeArq = "combustiveis";
        int opcao;
        String nomeCombustivel = new String();
        limparTela();
        opcao = menu(nomeArq);
        switch (opcao) {
            case 1:
                nomeCombustivel = "ALCOOL";
                break;
            case 2:
                nomeCombustivel = "DIESEL";
                break;
            case 3:
                nomeCombustivel = "GASOLINA";
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
        return nomeCombustivel;
    }

    public static Veiculo localizarVeiculo() {
        System.out.println("Digite a placa do veículo que deseja verificar o gasto total: ");
        String placa = sc.nextLine();
        Veiculo v = frota.localizarVeiculo(placa);

        return v;
    }

    public static void verificarGastoTotalDeUmVeiculo() {

        System.out.println("Digite a placa do veículo que deseja verificar o gasto total: ");
        String placa = sc.nextLine();
        System.out.println("Digite o valor da manutenção da peça: ");
        double valorPeca = sc.nextDouble();
        System.out.println("Digite o valor da manutenção da períodica: ");
        double valorPeriodico = sc.nextDouble();

        System.out.println("O valor total do gasto do veículo foi: R$" +
                frota.gastoTotal(placa, valorPeca, valorPeriodico));
    }

    public static LocalDate converterData(String data) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormatada = LocalDate.parse(data+"/"+mes+"/"+ano, formato);
        
        return dataFormatada;
    }

    public static void adicionarRota() {

        Veiculo veiculo = localizarVeiculo();

        System.out.println("Digite o dia da rota: ");
        String dia = sc.nextLine();
        LocalDate dataCorreta = converterData(dia);

        System.out.println("Digite a quilometragem da rota: ");
        double quilometragem = sc.nextDouble();
        sc.nextLine();

        Rota rota = new Rota(quilometragem, dataCorreta);

        veiculo.addRota(rota);
        System.out.println("Rota adicionada com sucesso!");
    }

    public static void verificarQuilometragemDeUmVeiculo() {
        Veiculo v = localizarVeiculo();
        //v.kmNoMes(12);
        System.out.println("A quilometragem total do veículo de placa "+v.getPlaca()+" no mês ");
    }

    public static void verificarQuilometragemTotalDeUmVeiculo() {
        Veiculo v = localizarVeiculo();

        System.out.println("A quilometragem total do veículo de placa "+v.getPlaca()+" é de: "+v.getKmTotal());
    }

    public static void relatorioDeRotasDeUmVeiculo() {
    }

    public static void main(String[] args) throws FileNotFoundException {
        frota = new Frota();
        String nomeArquivoTexto = "entrada";
        lerEntradaTexto(nomeArquivoTexto);

        // sc = new Scanner(System.in);
        // String nomeArq = "menu";
        // int opcao = -1;
        // while (opcao != 0) {
        // limparTela();
        // opcao = menu(nomeArq);
        // switch (opcao) {
        // case 1:
        // adicionarVeiculo();
        // break;

        // case 2:
        // adicionarRota();
        // break;

        // case 3:
        // verificarGastoTotalDeUmVeiculo();
        // break;

        // case 4:
        // verificarQuilometragemDeUmVeiculo();
        // break;

        // case 5:
        // verificarQuilometragemTotalDeUmVeiculo();
        // break;

        // case 6:
        // relatorioDeRotasDeUmVeiculo();
        // break;

        // default:
        // System.out.println("teste2");
        // break;
        // }
        // }

        // sc.close();
    }
}