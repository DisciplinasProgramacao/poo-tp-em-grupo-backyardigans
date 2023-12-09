public class Carro extends Veiculo {

    private static final int MANUTENCAO_PERIODICA;
    private static final int MANUTENCAO_PECA;
    private Manutencao m;

    static {
        MANUTENCAO_PECA = 10000;
        MANUTENCAO_PERIODICA = 10000;
    }
    public Carro(String placa, int tipoCombustivel) {

            super(placa);
            this.tanque = new Tanque(50, tipoCombustivel);
            this.consumo = tanque.getConsumo();
            m = new Manutencao(MANUTENCAO_PERIODICA, MANUTENCAO_PECA);
    
    }
    
    @Override
    public String toString() {
        return "Carro [placa=" + placa + ", quantRotas=" + quantRotas
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
