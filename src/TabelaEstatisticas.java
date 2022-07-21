import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabelaEstatisticas extends Tabela{
	private List<Registro> registros;
	private int quantidade;

	public TabelaEstatisticas() {
		tabelaDosArquivos();
	}

	private void adicionaArquivoEAcessos(String nomeDoArquivo, int acessos){
	}

	private void adicionaLinhaArquivoEAcessos(String nomeDoArquivo) {
		HashMap<String, Integer> acessosPorArquivo = ArquivoUtils.getAcessosPorArquivo();
		int acessos = acessosPorArquivo.get(nomeDoArquivo);
		adicionaLinha(nomeDoArquivo);
		normalizaLinha(indexUltimaLinha(), 20);
		String acessos_formatado = String.format("|%7d|", acessos);
		adicionaNaUltimaLinha(acessos_formatado);
	}

	private void tabelaDosArquivos(){
		adicionaLinha("NOME DO ARQUIVO      ACESSOS  COMPARACOES  TROCAS");
		adicionaLinha("-------------------  -------  -----------  ------");

		HashMap<String, Integer> acessosPorArquivo = ArquivoUtils.getAcessosPorArquivo();
		for(int i=0; i<8; i++) {
			String nomeDoArquivo = String.format("caminho%d.csv", i+1);
			adicionaLinhaArquivoEAcessos(nomeDoArquivo);

			acessosPorArquivo.remove(nomeDoArquivo);
		}

		for (String key : acessosPorArquivo.keySet()) {
			adicionaLinhaArquivoEAcessos(key);
		}
	}
}
