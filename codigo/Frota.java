public class Frota {

    private int tamanhoFrota;
    private Veiculo veiculos[];

    public Frota(int tamanhoFrota) {
        this.tamanhoFrota = tamanhoFrota;
        this.veiculos = new Veiculo[tamanhoFrota];
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

        relatorio.append("RELATÓRIO DA FROTA DE PEDIDOS\n");
        relatorio.append("-----------------------------\n");

        if (tamanhoFrota == 0) {
            relatorio.append("Frota vazia");
        } else {
            relatorio.append("Tamanho da frota: " + tamanhoFrota);
        }

        for (int i = 0; i < veiculos.length; i++) {
            relatorio.append(String.format("02d", i));
            relatorio.append(veiculos.toString() + "\\n");
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
}