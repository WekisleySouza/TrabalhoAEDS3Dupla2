import java.util.List;

public class ArquivoComRegistrosAleatorios {

    // Gera arquivos com um  número de registros aleatórios
    public static void executar(String nomeDoArquivo, int numeroDeRegistros){
        String path = "arquivos//"+nomeDoArquivo+".csv";
        List<Registro> registros = Gerar.registros(numeroDeRegistros);
		ArquivoUtils.gravarRegistros(path, registros, false);
    }
}
