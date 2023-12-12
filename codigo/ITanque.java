/**
 * Interface Tanque. Todas classes que participam desta interface devem possuir os métodos
 *  double autonomiaAtual(), double autonomiaMaxima(), double abastecer(double litros);
 */
public interface ITanque {
    public double autonomiaAtual();
    public double autonomiaMaxima();
    public double abastecer(double litros);
}
