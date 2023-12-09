public class Furgao extends Veiculo {
    private static final int MANUTENCAO_PERIODICA;
    private static final int MANUTENCAO_PECA;
    private Manutencao m;

    static {
        MANUTENCAO_PECA = 12000;
        MANUTENCAO_PERIODICA = 10000;
    }

    public Furgao(String placa, int tipoCombustivel) {
        super(placa);
        this.tanque = new Tanque(80, tipoCombustivel);
        this.consumo = tanque.getConsumo();
        this.m = new Manutencao(MANUTENCAO_PERIODICA, MANUTENCAO_PECA);
    }

    @Override
    public String toString() {
        return "Furgao: [placa=" + placa + ", quantRotas=" + quantRotas
        + ", tanqueAtual=" + tanque.capacidadeAtual() + ", tanqueMaximo=" + tanque.capacidadeMaxima() + ", totalReabastecido="
        + totalReabastecido + "]";
    }

    @Override
    public double gastoTotal(String placa, double valorManuPeca, double valorManuPeriodico) {
        double gasto = 0;

        gasto = (totalReabastecido * tanque.getPrecoCombustivel()) + (m.quantidadeManutencaoPeca(kmTotal) * valorManuPeca) + (m.quantidadeManutencaoPeriodica(kmTotal) * valorManuPeriodico);

        return gasto;
    }
}
