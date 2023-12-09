public class Tanque implements ITanque {
    private double consumo;
    private double capacidadeMaxima;
    private double capacidadeAtual;
    private Combustivel tipo;

    public Tanque(double capacidadeMaxima, String tipoCombustivel) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.capacidadeAtual = capacidadeMaxima;
        this.tipo = Combustivel.valueOf(tipoCombustivel);
        this.consumo = getConsumo();
    }

    /**
     * Abastace o tanque do carro de acordo com a quantidade fornecida, ignorando o
     * sinal do valor
     * 
     * @param litros Litros a serem abastecidos dentro do tanque
     * @return a capacidade atual do tanque depois de abastecido, em Double
     */
    public double abastecer(double litros) {

        if (litros < 0) {
            litros = litros * (-1);
        }

        if (capacidadeAtual + litros < capacidadeMaxima) {
            capacidadeAtual += litros;
        } else if (capacidadeAtual + litros >= capacidadeMaxima) {
            capacidadeAtual = capacidadeMaxima;
        }
        return capacidadeAtual;
    }

    /**
     * Fornece quilometragem possivel de acordo com a capacidade maxima do tanque
     * 
     * @return o valor em double da capacidade maxima do tanque vezes o consumo do
     *         veiculo
     */

    public double autonomiaMaxima() {

        double kmPossiveis = capacidadeMaxima / consumo;
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

        quantosKmPodeRodar = capacidadeAtual / consumo;

        return quantosKmPodeRodar;
    }

    public double getConsumo() {
        return tipo.getConsumo();
    }

    public double capacidadeAtual() {
        return this.capacidadeAtual;
    }

    public double capacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void consumirLitros(double litros) {
        capacidadeAtual -= litros;
    }

    public double getPrecoCombustivel(){
        return tipo.getPreco();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("Combustível: "+tipo.name());
        str.append("\nCapacidade Máxima"+capacidadeMaxima);
        str.append("\nCapacidade Atual"+capacidadeAtual);
        return super.toString();
    }
}
