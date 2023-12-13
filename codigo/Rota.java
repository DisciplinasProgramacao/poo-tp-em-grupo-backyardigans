import java.text.DecimalFormat;
import java.time.LocalDate;

public class Rota {
    private double quilometragem;
    private LocalDate data;

    /**
     * Cria um objeto da classe Rota.
     * 
     * @param quilometragem quilometragem da rota, do tipo double
     * @param data          data da rota, do tipo date
     */
    public Rota(double quilometragem, LocalDate data) {
        this.quilometragem = quilometragem;
        this.data = data;
    }

    /**
     * Relatório da rota. Formatação em 2 linhas, com
     * <ul>
     * <li>Data da rota</li>
     * <li>Quilometragem da rota</li>
     * <ul>
     * 
     * @return Uma string contendo o relatório da rota, conforme informações acima.
     */
    public String relatorio() {

        StringBuilder aux = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.##");
        aux.append(" DATA: " + data + " - ");
        aux.append(" QUILOMETRAGEM: " + df.format(quilometragem) + " km");
        return aux.toString();
    }

    /**
     * Método para obter a quilometragem
     * 
     * @return retorna a quilometragem
     */
    public double getQuilometragem() {
        return quilometragem;
    }

    /**
     * Método para obter a data da rota.
     * @return data da rota.
     */
    public LocalDate getData() {
        return data;
    }
}