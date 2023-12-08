public enum Combustivel {
    ALCOOL(7, 3.29),
    DIESEL(10, 5.19),
    GASOLINA(4, 6.09);

    private double consumo;
    private double preco;

    Combustivel(double consumo, double preco) {
        this.consumo = consumo;
        this.preco = preco;
    };

    public double getPreco() {
        return this.preco;
    }

    public double getConsumo() {
        return this.consumo;
    }

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
