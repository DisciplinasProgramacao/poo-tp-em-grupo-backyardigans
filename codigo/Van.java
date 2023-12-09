public class Van extends Veiculo {
    private static final int MANUTENCAO_PERIODICA;
    private static final int MANUTENCAO_PECA;
    private Manutencao m;

    static {
        MANUTENCAO_PECA = 10000;
        MANUTENCAO_PERIODICA = 12000;
    }

    public Van(String placa, int tipoCombustivel) {
        super(placa);
        this.tanque = new Tanque(60, tipoCombustivel);
        this.consumo = tanque.getConsumo();
        this.m = new Manutencao(MANUTENCAO_PERIODICA, MANUTENCAO_PECA);
    }

    @Override
    public String toString() {
        return "Van [placa=" + placa + ", quantRotas=" + quantRotas
        + ", tanqueAtual=" + tanque.capacidadeAtual() + ", tanqueMaximo=" + tanque.capacidadeMaxima() + ", totalReabastecido="
        + totalReabastecido + "]";
    }

    /**
     * Metódo que vai calcular o gasto total da Van
     * @param placa placa do veículo
     * @param valorManuPeca valor das peças de manutenção
     * @param valorManuPeriodico valor da manutenção períodioca
     * @return retorna o valor total gasto na manutenção
     */
    @Override
    public double gastoTotal(double valorManuPeca, double valorManuPeriodico) {
        double gasto = 0;

        gasto = (totalReabastecido * tanque.getPrecoCombustivel()) + (m.quantidadeManutencaoPeca(kmTotal) * valorManuPeca) + (m.quantidadeManutencaoPeriodica(kmTotal) * valorManuPeriodico);

        return gasto;
    }
}
