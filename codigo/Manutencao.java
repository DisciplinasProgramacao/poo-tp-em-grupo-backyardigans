
public class Manutencao {
    int kmPeca;
    int kmPeriodica;

    /**
     * Construtor da classe manutenção.
     * @param manutencaoPeriodica parâmetro do tipo int que indica a quilometragem em que 
     * é feita a manutenção periódica.
     * @param manutencaoPeca parâmetro do tipo int que indica a quilometragem em que 
     * é feita a manutenção de peça.
     */
    public Manutencao(int manutencaoPeriodica, int manutencaoPeca) {
        this.kmPeriodica = manutencaoPeriodica;
        this.kmPeca = manutencaoPeca;
    }

    /**
     * Método que calcula a quantidade de manutenção de peças realizadas.
     * @param km parâmetro do tipo double que determina a quilometragem rodada do veículo.
     * @return retorna a quantidade de manutenção de peças realizadas, obtida pela divisão
     * entre a quilometragem do carro e a quilometragem em que é feita a manutenção de peça.
     */
    public int quantidadeManutencaoPeca(double km) {
        return (int) (km/kmPeca);
    }

    /**
     * Método que calcula a quantidade de manutenção periódicas realizadas.
     * @param km parâmetro do tipo double que determina a quilometragem rodada do veículo.
     * @return retorna a quantidade de manutenção de peças periódicas realizadas, obtida pela divisão
     * entre a quilometragem do carro e a quilometragem em que é feita a manutenção periódica.
     */
    public int quantidadeManutencaoPeriodica(double km) {
        return (int) (km/kmPeriodica);
    }

}
