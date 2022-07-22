import java.util.List;

public class CaminhoNovo {
    private int index;
    private int tamanhoCaminho;
    private MergeSort ordenacao;
    private List<Registro> registros;
    private final String dadosIniciaisPath = "arquivos//dadosIniciais.csv";

    // Construtor
    public CaminhoNovo(int index, int tamanhoCaminho) {
		this.index = index;
        this.tamanhoCaminho = tamanhoCaminho;
        this.registros = registrosDoCaminho();
        this.ordenacao = new MergeSort(this.registros);
	}

    // Salva registros de forma ordenada
    public void salvarOrdenado(){
        ArquivoUtils.gravarRegistros(caminhoPath(), this.registros, false);
    }

    // Retorna caminho do arquivo
    private String caminhoPath(){
        return "arquivos//caminho" + (this.index + 1) + ".csv";
    }

    // Retorna registros ordenados
    public MergeSort getOrdenacao(){    
        return this.ordenacao;
    }

    // Retorna index do registro inicial
    private int registroInicialIndex(){
        return this.index * this.tamanhoCaminho;
    }

    // Retorna index do registro final
    private int registroFinalIndex(){
        return (this.index + 1) * this.tamanhoCaminho;
    }

    // Retorna lista de registros do caminho
    private List<Registro> registrosDoCaminho() {
        return (List<Registro>) ArquivoUtils.lerRegistrosNoIntervalo(
            dadosIniciaisPath, 
            registroInicialIndex(), 
            registroFinalIndex()
            );
    }
}
