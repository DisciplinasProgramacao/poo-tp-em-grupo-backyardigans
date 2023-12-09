
public class Manutencao {
    int kmPeca;
    int kmPeriodica;

    public Manutencao(int manutencaoPeriodica, int manutencaoPeca) {
        this.kmPeriodica = manutencaoPeriodica;
        this.kmPeca = manutencaoPeca;
    }

    public int quantidadeManutencaoPeca(double km) {
        return (int) (km/kmPeca);
    }

    public int quantidadeManutencaoPeriodica(double km) {
        return (int) (km/kmPeriodica);
    }

}
