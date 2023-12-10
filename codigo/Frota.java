import java.util.ArrayList;

public class Frota {

    private ArrayList<Veiculo> veiculos;

    public Frota() {
        this.veiculos = new ArrayList<Veiculo>();
    }

    /**
     * Metódo que retorna uma String com um relatório da frota de veículos.
     * Ele é composto pela quantidade de veículos presente na frota e pelos veículos
     * existentes.
     * 
     * @return String segundo a descrição:
     *         <ul>
     *         <li>
     *         <li>
     *         <ul>
     */
    public String toString() {
        StringBuilder relatorio = new StringBuilder();

        relatorio.append("RELATÓRIO DA FROTA\n");
        relatorio.append("------------------\n");

        if (veiculos.isEmpty()) {
            relatorio.append("Frota vazia");
        } else {
            relatorio.append("Tamanho da frota: " + veiculos.size() + "\n");
        }

        for (Veiculo veiculo : veiculos) {
                relatorio.append(veiculo.toString() + "\n\n");
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
            totalQuilometragem += x.kmTotal();
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
            if (x.kmTotal() > maiorKm) {
                maiorKm = x.kmTotal();
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
            double mediaVeiculo = x.kmTotal() / x.getQuantRotas();
            if (mediaVeiculo > maiorKmMedia) {
                maiorKmMedia = mediaVeiculo;
                veiculoMaiorMedia = x;
            }
        }

        return veiculoMaiorMedia;
    }

    /**
     * Vira o mes de toda a frota de veiculos, limpando todas as rotas do mes de
     * cada veiculo
     */
    public void virarMes() {

        for (Veiculo veiculo : veiculos) {
            veiculo.zerarRotas();
        }
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public double gastoTotal(String placa, double valorMPeca, double valorMPeriodico) {
        Veiculo v = (Veiculo) veiculos.stream().filter(e -> e.getPlaca().equals(placa));

        double valorManutencaoPeriodica = v.quantidadeManutencaoPeriodica() * valorMPeriodico;
        double valoManutencaoPeca = v.quantidadeManutencaoPeca() * valorMPeca;
        double gastoEmCombustivel = v.getTotalReabastecido() * v.valorCombustivel();

        
       return gastoEmCombustivel +  valoManutencaoPeca + valorManutencaoPeriodica;
    }
}
