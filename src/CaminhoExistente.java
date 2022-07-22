import java.util.List;

public class CaminhoExistente {
    private int index;
    private List<Registro> registros;

    // Construtor
    public CaminhoExistente(int index) {
		this.index = index;
        this.registros = lerRegistrosDoCaminho();
	}

    // Retorna um caminho
    private String caminhoPath(){
        return "arquivos//caminho" + (this.index + 1) + ".csv";
    }

    // Lê registros de um caminho
    private List<Registro> lerRegistrosDoCaminho() {
        return (List<Registro>) ArquivoUtils.lerRegistros(caminhoPath());
    }

    // Retorna primeiro registro de caminho
    public Registro primeiroRegistro(){
        return this.registros.get(0);
    }

    // Compara primeiro registro entre dois caminhos
    public int comparaPrimeiroRegistro(CaminhoExistente outroCaminho) {
        Registro primeiroRegistro = primeiroRegistro();
        Registro primeiroRegistroDoOutro = outroCaminho.primeiroRegistro();
        int comparacao = Registro.comparaRegistros(primeiroRegistro, primeiroRegistroDoOutro);
        return comparacao;
    }

    // Retorna se caminho está vazio
    public boolean isEmpty(){
        return this.registros.isEmpty();
    }

    // GETTERS E SETTERS
    public List<Registro> getRegistros(){ return registros; }
}
