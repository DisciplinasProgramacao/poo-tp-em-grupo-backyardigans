import java.text.DecimalFormat;
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
    public Manutencao manutencao;
    private TipoVeiculo tipo;

    static {
        MAX_ROTAS = 30;
    }

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
     * Método para adicionar uma rota ao veículo
     * 
     * @param rota rota a ser adicionada ao veículo
     * @return retorna true caso a rota tenha sido adicionada ou retorna false caso
     *         o limite de rotas tenha sido atingido
     */
    public boolean addRota(Rota rota) {
        if (quantRotas < MAX_ROTAS && rota.getQuilometragem() <= autonomiaMaxima()) {
            rotas.add(rota);
            quantRotas++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que irá zerar todas as rotas do Vieculo, feitas ou que irão ser feitas
     * no mes
     */
    public void zerarRotas() {
        rotas.clear();
        kmTotal = 0;
    }

    /**
     * Método para obter a autonomia máxima do veículo
     * 
     * @return retorna a autonomia máxima do veiculo
     */
    public double autonomiaMaxima() {
        return tanque.autonomiaMaxima();
    }

    /**
     * Método para obter a autonomia atual do veículo
     * 
     * @return retorna a autonomia atual do veículo
     */
    public double autonomiaAtual() {
        return tanque.autonomiaAtual();
    }

    /**
     * Método para abastecer o veículo
     * 
     * @param litros quantidade de litros para ser adicinado no tanque do veículo
     * @return retorna a quantidade de litros do tanque após o abastecimento
     */
    public double abastecer(double litros) {

        if (tanque.capacidadeAtual() + litros <= tanque.capacidadeMaxima()) {
            tanque.abastecer(litros);
            totalReabastecido += litros;
        }

        return tanque.capacidadeAtual();
    }

    /**
     * Método para calcular a quilometragem percorrida pelo veículo no mês com base
     * nas rotas registradas
     * 
     * @return retorna a quilometragem percorrida no mês
     */
    public double kmNoMes() {
        double quilometragemNoMes = 0;
        for (int i = 0; i < quantRotas; i++) {
            quilometragemNoMes += rotas.get(i).getQuilometragem();
        }
        return quilometragemNoMes;
    }

    /**
     * Método para calcular a quilometragem total percorrida pelo veículo com base
     * nas rotas registradas
     * 
     * @return retorna a quilometragem total percorrida
     */
    public double getKmTotal() {
        return kmTotal;
    }

    /**
     * Método para simular a rota de um veículo, a fim de avaliar se a quantidade de
     * combustível atual é capaz de realizar a rota
     * 
     * @param rota rota que será percorrida
     */

    protected void percorrerRota(Rota rota) {
        if (rota != null) {
            double kmRota = rota.getQuilometragem();
            double litros = kmRota / consumo;

            if (kmRota > autonomiaAtual()) {
                abastecer(litros);
            }

            tanque.consumirLitros(litros);
            kmTotal += kmRota;
        }
    }

    /**
     * Método para obter a placa do veículo
     * 
     * @return retorna a placa do veículo
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Método para obter a quantidade de rotas registradas pelo veículo
     * 
     * @return retorna a quantidade de rotas registradas pelo veículo
     */
    public int getQuantRotas() {
        return quantRotas;
    }

    public double getConsumo() {

        return consumo;
    }

    public double getTotalReabastecido() {
        return totalReabastecido;
    }

    public double quantidadeManutencaoPeca() {

        return manutencao.quantidadeManutencaoPeca(kmTotal);
    };

    public double quantidadeManutencaoPeriodica() {

        return manutencao.quantidadeManutencaoPeriodica(kmTotal);
    };

    public double valorCombustivel() {
        return tanque.getPrecoCombustivel();
    }

    public double gastoGasolina() {
        return totalReabastecido * tanque.getPrecoCombustivel();
    }

    @Override
    public String toString() {
        StringBuilder relatorio = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.##");
        relatorio.append("Placa:" + placa + "\n");
        relatorio.append("Tipo do Veiculo: " + tipo + "\n");
        relatorio.append(tanque.toString() + "\n");
        relatorio.append("Km total do Veiculo: " + df.format(kmTotal) + "\n");
        relatorio.append("Quantidade de rotas: " + quantRotas + "\n");
        relatorio.append("Total reabastecido: " + df.format(totalReabastecido) + "\n");
        relatorio.append("Quantidade manutenção peças: " + manutencao.quantidadeManutencaoPeca(kmTotal) + "\n");
        relatorio
                .append("Quantidade manutenção períodica: " + manutencao.quantidadeManutencaoPeriodica(kmTotal) + "\n");
        relatorio.append("Gasto Gasolina: " + df.format(gastoGasolina()));

        return relatorio.toString();
    }

}
