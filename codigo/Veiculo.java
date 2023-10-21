import java.util.Arrays;

public class Veiculo {

    private static final int MAX_ROTAS;
    private static final double CONSUMO;
    private String placa;
    private Rota rotas[];
    private int quantRotas;
    private double tanqueAtual;
    private double tanqueMaximo;
    private double totalReabastecido;

    static {
        MAX_ROTAS = 30;
        CONSUMO = 8.2;
    }

    public Veiculo(String placa, Rota[] rotas, int quantRotas, double tanqueAtual, double tanqueMaximo,
            double totalReabastecido) {
        this.placa = placa;
        this.rotas = rotas;
        this.quantRotas = quantRotas;
        this.tanqueAtual = tanqueAtual;
        this.tanqueMaximo = tanqueMaximo;
        this.totalReabastecido = totalReabastecido;
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
        return tanqueMaximo * CONSUMO;
    }

    /**
     * Método para obter a autonomia atual do veículo
     * 
     * @return retorna a autonomia atual do veículo
     */
    private double autonomiaAtual() {
        return tanqueAtual * CONSUMO;
    }

    /**
     * Método para abastecer o veículo
     * 
     * @param litros quantidade de litros para ser adicinado no tanque do veículo
     * @return retorna a quantidade de litros do tanque após o abastecimento
     */
    public double abastecer(double litros) {

        if (tanqueAtual + litros <= tanqueMaximo) {
            tanqueAtual += litros;
        } else {
            litros = tanqueMaximo - tanqueAtual;
            tanqueAtual = tanqueMaximo;
        }

        totalReabastecido += litros;

        return tanqueAtual;
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
        double quilometragemTotal = 0;
        for (int i = 0; i < quantRotas; i++) {
            quilometragemTotal += rotas[i].getQuilometragem();
        }
        return quilometragemTotal;
    }

    /**
     * Método para simular a rota de um veículo, a fim de avaliar se a quantidade de
     * combustível atual é capaz de realizar a rota
     * 
     * @param rota rota que será percorrida
     */
    private void percorrerRota(Rota rota) {
        if (rota != null) {
            double consumoRota = rota.getQuilometragem() / CONSUMO;
            if (consumoRota <= tanqueAtual) {
                tanqueAtual -= consumoRota;
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
