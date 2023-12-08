public class Caminhao extends Veiculo {
    private static final int MANUTENCAO_PERIODICA;
    private static final int MANUTENCAO_PECA;



    static {
        MANUTENCAO_PECA = 20000;
        MANUTENCAO_PERIODICA = 20000;
    }
    public Caminhao(String placa) {
        super(placa);
        //TODO Auto-generated constructor stub
    }
    
}
