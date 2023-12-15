import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class Frota {

    protected ArrayList<Veiculo> veiculos;
    private static int quantidadeMPecaAntiga;
    private static int quantidadeMPeriodicaAntiga;
    private static double valorAntigoMPega;
    private static double valorAntigoMPeriodico;

    /**
     * Inicializa valores statics
     */
    static{
        quantidadeMPeriodicaAntiga = 0;
        quantidadeMPecaAntiga = 0;
        valorAntigoMPega = 0;
        valorAntigoMPeriodico = 0;
    }

    public Frota() {
        this.veiculos = new ArrayList<Veiculo>();
    }

    /**
     * Metódo que retorna uma String com um relatório da frota de veículos.
     * Retorna uma String composta pela quantidade de veículos presentes na frota e
     * pelos veículos existentes, além dos detalhes das rotas realizadas por cada
     * veículo.
     * 
     */
    public String relatorioFrota() {
        StringBuilder relatorio = new StringBuilder();

        relatorio.append("RELATÓRIO DA FROTA\n");
        relatorio.append("------------------\n");
        relatorio.append("Veículo com mais quilometros: "+maiorKmTotal().getPlaca()+"\n");
        relatorio.append("Veículo com maior média de quilometros: "+maiorKmMedia().getPlaca()+"\n");

        if (veiculos.isEmpty()) {
            relatorio.append("Frota vazia");
        } else {
            relatorio.append("Tamanho da frota: " + veiculos.size() + "\n");
        }

        for (Veiculo veiculo : veiculos) {
            relatorio.append("Tipo: " + veiculo.getTipo() + "\n");
            relatorio.append("Placa: " + veiculo.getPlaca()+ "\n");
            relatorio.append("Tanque: " + veiculo.getTanque().getCapacidadeMaxima()+ " litros\n");

            relatorio.append("\n\n");
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
     * Metódo que zera todas as rotas dos veiculos dentro da frota
     */
    public void zerarRotas() {
        for (Veiculo veiculo : veiculos) {
            veiculo.getRotas().clear();
        }
    }

    /**
     * Método que adiciona um novo veículo à frota.
     * 
     * @param veiculo parâmetro do tipo veículo que passa o veículo que será
     *                adicionado à frota.
     */
    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
        veiculos.sort(Comparator.comparing(Veiculo::getPlaca));
    }

    /**
     * Método que calcula o gasto total de determinado veículo, considerando os
     * valores de manutenção
     * periodica, manutenção de peças e gastos em combustível, de acordo com a
     * quantidade de recorrência de
     * cada. Para encontrar o veículo cuja placa foi passada como parâmetro, usa-se
     * Stream para percorrer a
     * lista de veículos e localizar a placa desejada.
     * 
     * @param placa           parâmetro do tipo String que passa a placa do veículo
     *                        cujo gasto total será
     *                        calculado.
     * 
     * @param valorMPeca      parâmetro do tipo double que indica o valor da
     *                        manutenção de peça.
     * 
     * @param valorMPeriodico parâmetro do tipo double que indica o valor da
     *                        manutenção periódica.
     * 
     * @return retorna o gasto total, obtido pela soma do gastos totais com
     *         combustível, manutenção periodica
     *         e manutenção de peças
     */
    public String relatorioGastoTotal(Veiculo v, double valorMPeca, double valorMPeriodico) {

        StringBuilder str = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#.##");

        int quantidadeAtualPeca = v.quantidadeManutencaoPeca();
        int quantidadeAtualPeriodica = v.quantidadeManutencaoPeriodica();

        if(v.quantidadeManutencaoPeca() > 0){
            quantidadeAtualPeca = v.quantidadeManutencaoPeca() - quantidadeMPecaAntiga;
        }

        if(v.quantidadeManutencaoPeriodica() > 0){
            quantidadeAtualPeriodica =v.quantidadeManutencaoPeriodica() -  quantidadeMPeriodicaAntiga;
        }

        double gastoEmCombustivel = v.getTotalReabastecido() * v.getTanque().getPrecoCombustivel();
        double valorManutencaoPeriodica = (quantidadeAtualPeriodica * valorMPeriodico);
        double valorManutencaoPeca = (quantidadeAtualPeca * valorMPeca);

        str.append("O gasto total do veículo de placa " + v.getPlaca() + " foi: ");

        str.append("\nQuantidade de manutenção periódica: " + v.quantidadeManutencaoPeriodica() + " - Valor: R$"
                + df.format((valorManutencaoPeriodica)));

        str.append("\nQuantidade de manutenção em peças: " + v.quantidadeManutencaoPeca() + " - Valor: R$"
                + df.format(valorManutencaoPeca));

        str.append("\nTotal reabastecido pelo carro: " + df.format(v.getTotalReabastecido())+" litros"+ " - Valor: R$"
                + df.format(gastoEmCombustivel));

        str.append("\nValor total gasto: R$"
                + df.format(gastoEmCombustivel + (valorManutencaoPeca+valorAntigoMPega) + (valorManutencaoPeriodica+valorAntigoMPeriodico)));
 
        if(v.quantidadeManutencaoPeca()>0){
            valorAntigoMPega += valorManutencaoPeca;
        }
        if(v.quantidadeManutencaoPeriodica()>0){
            valorAntigoMPeriodico += valorMPeriodico;
        }

        quantidadeMPecaAntiga = v.quantidadeManutencaoPeca();
        quantidadeMPeriodicaAntiga = v.quantidadeManutencaoPeriodica();
            return str.toString();
    }
}