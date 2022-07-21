public class App {
    public static void main(String[] args){
        int numeroDeRegistros = numeroDeRegistrosDivisivelPorOito(800);

        ArquivoUtils.deletarArquivosPrevios();
        ArquivoComRegistrosAleatorios.criar("dadosIniciais", numeroDeRegistros);
        ArquivosDosCaminhos.criar(numeroDeRegistros);

        Cronometro cronometroIntercalar = new Cronometro();
        cronometroIntercalar.comecar();
        ArquivoFinalIntercalado.criar();
        cronometroIntercalar.parar();

        System.out.println("10 PRIMEIROS DADOS INICIAIS:");
        (new TabelaRegistros("arquivos//dadosIniciais.csv", 10)).imprimir();

        System.out.println("10 PRIMEIROS DADOS FINAIS (ORDENADOS):");
        (new TabelaRegistros("arquivos//dadosOrdenados.csv", 10)).imprimir();
        
    }

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
