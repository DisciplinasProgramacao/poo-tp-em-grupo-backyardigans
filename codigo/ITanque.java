public interface ITanque {
    public double autonomiaAtual();
    public double autonomiaMaxima();
    public double abastecer(double litros);
    public double calcularGastoTotal();
    public void consumirLitros(double litros);
}
