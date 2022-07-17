import java.util.List;

public class ArquivoComRegistrosAleatorios {
    public static void criar(String nomeDoArquivo, int numeroDeRegistros){
        String path = "arquivos//"+nomeDoArquivo+".csv";
        List<Registro> registros = Gerar.registros(numeroDeRegistros);
		ArquivoUtils.gravarRegistros(path, registros, false);
    }
}
