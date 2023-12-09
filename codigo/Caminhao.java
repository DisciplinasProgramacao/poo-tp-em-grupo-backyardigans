public class Caminhao extends Veiculo {
    private static final int MANUTENCAO_PERIODICA;
    private static final int MANUTENCAO_PECA;



    static {
        MANUTENCAO_PECA = 20000;
        MANUTENCAO_PERIODICA = 20000;
    }
    public Caminhao(String placa, int tipoCombustivel) {
        super(placa);
        this.tanque = new Tanque(250, tipoCombustivel);
        this.consumo = getConsumo();
    }


    private double getConsumo() {
        return tanque.getConsumo();
    }

    @Override
    public String toString() {
        return "Caminhão [placa=" + placa + ", quantRotas=" + quantRotas
        + ", tanqueAtual=" + tanque.capacidadeAtual() + ", tanqueMaximo=" + tanque.capacidadeMaxima() + ", totalReabastecido="
        + totalReabastecido + "]";
    }
    
}
