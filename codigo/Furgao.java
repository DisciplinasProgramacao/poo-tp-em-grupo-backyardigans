public class Furgao extends Veiculo {
    private static final int MANUTENCAO_PERIODICA;
    private static final int MANUTENCAO_PECA;
    private Manutencao m;

    static {
        MANUTENCAO_PECA = 12000;
        MANUTENCAO_PERIODICA = 10000;
    }

    public Furgao(String placa, Combustivel tipoCombustivel) {
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

    /**
     * Metódo que vai calcular o gasto total da Furgão
     * @param placa placa do veículo
     * @param valorManuPeca valor das peças de manutenção
     * @param valorManuPeriodico valor da manutenção períodioca
     * @return retorna o valor total gasto na manutenção */
    @Override
    public double gastoTotal(double valorManuPeca, double valorManuPeriodico) {
        double gasto = 0;

        gasto = (totalReabastecido * tanque.getPrecoCombustivel()) + (m.quantidadeManutencaoPeca(kmTotal) * valorManuPeca) + (m.quantidadeManutencaoPeriodica(kmTotal) * valorManuPeriodico);

        return gasto;
    }
}
