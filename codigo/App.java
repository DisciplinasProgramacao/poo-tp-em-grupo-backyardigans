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
    static Random sorteador = new Random();
    static DecimalFormat df = new DecimalFormat("#.##");

    /**
     * Main com a iniciação do sistema e chamada de métodos do menu
     */
    public static void main(String[] args) throws FileNotFoundException {
        frota = new Frota();

        geracaoAleatoria();

        sc = new Scanner(System.in);
        String nomeArq = "menu";
        int opcao = -1;
        while (opcao != 0) {
            limparTela();
            opcao = menu(nomeArq);
            switch (opcao) {
                case 1:
                    limparTela();
                    adicionarVeiculo();
                    break;

                case 2:
                    limparTela();
                    adicionarRota();
                    break;
                case 3:
                    limparTela();
                    relatorios();
                    break;
            }
            pausa();
        }
        System.out.println("Sistema fechado!");
        sc.close();
    }

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
        if (opcaoVeiculo != 0) {
            System.out.println("Digite a o número do veículo utilizado: ");
            String opcaoCombustivel = pegarCombustivel();
            if (verificarOpcaoCombustivel(opcaoCombustivel)) {
                switch (opcaoVeiculo) {
                    case 1:
                        Veiculo caminhao = new Veiculo(placa, "CAMINHAO", opcaoCombustivel);
                        frota.adicionarVeiculo(caminhao);
                        break;
                    case 2:
                        Veiculo carro = new Veiculo(placa, "CARRO", opcaoCombustivel);
                        frota.adicionarVeiculo(carro);
                        break;
                    case 3:
                        Veiculo furgao = new Veiculo(placa, "FURGAO", opcaoCombustivel);
                        frota.adicionarVeiculo(furgao);
                        break;
                    case 4:
                        Veiculo van = new Veiculo(placa, "VAN", opcaoCombustivel);
                        frota.adicionarVeiculo(van);
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            }
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
            default:
                System.out.println("Opção inválida");
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

    /**
     * Método que verifica a opção de combustível
     * 
     * @param opcao parâmetro do tipo String que indica a opção
     *              de combustível a ser verificada
     * 
     * @return retorna um boolean,sendo true caso o parâemtro passado
     *         seja igual a ALCOOL, GASOLINA ou DIESEL, e false nos demais casos.
     * 
     */
    public static boolean verificarOpcaoCombustivel(String opcao) {
        if (opcao.equals("ALCOOL") || (opcao.equals("GASOLINA")) || (opcao.equals("DIESEL"))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que verifica o total reabastecido pelo veículo e imprime a informação
     * na tela.
     * 
     */
    public static void verificarTotalReabastecido() {
        Veiculo v = localizarVeiculo();
        if (v != null) {
            System.out.println("O total de gasolina reabastecido pelo veículo é de: "
                    + df.format(v.getTotalReabastecido()) + "litros.");
        }
    }

    /**
     * Método que verifica o gasto total de um veículo. Lê-se do teclado as
     * informações
     * de placa, valor de manutenção de peça e valor de manutenção periódica, e o
     * relatório
     * é impresso na tela através da chamada de método relatorioGastoTotal, passando
     * como
     * parâmetro estas informações.
     * 
     */
    public static void verificarGastoTotalDeUmVeiculo() {

        System.out.println("Digite a placa do veículo que deseja verificar o gasto total: ");
        String placa = sc.nextLine();
        Veiculo v = frota.localizarVeiculo(placa);

        if (v != null) {
            System.out.println("Digite o valor da manutenção da peça: ");
            double valorPeca = sc.nextDouble();
            System.out.println("Digite o valor da manutenção da períodica: ");
            double valorPeriodico = sc.nextDouble();

            System.out.println(frota.relatorioGastoTotal(v, valorPeca, valorPeriodico));
        } else {
            System.out.println("veiculo não encontrado");
        }
        pausa();
    }

    /**
     * Método que formata a data.
     * 
     * @param data data a ser formatada, do tipo String.
     * @return retorna a data formatada, do LocalDate.
     */
    public static LocalDate converterData(String data) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormatada = LocalDate.parse(data, formato);

        return dataFormatada;
    }

    /**
     * Método que adiciona uma nova rota. Caso o veículo
     * seja localizado, lê-se do teclado o dia da rota e a
     * quilometragem da rota. Cria-se um novo objeto do tipo
     * Rota, passando estas informações como parâmetro, e a
     * rota é vinculada ao veículo.
     * 
     */
    public static void adicionarRota() {

        Veiculo veiculo = localizarVeiculo();

        if (veiculo != null) {
            System.out.println("Digite o dia da rota: dd/mm/yyyy");
            String dia = sc.nextLine();
            LocalDate dataCorreta = converterData(dia);

            System.out.println("Digite a quilometragem da rota: ");
            double quilometragem = sc.nextDouble();
            sc.nextLine();

            Rota rota = new Rota(quilometragem, dataCorreta);
            boolean resultado = veiculo.addRota(rota);
            System.out.println(resultado ? "Rota adicionada para o veículo com sucesso!"
                    : "Não foi possível adicionar a rota ao veículo!");
        }
    }

    /**
     * Método que verifica a quilometragem de um veículo no mês.
     * Caso o veículo seja localizado, lê-se do teclado a data e
     * é impresso na tela a quilometragem total do mês.
     * 
     */
    public static void verificarQuilometragemDeUmVeiculoNoMes() {
        Veiculo v = localizarVeiculo();

        if (v != null) {

            System.out.println("A quilometragem total do no mês foi de " + df.format(v.kmNoMes())
                    + " quilômetros");
        } else {
            System.out.println("veiculo não encontrado!");
        }

    }

    /**
     * Método que verifica a quilometragem total de um veículo.
     * Caso o veículo seja localizado, é impresso na tela a sua
     * quilometragem total e quantidade de rotas.
     * 
     */
    public static void verificarQuilometragemTotalDeUmVeiculo() {
        Veiculo v = localizarVeiculo();

        if (v != null) {
            System.out.println("Quilometragem total: " + v.getKmTotal());
        }

    }

    /**
     * Método que mostra o relatório de rotas de um veículo.
     * Caso o veículo seja localizado, o relatório é impresso
     * na tela.
     * 
     */
    public static void relatorioDeRotasDeUmVeiculo() {
        Veiculo v = localizarVeiculo();

        if (v != null) {
            System.out.println(v.relatorioRotasVeiculo());
        }

    }

    /**
     * Submenu que permite o usuário selecionar qual relatório
     * quer ver, a partir do arquivo "relatorios".
     * 
     * @throws FileNotFoundException caso o arquivo "relatorios"
     *                               não exista, é lançada a exceção.
     * 
     */
    private static void relatorios() throws FileNotFoundException {
        sc = new Scanner(System.in);
        String nomeArq = "relatorios";
        int opcao = -1;
        limparTela();
        while (opcao != 0) {
            limparTela();
            opcao = menu(nomeArq);
            switch (opcao) {
                case 1:
                    limparTela();
                    verificarGastoTotalDeUmVeiculo();
                    break;
                case 2:
                    limparTela();
                    verificarQuilometragemDeUmVeiculoNoMes();
                    break;
                case 3:
                    limparTela();
                    verificarQuilometragemTotalDeUmVeiculo();
                    break;
                case 4:
                    limparTela();
                    relatorioDeRotasDeUmVeiculo();
                    break;
                case 5:
                    limparTela();
                    zerarRotas();
                    break;
            }
            pausa();
        }
    }

    private static void zerarRotas() {
        frota.zerarRotas();
        System.out.println("as rotas dos veiculos foram descartas!");
        pausa();
    }

    /**
     * função para gerar a base de dados dos veiculos aleatorios
     * 
     * @throws FileNotFoundException
     */
    private static void geracaoAleatoria() throws FileNotFoundException {
        String nomeArquivoTexto = "entrada";
        lerEntradaVeiculos(nomeArquivoTexto);
        for (int i = 0; i < 2; i++) {
            gerarRotasAleatorias();
            frota.zerarRotas();
        }

        gerarRotasAleatorias();
    }

    public static void gerarRotasAleatorias() {
        for (Veiculo v : frota.veiculos) {
                int quantidadeAleatoriaRotas = sorteador.nextInt((50 - 20) + 1) + 20;

                for (int j = 0; j < quantidadeAleatoriaRotas; j++) {
                    Rota rota = new Rota(gerarKmAleatorio(), gerarDataAleatoria());
                    v.addRota(rota);
                }
            }

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
    public static void lerEntradaVeiculos(String nomeArquivo) throws FileNotFoundException {

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
        }

        leitor.close();
    }

    /**
     * Método para gerar quilometragens aleatórias para a base de teste.
     * 
     * @return double contento os quilômetros.
     */
    public static double gerarKmAleatorio() {
        Double km = ((Math.random() * (300 - 100)) + 100);

        return km;
    }

    /**
     * Método para gerar datas aleatórias para a base de teste.
     * 
     * @return LocalDate com a data.
     */
    public static LocalDate gerarDataAleatoria() {
        int ano = sorteador.nextInt(2024 - 2023) + 2023;

        int mes = 12;

        int dia = sorteador.nextInt(LocalDate.of(ano, mes, 1).lengthOfMonth()) + 1;

        LocalDate dataAleatoria = LocalDate.of(ano, mes, dia);

        return dataAleatoria;
    }
}