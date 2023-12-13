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

    /**
     * Método para verificar se um veículo necessita de manutenção. Verifica a partir da quantidade de
     * manutenções realizadas e quantas são necessárias para o veículo a partir dos quilometros rodados.
     * @param kmTotalAtual
     * @return true caso seja necessário, false caso não seja necessário
     */
    public boolean verificarManutencao(double kmTotalAtual){
        
        if((kmTotalAtual/kmPeca) > quantidadeManutencaoPeca || (kmTotalAtual/kmPeriodica) > quantidadeManutencaoPeriodica){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Método que realiza a manutenção no veículo, quantifica quantas manutenções foram
     * realizadas
     * @param kmTotalAtual
     */
    public void realizarManutencao(double kmTotalAtual){
        
        if(quantidadeManutencaoPeca < (kmTotalAtual/kmPeca)){
            quantidadeManutencaoPeca++;
        }
        if(quantidadeManutencaoPeriodica < (kmTotalAtual/kmPeriodica)){
            quantidadeManutencaoPeriodica++;
        }
    }
    
    /**
     * Método para retornar o valor de quantidade de manutenção de peças
     * @return
     */
    public int getQuantidadeManutencaoPeca(){
        return quantidadeManutencaoPeca;
    }

    /**
     * Método para retornar o valor de quantidade de manutenção de peças
     * @return
     */
    public int getQuantidadeManutencaoPeriodica(){
        return quantidadeManutencaoPeriodica;
    }
}