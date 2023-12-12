import java.util.ArrayList;

public class Frota {

    private ArrayList<Veiculo> veiculos;

    public Frota() {
        this.veiculos = new ArrayList<Veiculo>();
    }

    /**
     * Metódo que retorna uma String com um relatório da frota de veículos.
     * Ele é composto pela quantidade de veículos presentes na frota e pelos veículos
     * existentes.
     * 
     * @return String segundo a descrição:
     *         <ul>
     *         <li>
     *         <li>
     *         <ul>
     */
    public String relatorioFrota() {
        StringBuilder relatorio = new StringBuilder();

        relatorio.append("RELATÓRIO DA FROTA\n");
        relatorio.append("------------------\n");

        if (veiculos.isEmpty()) {
            relatorio.append("Frota vazia");
        } else {
            relatorio.append("Tamanho da frota: " + veiculos.size() + "\n");
        }

        for (Veiculo veiculo : veiculos) {
            relatorio.append(veiculo.relatorioRotasVeiculo() + "\n\n");
        }

        return relatorio.toString();
    }

    /**
     * Metódo que vai localizar, a partir da placa fornecida, se o veículo existe na
     * frota.
     * 
     * @param placa Placa do veículo que será pesquisado.
     * @return Veículo procurado.
     */
    public Veiculo localizarVeiculo(String placa) {
        Veiculo veiculoLocalizado = null;

        for (Veiculo x : veiculos) {
            if (placa.equalsIgnoreCase(x.getPlaca())) {
                veiculoLocalizado = x;
            }
        }

        return veiculoLocalizado;
    }

    /**
     * Metódo que vai retornar a quilometragem total de todos os veículos da frota.
     * 
     * @return Quilometragem total da frota de veículos.
     */
    public double quilometragemTotal() {
        double totalQuilometragem = 0;

        for (Veiculo x : veiculos) {
            totalQuilometragem += x.getKmTotal();
        }

        return totalQuilometragem;
    }

    /**
     * Metódo que vai retornar o veículo com a maior quilometragem da frota.
     * 
     * @return Veículo com a maior quilometragem da frota.
     */
    public Veiculo maiorKmTotal() {
        double maiorKm = 0;
        Veiculo veiculoMaiorKm = null;

        for (Veiculo x : veiculos) {
            if (x.getKmTotal() > maiorKm) {
                maiorKm = x.getKmTotal();
                veiculoMaiorKm = x;
            }
        }

        return veiculoMaiorKm;
    }

    /**
     * Metódo que vai retornar o veículo com a maior quilometragem média da frota.
     * 
     * @return Veículo com a maior quilometragem média da frota.
     */
    public Veiculo maiorKmMedia() {
        Veiculo veiculoMaiorMedia = null;
        double maiorKmMedia = 0;

        for (Veiculo x : veiculos) {
            double mediaVeiculo = x.getKmTotal() / x.getQuantRotas();
            if (mediaVeiculo > maiorKmMedia) {
                maiorKmMedia = mediaVeiculo;
                veiculoMaiorMedia = x;
            }
        }

        return veiculoMaiorMedia;
    }

    /**
     * Método que vira o mes de toda a frota de veiculos, limpando todas as rotas do mes de
     * cada veiculo
     */
    public void virarMes() {

        for (Veiculo veiculo : veiculos) {
            veiculo.zerarRotas();
        }
    }

    /**
     * Método que adiciona um novo veículo à frota.
     * @param veiculo parâmetro do tipo veículo que passa o veículo que será adicionado à frota.
     */
    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    /**
     * Método que calcula o gasto total de determinado veículo, considerando os valores de manutenção periodica,
     * manutenção de peças e gastos em combustível, de acordo com a quantidade de recorrência de cada. Para encontrar
     * o veículo cuja placa foi passada como parâmetro, usa-se Stream para percorrer a lista de veículos e localizar
     * a placa desejada.
     * @param placa parâmetro do tipo String que passa a placa do veículo cujo gasto total será
     * calculado.
     * @param valorMPeca parâmetro do tipo double que indica o valor da manutenção de peça.
     * @param valorMPeriodico parâmetro do tipo double que indica o valor da manutenção periódica.
     * @return retorna o gasto total, obtido pela soma do gastos totais com combustível, manutenção periodica e
     * manutenção de peças
     */
    public double gastoTotal(String placa, double valorMPeca, double valorMPeriodico) {
        Veiculo v = (Veiculo) veiculos.stream().filter(e -> e.getPlaca().equals(placa));

        double valorManutencaoPeriodica = v.quantidadeManutencaoPeriodica() * valorMPeriodico;
        double valoManutencaoPeca = v.quantidadeManutencaoPeca() * valorMPeca;
        double gastoEmCombustivel = v.getTotalReabastecido() * v.valorCombustivel();

        return gastoEmCombustivel + valoManutencaoPeca + valorManutencaoPeriodica;
    }
}
