public class Carro extends Veiculo {

    private static final int MANUTENCAO_PERIODICA;
    private static final int MANUTENCAO_PECA;
    private Manutencao manutencaoCarro;



    static {
        MANUTENCAO_PECA = 10000;
        MANUTENCAO_PERIODICA = 10000;
    }
    public Carro(String placa, int tipoCombustivel) {

            super(placa);
            this.tanque = new Tanque(50, tipoCombustivel);
            this.consumo = tanque.getConsumo();
            this.manutencaoCarro = new Manutencao(MANUTENCAO_PERIODICA, MANUTENCAO_PECA);
    
    }
    
    @Override
    public String toString() {
        return "Carro [placa=" + placa + ", quantRotas=" + quantRotas
        + ", tanqueAtual=" + tanque.capacidadeAtual() + ", tanqueMaximo=" + tanque.capacidadeMaxima() + ", totalReabastecido="
        + totalReabastecido + "]";
    }
}
