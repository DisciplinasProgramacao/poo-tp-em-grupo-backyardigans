public class Van extends Veiculo {
    private static final int MANUTENCAO_PERIODICA;
    private static final int MANUTENCAO_PECA;

    static {
        MANUTENCAO_PECA = 10000;
        MANUTENCAO_PERIODICA = 12000;
    }

    public Van(String placa, int tipoCombustivel) {
        super(placa);
        this.tanque = new Tanque(60, tipoCombustivel);
        this.consumo = tanque.getConsumo();
    }

}
