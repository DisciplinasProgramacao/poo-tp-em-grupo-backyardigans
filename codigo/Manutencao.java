
public class Manutencao {
    int kmPeca;
    int kmPeriodica;

    public Manutencao(int manutencaoPeriodica, int manutencaoPeca) {
        this.kmPeriodica = manutencaoPeriodica;
        this.kmPeca = manutencaoPeca;
    }

    public int quantidadeManutencaoPeca(int km) {
        return km/kmPeca;
    }

}
