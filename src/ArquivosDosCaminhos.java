import java.util.ArrayList;
import java.util.List;

public class ArquivosDosCaminhos {
    private List<MergeSort> ordenacoes;

    public void executar(int numeroDeRegistros){
        this.ordenacoes = new ArrayList<MergeSort>();

        int tamanhoCaminho = numeroDeRegistros / 8;
        for(int i = 0; i < 8; i++){
            CaminhoNovo caminho = new CaminhoNovo(i, tamanhoCaminho);
            caminho.salvarOrdenado();
            ordenacoes.add(caminho.getOrdenacao());
        }
    }

    // GETTERS E SETTERS
    public List<MergeSort> getOrdenacoes() { return ordenacoes; }
}
