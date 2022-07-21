import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabelaEstatisticas extends Tabela{
	private List<MergeSort> ordenacoes;

	public TabelaEstatisticas(List<MergeSort> ordenacoes, long tempoTotal, long tempoIntercalar) {
		this.ordenacoes = ordenacoes;
		tabelaDosArquivos();
		adicionaLinha("");
		String textoTempo = String.format(
			"ORDENACAO REALIZADA NUM TEMPO TOTAL DE %d ms, DOS QUAIS %d ms FORAM DEDICADOS A INTERCALACAO",
			tempoTotal, 
			tempoIntercalar
			);

		adicionaLinha(textoTempo);
	}

	private void adicionaLinhaArquivoEAcessos(String nomeDoArquivo) {
		HashMap<String, Integer> acessosPorArquivo = ArquivoUtils.getAcessosPorArquivo();
		int acessos = acessosPorArquivo.get(nomeDoArquivo);
		adicionaLinha(nomeDoArquivo);
		normalizaLinha(indexUltimaLinha(), 20);
		String acessos_formatado = String.format("|%7d", acessos);
		adicionaNaUltimaLinha(acessos_formatado);
	}

	private void adicionaLinhaComparacoesETrocas(MergeSort ordenacao) {
		String comparacoes = String.format("%13d", ordenacao.getComparacoes());
		String trocas = String.format("%12d", ordenacao.getTrocas());
		adicionaNaUltimaLinha(comparacoes);
		adicionaNaUltimaLinha(trocas);
	}

	private void tabelaDosArquivos(){
		adicionaLinha("NOME DO ARQUIVO      ACESSOS  COMPARACOES      TROCAS");
		adicionaLinha("-------------------  -------  -----------  ----------");

		HashMap<String, Integer> acessosPorArquivo = ArquivoUtils.getAcessosPorArquivo();
		for(int i=0; i<8; i++) {
			String nomeDoArquivo = String.format("caminho%d.csv", i+1);
			adicionaLinhaArquivoEAcessos(nomeDoArquivo);
			adicionaLinhaComparacoesETrocas(ordenacoes.get(i));
			acessosPorArquivo.remove(nomeDoArquivo);
		}

		for (String key : acessosPorArquivo.keySet()) {
			adicionaLinhaArquivoEAcessos(key);
		}
	}
}
