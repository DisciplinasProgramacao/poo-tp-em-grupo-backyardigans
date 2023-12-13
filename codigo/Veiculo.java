import java.time.LocalDate;
import java.util.ArrayList;

public class Veiculo {

    private static final int MAX_ROTAS;
    protected double consumo;
    protected String placa;
    protected ArrayList<Rota> rotas;
    protected int quantRotas;
    protected double totalReabastecido;
    protected Tanque tanque;
    protected double kmTotal;
    private Manutencao manutencao;
    private TipoVeiculo tipo;

    static {
        MAX_ROTAS = 30;
    }

    /**
     * Cria um objeto da classe Veiculo.
     * @param placa parâmetro do tipo String, que indica a placa do veículo.
     * @param tipoVeiculo parâemtro do tipo String, que indica o tipo do veículo.
     * @param tipoCombustivel parâmetro do tipo String, que indica o tipo de combustível
     * que o veículo utiliza.
     */
    public Veiculo(String placa, String tipoVeiculo, String tipoCombustivel) {
        this.placa = placa;
        this.rotas = new ArrayList<>(MAX_ROTAS);
        this.quantRotas = 0;
        this.totalReabastecido = 0;
        this.kmTotal = 0;
        this.tipo = TipoVeiculo.valueOf(tipoVeiculo);
        this.tanque = new Tanque(tipo.getTanque(), tipoCombustivel);
        this.manutencao = new Manutencao(tipo.getManutencaoPeriodica(), tipo.getManutencaoPeca());
        this.consumo = tanque.getConsumo();
    }

    /**
     * Método para adicionar uma rota ao veículo, antes de adicionar ele verifica se o 
     * veículo tem autonomia suficiente e se pode realizar a rota.
     * @param rota rota a ser adicionada ao veículo
     * @return retorna true caso a rota tenha sido adicionada ou retorna false caso
     *         o limite de rotas tenha sido atingido
     */
    public boolean addRota(Rota rota) {
        if (quantRotas < MAX_ROTAS && rota.getQuilometragem() <= tanque.autonomiaMaxima()) {
            rotas.add(rota);
            quantRotas++;
            percorrerRota(rota);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para abastecer o veículo.
     * @param litros quantidade de litros para ser adicinado no tanque do veículo, do tipo double
     * @return retorna a quantidade de litros do tanque após o abastecimento, do tipo double.
     */
    public double abastecer(double litros) {

        if (tanque.getCapacidadeAtual() + litros <= tanque.getCapacidadeMaxima()) {
            tanque.abastecer(litros);
            totalReabastecido += litros;
        }

        return tanque.getCapacidadeAtual();
    }

    /**
     * Método para calcular a quilometragem percorrida pelo veículo no mês com base
     * nas rotas registradas.
     * 
     * @return retorna a quilometragem percorrida no mês, do tipo double.
     */
    public double kmNoMes(LocalDate data) {
        double km = 0;

        for(Rota r : rotas){
            if((r.getData().getMonthValue() == data.getMonthValue()) && (r.getData().getYear() == data.getYear())){
                km += r.getQuilometragem();
            }
                
        }
           return km;
    }

    /**
     * Método para percorrer a rota de um veículo, a fim de avaliar se a quantidade de
     * combustível atual é capaz de realizar a rota. É verificado se o carro necessita
     * de manutenção antes de percorrer a rota e se é necessário abastecer.
     * 
     * @param rota rota que será percorrida, do tipo Rota.
     */

    protected void percorrerRota(Rota rota) {
        if (rota != null) {
            double kmRota = rota.getQuilometragem();
            double litros = kmRota / consumo;
            
            if(manutencao.verificarManutencao(kmTotal)){
                manutencao.realizarManutencao(kmTotal);
            }

            if (kmRota > tanque.autonomiaAtual()) {
                abastecer(litros);
            }

            tanque.consumirLitros(litros);
            kmTotal += kmRota;
        }
    }

    /**
     * Método que cálcula e retorna o gasto total de gasolina do veículo.
     */
    public double gastoGasolina() {
        return totalReabastecido * tanque.getPrecoCombustivel();
    }

    /**
     * Relatório de rotas realizadas por um veículo.
     * Retorna uma String com a placa, tipo do veículo e a data e quilometragem da rota.
     */
    public String relatorioRotasVeiculo() {
        StringBuilder relatorio = new StringBuilder();
        
        relatorio.append("Placa:" + placa + "\n");
        relatorio.append("Tipo do Veiculo: " + tipo + "\n");
        rotas.stream().map(Rota::relatorio)
                    .forEach(r -> relatorio.append(r+"\n"));

        return relatorio.toString();
    }

    /**
     * Método que chama o método da classe manutenção para calcular a quantidade
     * de manutenções de peças realizadas por um veículo.
     * @return inteiro com a quantidade de manutenções feitas.
     */
    public int quantidadeManutencaoPeca(){
        return manutencao.getQuantidadeManutencaoPeca();
    }

    /**
     * * Método que chama o método da classe manutenção para calcular a quantidade
     * de manutenções períodicas realizadas por um veículo.
     * @return inteiro com a quantidade de manutenções feitas
     */
    public int quantidadeManutencaoPeriodica(){
        return manutencao.getQuantidadeManutencaoPeriodica();
    }

    /**
     * Método para obter a placa do veículo
     * 
     * @return retorna a placa do veículo, do tipo String
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Método para obter a quantidade de rotas registradas pelo veículo
     * 
     * @return retorna a quantidade de rotas registradas pelo veículo, do tipo int.
     */
    public int getQuantRotas() {
        return quantRotas;
    }

    /**
     * Método para obter o consumo do veículo.
     * @return retorna o consumo do veículo, do tipo double.
     */
    public double getConsumo() {
        return consumo;
    }

    /**
     * Método para obter o total reabastecido.
     * @return retorna o total reabastecido, em double.
     */
    public double getTotalReabastecido() {
        return totalReabastecido;
    }
    
    /**
     * Método para calcular a quilometragem total percorrida pelo veículo com base
     * nas rotas registradas.
     * 
     * @return retorna a quilometragem total percorrida, do tipo double.
     */
    public double getKmTotal() {
        return kmTotal;
    }
}