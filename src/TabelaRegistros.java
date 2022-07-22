import java.util.List;

public class TabelaRegistros extends Tabela{
	private List<Registro> registros;
	private int quantidade;

	// Construtor
	public TabelaRegistros(String path, int quantidade) {
		this.registros = ArquivoUtils.lerRegistros(path); 
		this.quantidade = quantidade;
		criarEsqueleto();
		inserirDezPrimeiros();
	}

	// Cria esqueleto da tabela
	private void criarEsqueleto(){
		adicionaLinha("    ID CEP       SEXO IDADE NOME                                                                    CPF");
		adicionaLinha("------ --------- ---- ----- ----------------------------------------------------------------------- --------------");
	}

	// insere dez primeiros registros
	private void inserirDezPrimeiros(){
		for(int i=0; i<this.quantidade; i++){
			inserirRegistro(i);
		}
		adicionaLinha("");
	}

	// Insere registros na tabela
	private void inserirRegistro(int index){
		Registro registro = this.registros.get(index);
		int indexLinha = index+2;
		
		adicionaLinha(inteiroPelaDireita(registro.getId(), 6));
		adicionarEspacos(indexLinha, 1);

		adicionaNaUltimaLinha(registro.getCep());
		adicionarEspacos(indexLinha, 1);
		
		adicionaNaUltimaLinha(String.valueOf(registro.getSexo()));
		adicionarEspacos(indexLinha, 4);

		adicionaNaUltimaLinha(inteiroPelaDireita(registro.getIdade(), 5));
		adicionarEspacos(indexLinha, 1);

		adicionaNaUltimaLinha(registro.getNome());
		normalizaLinha(indexLinha, 100);

		adicionaNaUltimaLinha(registro.getCpf());
	}
}
