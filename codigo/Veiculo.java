import java.util.Arrays;

public abstract class Veiculo implements IManutencao {

    private static final int MAX_ROTAS;
    protected double consumo;
    private String placa;
    private Rota rotas[];
    private int quantRotas;
    private double totalReabastecido;
    protected Tanque tanque;
    protected double kmTotal;
    static {
        MAX_ROTAS = 30;
    }

    public Veiculo(String placa) {
        this.placa = placa;
        this.rotas = new Rota[30];
        this.quantRotas = 0;
        this.totalReabastecido = 0;
        this.kmTotal = 0;
    }

    /**
     * Método para adicionar uma rota ao veículo
     * 
     * @param rota rota a ser adicionada ao veículo
     * @return retorna true caso a rota tenha sido adicionada ou retorna false caso
     *         o limite de rotas tenha sido atingido
     */
    public boolean addRota(Rota rota) {
        if (quantRotas < MAX_ROTAS) {
            rotas[quantRotas] = rota;
            quantRotas++;
            return true;
        } else {
            System.out.println("Limite de rotas atingido para este veículo.");
            return false;
        }
    }

    /**
     * Método para obter a autonomia máxima do veículo
     * 
     * @return retorna a autonomia máxima do vepiculo
     */
    private double autonomiaMaxima() {
        return tanque.autonomiaMaxima();
    }

    /**
     * Método para obter a autonomia atual do veículo
     * 
     * @return retorna a autonomia atual do veículo
     */
    private double autonomiaAtual() {
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
        } else {
            litros = tanque.capacidadeMaxima() - tanque.capacidadeAtual();
            tanque.abastecer(litros);
        }

        totalReabastecido = totalReabastecido + litros;

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
            quilometragemNoMes += rotas[i].getQuilometragem();
        }
        return quilometragemNoMes;
    }

    /**
     * Método para calcular a quilometragem total percorrida pelo veículo com base
     * nas rotas registradas
     * 
     * @return retorna a quilometragem total percorrida
     */
    public double kmTotal() {

        return kmTotal;
    }

    /**
     * Método para simular a rota de um veículo, a fim de avaliar se a quantidade de
     * combustível atual é capaz de realizar a rota
     * 
     * @param rota rota que será percorrida
     */

    
    private void percorrerRota(Rota rota) {
        if (rota != null) {
            double kmRota = rota.getQuilometragem();
            if (kmRota <= tanque.autonomiaAtual()) {
                tanqueAtual -= kmRota;
            } else {
                System.out.println("Não há combustível suficiente para percorrer esta rota.");
            }
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

    @Override
    public String toString() {
        return "Veiculo [placa=" + placa + ", rotas=" + Arrays.toString(rotas) + ", quantRotas=" + quantRotas
                + ", tanqueAtual=" + tanqueAtual + ", tanqueMaximo=" + tanqueMaximo + ", totalReabastecido="
                + totalReabastecido + "]";
    }

}
