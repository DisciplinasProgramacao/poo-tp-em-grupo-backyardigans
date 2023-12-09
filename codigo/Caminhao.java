public class Caminhao extends Veiculo {
    private static final int MANUTENCAO_PERIODICA;
    private static final int MANUTENCAO_PECA;
    private Manutencao m;


    static {
        MANUTENCAO_PECA = 20000;
        MANUTENCAO_PERIODICA = 20000;
    }
    public Caminhao(String placa, int tipoCombustivel) {
        super(placa);
        this.tanque = new Tanque(250, tipoCombustivel);
        this.consumo = getConsumo();
        this.m = new Manutencao(MANUTENCAO_PERIODICA, MANUTENCAO_PECA);
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


    @Override
    public double gastoTotal(String placa, double valorManuPeca, double valorManuPeriodico) {
        double gasto = 0;

        gasto = (totalReabastecido * tanque.getPrecoCombustivel()) + (m.quantidadeManutencaoPeca(kmTotal) * valorManuPeca) + (m.quantidadeManutencaoPeriodica(kmTotal) * valorManuPeriodico);

        return gasto;
    }
    
}
