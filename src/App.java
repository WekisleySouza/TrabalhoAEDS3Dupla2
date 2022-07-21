import java.util.List;

public class App {
    public static void main(String[] args){
        int numeroDeRegistros = numeroDeRegistrosDivisivelPorOito(800000);
        ArquivoUtils.deletarArquivosPrevios();

        // CRIAÇÃO DO ARQUIVO INICIAL
        ArquivoComRegistrosAleatorios.executar("dadosIniciais", numeroDeRegistros);

        Cronometro cronometroOrdenacao = new Cronometro();
        cronometroOrdenacao.comecar(); //criação + intercalação

        // CRIAÇÃO DE CAMINHOS
        ArquivosDosCaminhos etapaCaminhos = new ArquivosDosCaminhos();
        etapaCaminhos.executar(numeroDeRegistros);
        List<MergeSort> ordenacoes = etapaCaminhos.getOrdenacoes();

        // INTECALAÇÃO
        ArquivoFinalIntercalado etapaIntercalar = new ArquivoFinalIntercalado();
        etapaIntercalar.executar();
        long tempoIntercalar = etapaIntercalar.getTempo();

        cronometroOrdenacao.parar(); //criação + intercalação

        // IMPRIME O RELATÓRIO
        System.out.println("10 PRIMEIROS DADOS INICIAIS:");
        (new TabelaRegistros("arquivos//dadosIniciais.csv", 10)).imprimir();

        System.out.println("10 PRIMEIROS DADOS FINAIS (ORDENADOS):");
        (new TabelaRegistros("arquivos//dadosOrdenados.csv", 10)).imprimir();

        System.out.println("ALGUMAS ESTATISTICAS:");
        (new TabelaEstatisticas(ordenacoes, cronometroOrdenacao.getTempo(), tempoIntercalar)).imprimir();
        
    }

    // Arredonda o número de registros a ser ordenado para 8
    private static int numeroDeRegistrosDivisivelPorOito(int numeroDeRegistros){
        if(numeroDeRegistros % 8 == 0){
            return numeroDeRegistros;
        }

        int novoNumeroDeRegistros = (int)(numeroDeRegistros/8) * 8;
        System.out.println(
            "Este programa utiliza a intercalação balanceada de 8 caminhos. "
            + "Um número não divisivel por 8, ["
            + Integer.toString(numeroDeRegistros)
            + "] foi escolhido, então foi feito um arredondamento para "
            + Integer.toString(novoNumeroDeRegistros)
            + " registros\n");

        return novoNumeroDeRegistros;
    }
}
