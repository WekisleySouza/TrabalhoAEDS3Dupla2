import java.util.List;
import java.util.ArrayList;

public class ArquivosDosCaminhos {
    public static void criar(int numeroDeRegistros){
        int tamanhoCaminho = numeroDeRegistros / 8;
        for(int i = 0; i < 8; i++){
            CaminhoNovo caminho = new CaminhoNovo(i, tamanhoCaminho);
            caminho.salvarOrdenado();
        }
    }
}
