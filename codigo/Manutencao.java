public class Manutencao {
    private double kmPeca;
    private double kmPeriodica;
    private int quantidadeManutencaoPeca;
    private int quantidadeManutencaoPeriodica;

    /**
     * Construtor da classe manutenção.
     * @param manutencaoPeriodica parâmetro do tipo int que indica a quilometragem em que 
     * é feita a manutenção periódica.
     * @param manutencaoPeca parâmetro do tipo int que indica a quilometragem em que 
     * é feita a manutenção de peça.
     */
    public Manutencao(double manutencaoPeriodica, double manutencaoPeca) {
        this.kmPeriodica = manutencaoPeriodica;
        this.kmPeca = manutencaoPeca;
        this.quantidadeManutencaoPeca = 0;
        this.quantidadeManutencaoPeriodica = 0;
    }

    public boolean verificarManutencao(double kmTotalAtual){
        
        if((kmTotalAtual/kmPeca) > quantidadeManutencaoPeca || (kmTotalAtual/kmPeriodica) > quantidadeManutencaoPeriodica){
            return true;
        }
        else{
            return false;
        }
    }

    public void realizarManutencao(double kmTotalAtual){
        
        if(quantidadeManutencaoPeca < (kmTotalAtual/kmPeca)){
            quantidadeManutencaoPeca++;
        }
        if(quantidadeManutencaoPeriodica < (kmTotalAtual/kmPeriodica)){
            quantidadeManutencaoPeriodica++;
        }
    }
    
    public int getQuantidadeManutencaoPeca(){
        return quantidadeManutencaoPeca;
    }

    public int getQuantidadeManutencaoPeriodica(){
        return quantidadeManutencaoPeriodica;
    }
}