public enum TipoVeiculo {
    CARRO(50, 10000, 10000),
    VAN(60, 10000, 10000),
    FURGAO(80, 10000, 10000),
    CAMINHAO(250, 20000, 10000);

    public int tanque;
    public int ManutencaoPeca;
    public int ManutencaoPeriodica;

    TipoVeiculo (int tanque, int ManutencaoPeca, int ManutencaoPeriodica) {
        this.tanque = tanque;
        this.ManutencaoPeca = ManutencaoPeca;
        this.ManutencaoPeriodica = ManutencaoPeriodica;
    }

    public int getTanque() {
        return tanque;
    }

    public int getManutencaoPeca() {
        return ManutencaoPeca;
    }
    public int getManutencaoPeriodica() {
        return ManutencaoPeriodica;
    }
}
