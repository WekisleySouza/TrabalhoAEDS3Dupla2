public class App {
    public static void main(String[] args){
        int numeroDeRegistros = numeroDeRegistrosDivisivelPorOito(100);

        ArquivoUtils.deletarArquivosPrevios();
        ArquivoComRegistrosAleatorios.criar("dadosIniciais", numeroDeRegistros);
        ArquivosDosCaminhos.criar(numeroDeRegistros);
        ArquivoFinalIntercalado.criar("dadosOrdenados");
        
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
            + " registros");

        return novoNumeroDeRegistros;
    }
}
