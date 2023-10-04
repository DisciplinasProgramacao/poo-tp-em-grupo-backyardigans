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

    public Veiculo(String placa, double tanqueMaximo) {
        this.placa = placa;
        this.tanqueMaximo = tanqueMaximo;
        this.rotas = new Rota[MAX_ROTAS];
    }

    public double kmTotal() {
       // Logica para calcular quilometragem total
        // Retorna a quilometragem total
        return kmTotal;
    }

     public double kmNoMes() {
        // Logica para calcular quilometragem no mes
        // Retorna a quilometragem percorrida no mes atual
        return 0;
    }

    private double autonomiaAtual() {
        double quantidadeTanque = tanque.autonomiaAtual();
        return quantidadeTanque;
    }

    private double autonomiaMaxima() {
        double quantidadeTanque = tanque.autonomiaMaxima();
        return quantidadeTanque;
    }    private void percorrerRota(Rota rota) {
        // Logica para percorrer a rota
    }

}
