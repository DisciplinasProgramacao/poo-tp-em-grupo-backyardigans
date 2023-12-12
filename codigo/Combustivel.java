/**
 * Enumerador de combustíveis. Cada tipo de combustível possui um consumo próprio e um preço próprio, ambos em double.
 */
public enum Combustivel {
    ALCOOL(7, 3.29),
    DIESEL(10, 5.19),
    GASOLINA(4, 6.09);

    private double consumo;
    private double preco;

/**
 * Construtor do enum Combustivel, que insere valores para os dois atributos, double consumo e double preco, de acordo com os parâmetros passados.
 * @param consumo parâmetro que indica o consumo do combustível em double
 * @param preco parâmetro que indica o preço do combustível em double
 */
    Combustivel(double consumo, double preco) {
        this.consumo = consumo;
        this.preco = preco;
    };

/**
 * Método do tipo get que retorna o preço do combustivel.
 * @return retorna o preço do combustível em double.
 */
    public double getPreco() {
        return this.preco;
    }

/**
 * Método do tipo get que retorna o consumo do combustivel.
 * @return retorna o consumo do combustível em double.
 */
    public double getConsumo() {
        return this.consumo;
    }

/**
 * Método do tipo get que retorna o tipo do combustivel.
 * @param numero parâmetro do tipo int para determinar qual tipo de combustível deverá ser retornado. "1" retorna ALCOOL, "2" retorna DISEL, demais
 * retornam GASOLINA.
 * @return retorna o tipo de combustível escolhido no parâmetro.
 */
    public Combustivel getTipo(int numero) {
        if (numero == 1) {
            return Combustivel.ALCOOL;
        } else if (numero == 2) {
            return Combustivel.DIESEL;
        } else {
            return Combustivel.GASOLINA;
        }
    }
} 
