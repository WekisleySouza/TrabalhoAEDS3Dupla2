import java.util.List;

public class CaminhoExistente {
    private int index;
    private List<Registro> registros;

    public CaminhoExistente(int index) {
		this.index = index;
        this.registros = lerRegistros();
	}

    private String caminhoPath(){
        return "arquivos//caminho" + (this.index + 1) + ".csv";
    }

    private List<Registro> lerRegistros() {
        return (List<Registro>) ArquivoUtils.ler(caminhoPath());
    }

    public Registro primeiroRegistro(){
        return this.registros.get(0);
    }

    public int comparaPrimeiroRegistro(CaminhoExistente outroCaminho) {
        Registro primeiroRegistro = primeiroRegistro();
        Registro primeiroRegistroDoOutro = outroCaminho.primeiroRegistro();
        int comparacao = Registro.comparaRegistros(primeiroRegistro, primeiroRegistroDoOutro);
        return comparacao;
    }

    public boolean isEmpty(){
        return this.registros.isEmpty();
    }

    // GETTERS E SETTERS
    public List<Registro> getRegistros(){ return registros; }
}
