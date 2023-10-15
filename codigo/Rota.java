import java.util.*;

public class Rota{
    private double quilometragem;
    private Date data;

/**
 * Cria um objeto da classe Rota.
 * @param quilometragem quilometragem da rota, do tipo double
 * @param data data da rota, do tipo date
 */
    public Rota (double quilometragem, Date data){
        this.quilometragem=quilometragem;
        this.data=data;
    }

/**
 *Relatório da rota. Formatação em 2 linhas, com <ul>
     * <li> Data da rota </li>
     * <li> Quilometragem da rota </li>
     * <ul>
     * @return Uma string contendo o relatório da rota, conforme informações acima. 
 */
 @Override
	public String toString() {

		StringBuilder aux = new StringBuilder();
        aux.append(" DATA: "+data+"\n");
        aux.append(" QUILOMETRAGEM: "+quilometragem+ " km");
        return aux.toString();
	}
}