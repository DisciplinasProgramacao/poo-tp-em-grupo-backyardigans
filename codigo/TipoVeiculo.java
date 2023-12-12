/**
 * Enumerador de tipo de veículo. Cada tipo de veículo possui um valor do tipo int de 
 * capacidade máxima do tanque, um valor do tipo int de quilometragem de manutenção de 
 * peça e um valor do tipo int de quilometragem de manutenção periódica.
 * 
 */
public enum TipoVeiculo {
    CARRO(50, 10000, 10000),
    VAN(60, 10000, 12000),
    FURGAO(80, 10000, 12000),
    CAMINHAO(250, 20000, 20000);

    public int tanque;
    public int ManutencaoPeca;
    public int ManutencaoPeriodica;

    /**
     * Construtor do enumerador TipoVeiculo.
     * @param tanque capacidade máxima do tanque do tipo int.
     * @param ManutencaoPeca quilometragem de manutenção de peça, do tipo int.
     * @param ManutencaoPeriodica quilometragem de manutenção periódica, do tipo int.
     */
    TipoVeiculo (int tanque, int ManutencaoPeca, int ManutencaoPeriodica) {
        this.tanque = tanque;
        this.ManutencaoPeca = ManutencaoPeca;
        this.ManutencaoPeriodica = ManutencaoPeriodica;
    }

    /**
     * Método para obter a capacidade máxima do tanque
     * @return retorna a capacidade máxima do tanque, do tipo int.
     */
    public int getTanque() {
        return tanque;
    }

    /**
     * Método para obter a quilometragem da realização da manutenção
     * de peça.
     * @return retorna a quilometragem da realização da manutenção
     * de peça.
     */
    public int getManutencaoPeca() {
        return ManutencaoPeca;
    }

    /**
     *Método para obter a quilometragem da realização da manutenção
     * periódica.
     * @return retorna a quilometragem da realização da manutenção
     * periódica.
     */
    public int getManutencaoPeriodica() {
        return ManutencaoPeriodica;
    }
}
