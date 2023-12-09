
public class Manutencao {
    int kmPeca;
    int kmPeriodica;

    public Manutencao(int manutencaoPeriodica, int manutencaoPeca) {
        this.kmPeriodica = manutencaoPeriodica;
        this.kmPeca = manutencaoPeca;
    }

    public int quantManutencaoPeca(int km) {
        return km/kmPeca;

    }

}
