import java.util.ArrayList;

public abstract class Veiculo implements ITanque{

    private static final int MAX_ROTAS;
    protected double consumo;
    protected String placa;
    private ArrayList<Rota> rotas;
    protected int quantRotas;
    protected double totalReabastecido;
    protected Tanque tanque;
    protected double kmTotal;

    static {
        MAX_ROTAS = 30;
    }

    public Veiculo(String placa) {
        this.placa = placa;
        this.rotas = new ArrayList<>(MAX_ROTAS);
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
            rotas.add(rota);
            quantRotas++;
            return true;
        } else {
            System.out.println("Limite de rotas atingido para este veículo.");
            return false;
        }
    }

    /**
     * Método que irá zerar todas as rotas do Vieculo, feitas ou que irão ser feitas
     * no mes
     */
    public void zerarRotas() {
        rotas.clear();
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
    public double kmTotal() {
        return kmTotal;
    }

    /**
     * Método para simular a rota de um veículo, a fim de avaliar se a quantidade de
     * combustível atual é capaz de realizar a rota
     * 
     * @param rota rota que será percorrida
     */

    public void percorrerRota(Rota rota) {
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

    public double consumo() {

        return consumo;
    }

    public double totalReabastecido() {
        return totalReabastecido;
    }
    /**
     * Metódo que vai ser implementado nas classes filhas. Vai calcular o gasto total de um veículo
     * levando em consideração as manutenções e combustível utilizado.
     * @param placa placa do veículo
     * @param valorManuPeca valor das peças de manutenção
     * @param valorManuPeriodico valor da manutenção períodioca
     * @return retorna o valor total gasto na manutenção
     */
    public abstract double gastoTotal(double valorManuPeca, double valorManuPeriodico);

}
