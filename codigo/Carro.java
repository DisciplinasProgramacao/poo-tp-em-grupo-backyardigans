public class Carro extends Veiculo {

    private static final int MANUTENCAO_PERIODICA;
    private static final int MANUTENCAO_PECA;



    static {
        MANUTENCAO_PECA = 10000;
        MANUTENCAO_PERIODICA = 10000;
    }
    public Carro(String placa, int tipoCombustivel) {

            super(placa);
            this.tanque = new Tanque(50, tipoCombustivel);
            this.consumo = tanque.getConsumo();
            
    }
    
}
