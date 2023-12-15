import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class Veiculo {

    private static final int MAX_ROTAS;
    private double consumo;
    private String placa;
    private ArrayList<Rota> rotas;
    private double totalReabastecido;
    private Tanque tanque;
    private double kmTotal;
    private Manutencao manutencao;
    private TipoVeiculo tipo;
    private  int quantidadeMPecasAntiga;
    private  int quantidadeMPeriodicaAntiga;
    private  double valorAntigoMPecas;
    private  double valorAntigoMPeriodica;
    DecimalFormat df = new DecimalFormat("#.##");

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
        this.totalReabastecido = 0;
        this.kmTotal = 0;
        this.tipo = TipoVeiculo.valueOf(tipoVeiculo);
        this.tanque = new Tanque(tipo.getTanque(), tipoCombustivel);
        this.manutencao = new Manutencao(tipo.getManutencaoPeriodica(), tipo.getManutencaoPeca());
        this.consumo = tanque.getConsumo();
        this.quantidadeMPeriodicaAntiga = 0;
        this.quantidadeMPecasAntiga = 0;
        this.valorAntigoMPecas = 0;
        this.valorAntigoMPeriodica = 0;
    }

    /**
     * Método para adicionar uma rota ao veículo, antes de adicionar ele verifica se o 
     * veículo tem autonomia suficiente e se pode realizar a rota.
     * @param rota rota a ser adicionada ao veículo
     * @return retorna true caso a rota tenha sido adicionada ou retorna false caso
     *         o limite de rotas tenha sido atingido
     */
    public boolean addRota(Rota rota) {
        if (rotas.size() < MAX_ROTAS && rota.getQuilometragem() <= tanque.autonomiaMaxima()) {
            rotas.add(rota);
            rotas.sort(Comparator.comparing(Rota::getData));
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
    public double kmNoMes() {
        double km = 0;

        for(Rota r : rotas){
            km += r.getQuilometragem();
                
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

    public void percorrerRota(Rota rota) {
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
     * Relatório do veículo com todas as rotas atualizadas.
     * Retorna uma String com a placa, tipo do veículo, tanque e a data e quilometragem da rota.
     */
    public String relatorioVeiculo() {
        StringBuilder relatorio = new StringBuilder();
        
        relatorio.append("Placa:" + placa + "\n");
        relatorio.append("Tipo do Veiculo: " + tipo + "\n");
        relatorio.append(tanque.toString() + "\n");
        relatorio.append("Quantidade de rotas realizadas: "+rotas.size()+"\n");
        relatorio.append("Total reabastecido: "+df.format(totalReabastecido)+" litros\n");
        for(Rota r : rotas){
            relatorio.append(r.relatorio()+ "\n");
        }

        return relatorio.toString();
    }

    /**
     * Método que calcula o gasto total de determinado veículo, considerando os
     * valores de manutenção
     * periodica, manutenção de peças e gastos em combustível, de acordo com a
     * quantidade de recorrência de
     * cada. 
     * 
     * @param valorMPeca      parâmetro do tipo double que indica o valor da
     *                        manutenção de peça.
     * 
     * @param valorMPeriodico parâmetro do tipo double que indica o valor da
     *                        manutenção periódica.
     * 
     * @return retorna o gasto total, obtido pela soma do gastos totais com
     *         combustível, manutenção periodica
     *         e manutenção de peças
     */
 
    public String relatorioGastoTotal(double valorMPeca, double valorMPeriodico) {

        StringBuilder str = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.##");

        int quantidadeAtualPeca = manutencao.getQuantidadeManutencaoPeca();
        int quantidadeAtualPeriodica = manutencao.getQuantidadeManutencaoPeriodica();

        if(manutencao.getQuantidadeManutencaoPeca() > 0){
            quantidadeAtualPeca = manutencao.getQuantidadeManutencaoPeca() - quantidadeMPecasAntiga;
        }

        if(manutencao.getQuantidadeManutencaoPeriodica() > 0){
            quantidadeAtualPeriodica = manutencao.getQuantidadeManutencaoPeriodica() -  quantidadeMPeriodicaAntiga;
        }

        double gastoEmCombustivel = totalReabastecido * tanque.getPrecoCombustivel();
        double valorManutencaoPeriodica = (quantidadeAtualPeriodica * valorMPeriodico);
        double valorManutencaoPeca = (quantidadeAtualPeca * valorMPeca);

        str.append("O gasto total do veículo de placa " + placa + " foi: ");

        str.append("\nQuantidade de manutenção periódica: " + manutencao.getQuantidadeManutencaoPeriodica() + " - Valor: R$"
                + df.format((valorManutencaoPeriodica+valorAntigoMPeriodica)));

        str.append("\nQuantidade de manutenção em peças: " + manutencao.getQuantidadeManutencaoPeca() + " - Valor: R$"
                + df.format((valorManutencaoPeca+valorAntigoMPecas)));

        str.append("\nTotal reabastecido pelo carro: " + df.format(totalReabastecido)+" litros"+ " - Valor: R$"
                + df.format(gastoEmCombustivel));

        str.append("\nValor total gasto: R$"
                + df.format(gastoEmCombustivel + (valorManutencaoPeca+valorAntigoMPecas) + (valorManutencaoPeriodica+valorAntigoMPeriodica)));
 
        if(manutencao.getQuantidadeManutencaoPeca()>0){
            valorAntigoMPecas += valorManutencaoPeca;
        }
        if(manutencao.getQuantidadeManutencaoPeriodica()>0){
            valorAntigoMPeriodica += valorMPeriodico;
        }

        quantidadeMPecasAntiga = manutencao.getQuantidadeManutencaoPeca();
        quantidadeMPeriodicaAntiga = manutencao.getQuantidadeManutencaoPeriodica();
            return str.toString();
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
        return rotas.size();
    }

    /**
     * Método para obter o total reabastecido.
     * @return retorna o total reabastecido, em double.
     */
    public double getTotalReabastecido() {
        return totalReabastecido;
    }
    
    /**
     * Método para obter a quilometragem total percorrida pelo veículo.
     * @return retorna a quilometragem total percorrida.
     */
    public double getKmTotal(){
        return kmTotal;
    }

    /**
     * get retornando o tipo do veiculo
     * @return retorna o nome do tipo do veiculo em string.
     */
    public String getTipo() {
        return tipo.name();
    }

    public Tanque getTanque(){
        return tanque;
    }

    public ArrayList<Rota> getRotas(){
        return rotas;
    }
}