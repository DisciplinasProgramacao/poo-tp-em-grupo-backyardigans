public class Veiculo {

    private static final int MAX_ROTAS;
    private static final double CONSUMO;
    private String placa;
    private Rota rotas[];
    private int quantRotas;
    private Tanque tanque;
    private double totalReabastecido;

    static{
        MAX_ROTAS = 30;
        CONSUMO = 8.2;
    }

    public String getPlaca() {
        return placa;
    }

    public int getQuantRotas(){
        return quantRotas;
    }

    public double kmTotal(){
        double kmTotal =0;

        return kmTotal;
    }

    private double autonomiaAtual() {
        double quantidadeTanque = tanque.autonomiaAtual();
        return quantidadeTanque;
    }

    private double autonomiaMaxima() {
        double quantidadeTanque = tanque.autonomiaMaxima();
        return quantidadeTanque;
    }
}
