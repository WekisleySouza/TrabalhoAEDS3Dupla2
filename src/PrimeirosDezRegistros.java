import java.util.List;

public class PrimeirosDezRegistros extends Tabela{
	private List<Registro> registros;

	public PrimeirosDezRegistros(String path) {
		this.registros = ArquivoUtils.ler(path); 
		criarEsqueleto();
		inserirDezPrimeiros();
	}

	private void criarEsqueleto(){
		adicionaLinha("    ID CEP       SEXO IDADE NOME                                                                    CPF");
		adicionaLinha("------ --------- ---- ----- ----------------------------------------------------------------------- --------------");
	}

	private void inserirDezPrimeiros(){
		for(int i=0; i<10; i++){
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
