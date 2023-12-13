import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class App {
    static Scanner sc;
    static Frota frota;
    static Random sorteador = new Random(42);
    static DecimalFormat df = new DecimalFormat("R$ ##,#");

    /**
     * Método para limpar a tela do console.
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Método para dar uma pausa para leitura após alguma mensagem no console.
     */
    static void pausa() {
        System.out.println("Enter para continuar.");
        sc.nextLine();
    }

    /**
     * Método para a leitura da entrada texto de teste do trabalho. Leitura do
     * arquivo texto passado como
     * parâmetro e após isso gera também valores aleatórios para complementar as
     * informações de teste.
     * 
     * @param nomeArquivo nome do arquivo que será lido
     * @throws FileNotFoundException
     */
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

            int quantidadeAleatoriaRotas = sorteador.nextInt(30 - 15) + 15;

            for (int i = 0; i < quantidadeAleatoriaRotas; i++) {
                Rota rota = new Rota(kmAleatorio, dataAleatoria);

                vEntrada.addRota(rota);
            }
        }
        //System.out.println(frota.relatorioFrota());
        leitor.close();
    }

    /**
     * Método para gerar quilometragens aleatórias para a base de teste.
     * 
     * @return double contento os quilômetros.
     */
    public static double gerarKmAleatorio() {
        Double km = Math.random() * (1000 - 50) + 50;
        return km;
    }

    /**
     * Método para gerar datas aleatórias para a base de teste.
     * 
     * @return LocalDate com a data.
     */
    public static LocalDate gerarDataAleatoria() {
        int ano = sorteador.nextInt(2024 - 2023) + 2023;

        int mes = sorteador.nextInt(12) + 1;

        int dia = sorteador.nextInt(LocalDate.of(ano, mes, 1).lengthOfMonth()) + 1;

        LocalDate dataAleatoria = LocalDate.of(ano, mes, dia);

        return dataAleatoria;
    }

    /**
     * Método que realiza a construção do menu que será mostrado no console, ele
     * mostra todas
     * as opções disponíveis do arquivo texto e lê a opção escolhida pelo usuário.
     * 
     * @param nomeArquivo nome do arquivo de menu a ser lido.
     * @return inteiro que contém a opção escolhida pelo usuário.
     * @throws FileNotFoundException
     */
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

    /**
     * Método para realizar a adição de um novo veículo no sistema. Há uma leitura
     * de um arquivo
     * texto com as opções de veículos existentes e também a solicitação da placa,
     * o tipo do veículo utilizado e o tipo de combustível.
     * 
     * @throws FileNotFoundException
     */
    public static void adicionarVeiculo() throws FileNotFoundException {
        String nomeArq = "veiculos";
        System.out.println("Digite a placa do veículo: ");
        String placa = sc.nextLine();
        System.out.println("Escolha o tipo de veículo: ");
        int opcaoVeiculo = menu(nomeArq);
        System.out.println((opcaoVeiculo != 0)?"Escolha o tipo do combustivel: ":"Enter para continuar.");

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
        }

    }

    /**
     * Método para pegar o combustível utilizado pelo veículo para o cadastro no
     * sistema. Ele faz
     * a leitura de um arquivo texto com as opções de combustíveis disponíveis e
     * disponibiliza um menu
     * com as opções para o usuário.
     * 
     * @return Retorna uma String com o nome do tipo de combustível utilizado
     * @throws FileNotFoundException
     */
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
        }
        return nomeCombustivel;
    }

    /**
     * Método que localiza um veículo pela placa
     * 
     * @return Retorna o veículo localizado
     */
    public static Veiculo localizarVeiculo() {
        System.out.println("Digite a placa do veículo: ");
        String placa = sc.nextLine();
        Veiculo v = frota.localizarVeiculo(placa);
        if (v == null) {
            System.out.println("Veículo não encontrado!");
        }

        return v;
    }

    public static void verificarTotalReabastecido() {
        Veiculo v = localizarVeiculo();
        if (v != null) {
            System.out.println("O total de gasolina reabastecido pelo veículo é de: " + v.getTotalReabastecido());
        }
    }

    public static void verificarGastoTotalDeUmVeiculo() {

        System.out.println("Digite a placa do veículo que deseja verificar o gasto total: ");
        String placa = sc.nextLine();
        System.out.println("Digite o valor da manutenção da peça: ");
        double valorPeca = sc.nextDouble();
        System.out.println("Digite o valor da manutenção da períodica: ");
        double valorPeriodico = sc.nextDouble();

        System.out.println("O valor total do gasto do veículo foi de: " +
                df.format(frota.gastoTotal(placa, valorPeca, valorPeriodico)));
    }

    public static LocalDate converterData(String data) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormatada = LocalDate.parse(data, formato);

        return dataFormatada;
    }

    public static void adicionarRota() {

        Veiculo veiculo = localizarVeiculo();

        if (veiculo != null) {
            System.out.println("Digite o dia da rota: ");
            String dia = sc.nextLine();
            LocalDate dataCorreta = converterData(dia);

            System.out.println("Digite a quilometragem da rota: ");
            double quilometragem = sc.nextDouble();
            sc.nextLine();

            Rota rota = new Rota(quilometragem, dataCorreta);
            veiculo.addRota(rota);
        }
    }

    public static void verificarQuilometragemDeUmVeiculoNoMes() {
        Veiculo v = localizarVeiculo();

        if (v != null) {
            System.out.println("Digite a data: ");
            String data = sc.nextLine();

            System.out.println("A quilometragem total do no mês foi de " + df.format(v.kmNoMes(converterData(data)))
                    + " quilômetros");
        }

    }

    public static void verificarQuilometragemTotalDeUmVeiculo() {
        Veiculo v = localizarVeiculo();

        if (v != null) {
            System.out.println("A quilometragem total do veículo é de: " + df.format(v.getKmTotal()));
        }

    }

    public static void relatorioDeRotasDeUmVeiculo() {
        Veiculo v = localizarVeiculo();

        if (v != null) {
            System.out.println(v.relatorioRotasVeiculo());
        }

    }

    private static void relatorios() throws FileNotFoundException {
        sc = new Scanner(System.in);
        String nomeArq = "relatorios";
        int opcao = -1;
        while (opcao != 0) {
            limparTela();
            opcao = menu(nomeArq);
            switch (opcao) {
                case 1:
                    verificarGastoTotalDeUmVeiculo();
                    pausa();
                    break;
                case 2:
                    verificarQuilometragemDeUmVeiculoNoMes();
                    pausa();
                    break;
                case 3:
                    verificarQuilometragemTotalDeUmVeiculo();
                    pausa();
                    break;
                case 4:
                    relatorioDeRotasDeUmVeiculo();
                    pausa();
                    break;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        frota = new Frota();
        String nomeArquivoTexto = "entrada";

        sc = new Scanner(System.in);
        String nomeArq = "menu";
        int opcao = -1;
        while (opcao != 0) {
            limparTela();
            opcao = menu(nomeArq);
            switch (opcao) {
                case 1:
                    adicionarVeiculo();
                    pausa();
                    break;

                case 2:
                    adicionarRota();
                    pausa();
                    break;

                case 3:
                    lerEntradaTexto(nomeArquivoTexto);
                    pausa();
                    break;
                case 4:
                    relatorios();
                    pausa();
                    break;
            }
        }
        System.out.println("Sistema fechado!");
        sc.close();
    }
}