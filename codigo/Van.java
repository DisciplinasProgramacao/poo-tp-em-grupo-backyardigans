public class Van extends Veiculo {
    private static final int MANUTENCAO_PERIODICA;
    private static final int MANUTENCAO_PECA;

    static {
        MANUTENCAO_PECA = 10000;
        MANUTENCAO_PERIODICA = 12000;
    }

    public Van(String placa) {
        super(placa);
        // TODO Auto-generated constructor stub
    }

}
