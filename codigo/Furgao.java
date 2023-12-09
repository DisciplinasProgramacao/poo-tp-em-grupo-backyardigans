public class Furgao extends Veiculo {
    private static final int MANUTENCAO_PERIODICA;
    private static final int MANUTENCAO_PECA;

    static {
        MANUTENCAO_PECA = 12000;
        MANUTENCAO_PERIODICA = 10000;
    }

    public Furgao(String placa, int tipoCombustivel) {
        super(placa);
        this.tanque = new Tanque(80, tipoCombustivel);
        this.consumo = tanque.getConsumo();
    }

    @Override
    public String toString() {
        return "Furgao: [placa=" + placa + ", quantRotas=" + quantRotas
        + ", tanqueAtual=" + tanque.capacidadeAtual() + ", tanqueMaximo=" + tanque.capacidadeMaxima() + ", totalReabastecido="
        + totalReabastecido + "]";
    }
}
