import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.*;

public class CaminhoNovo {
    private int index;
    private int tamanhoCaminho;
    private MergeSort ordenacao;
    private List<Registro> registros;
    private final String dadosIniciaisPath = "arquivos//dadosIniciais.csv";

    public CaminhoNovo(int index, int tamanhoCaminho) {
		this.index = index;
        this.tamanhoCaminho = tamanhoCaminho;
        this.registros = registrosDoCaminho();
        this.ordenacao = new MergeSort(this.registros);
	}

    public void salvarOrdenado(){
        ArquivoUtils.gravarRegistros(caminhoPath(), this.registros, false);
    }

    private String caminhoPath(){
        return "arquivos//caminho" + (this.index + 1) + ".csv";
    }

    public MergeSort getOrdenacao(){    
        return this.ordenacao;
    }

    private int registroInicialIndex(){
        return this.index * this.tamanhoCaminho;
    }

    private int registroFinalIndex(){
        return (this.index + 1) * this.tamanhoCaminho;
    }

    private List<Registro> registrosDoCaminho() {
        return (List<Registro>) ArquivoUtils.lerIntervalo(
            dadosIniciaisPath, 
            registroInicialIndex(), 
            registroFinalIndex()
            );
    }
}
