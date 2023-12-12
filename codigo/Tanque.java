import java.text.DecimalFormat;

public class Tanque implements ITanque {
    private double consumo;
    private double capacidadeMaxima;
    private double capacidadeAtual;
    private Combustivel tipo;

    /**
     * Cria um objeto da classe tanque.
     * @param capacidadeMaxima capacidade máxima do tanque, do tipo double.
     * @param tipoCombustivel tipo de combustível do tanque, do tipo String.
     */
    public Tanque(double capacidadeMaxima, String tipoCombustivel) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.capacidadeAtual = capacidadeMaxima;
        this.tipo = Combustivel.valueOf(tipoCombustivel);
        this.consumo = tipo.getConsumo();
    }

    /**
     * Abastace o tanque do carro de acordo com a quantidade fornecida, ignorando o
     * sinal do valor
     * 
     * @param litros Litros a serem abastecidos dentro do tanque
     * @return a capacidade atual do tanque depois de abastecido, em Double
     */
    public double abastecer(double litros) {
        capacidadeAtual += litros;
        return capacidadeAtual;
    }

    /**
     * Fornece quilometragem possivel de acordo com a capacidade maxima do tanque
     * 
     * @return o valor em double da capacidade maxima do tanque vezes o consumo do
     *         veiculo
     */

    public double autonomiaMaxima() {

        double kmPossiveis = capacidadeMaxima * consumo;
        return kmPossiveis;
    }

    /**
     * Fornece quilometragem possivel da capacidade Atual do tanque
     * 
     * @return o valor em double da capacidade atual do tanque vezes o consumo do
     *         veiculo
     */
    public double autonomiaAtual() {
        double quantosKmPodeRodar;
        quantosKmPodeRodar = capacidadeAtual * consumo;
        
        return quantosKmPodeRodar;
    }

    /**
     * Método para obter o consumo.
     * @return retorna o consumo, do tipo double.
     */
    public double getConsumo() {
        return consumo;
    }

    /**
     * Método para obter a capacidade atual.
     * @return retorna a capacidade atual, do tipo double.
     */
    public double capacidadeAtual() {
        return this.capacidadeAtual;
    }

    /**
     * Método para obter a capacidade máxima.
     * @return retorna a capacidade máxima, do tipo double.
     */
    public double capacidadeMaxima() {
        return capacidadeMaxima;
    }

    /**
     * Método que consome litros, atualizando a capacidade atual de acordo
     * com o valor de litros passado como parâmetro. Tem como valor mínimo
     * 0, pois não é possível o tanque possuir capacidade negativa.
     * @param litros quantidade de litros a serem consumidos, do tipo double.
     */
    public void consumirLitros(double litros) {
        if (capacidadeAtual - litros < 0) {
            capacidadeAtual = 0;
        } else {
            capacidadeAtual -= litros;
        }

    }

    /**
     * Método para obter o preço do combustível.
     * @return retorna o preço do combustível, do tipo double.
     */
    public double getPrecoCombustivel() {
        return tipo.getPreco();
    }

    /**
     * Relatório do tanque. Formatação em 3 linhas, com
     * <ul>
     * <li>Combustível: </li>
     * <li>Capacidade Atual: </li>
     * <li>Capacidade Máxima: </li>
     * <ul>
     * @return Retorna uma String contendo o relatório do tanque, conforme informações acima.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.##");

        str.append("Combustível: " + tipo.name());
        str.append("\nCapacidade Máxima: " + df.format(capacidadeMaxima));
        str.append("\nCapacidade Atual: " + df.format(capacidadeAtual));
        return str.toString();
    }
}
