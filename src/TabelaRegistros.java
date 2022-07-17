import java.util.List;

public class TabelaRegistros extends Tabela{
	private List<Registro> registros;
	private int quantidade;

	public TabelaRegistros(String path, int quantidade) {
		this.registros = ArquivoUtils.ler(path); 
		this.quantidade = quantidade;
		criarEsqueleto();
		inserirDezPrimeiros();
	}

	private void criarEsqueleto(){
		adicionaLinha("    ID CEP       SEXO IDADE NOME                                                                    CPF");
		adicionaLinha("------ --------- ---- ----- ----------------------------------------------------------------------- --------------");
	}

	private void inserirDezPrimeiros(){
		for(int i=0; i<this.quantidade; i++){
			inserirRegistro(i);
		}
		adicionaLinha("");
	}

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
