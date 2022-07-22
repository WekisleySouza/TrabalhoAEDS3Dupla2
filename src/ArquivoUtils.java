import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class ArquivoUtils {
    private static List<List<String>> nomes = new ArrayList<List<String>>();
    private static List<String> sobrenomes = new ArrayList<String>();
    private static HashMap<String, Integer> acessosPorArquivo = new HashMap<String, Integer>();

    // Grava registros de lista em um arquivo
    public static void gravarRegistros(String path, List<Registro> registros, boolean adicionar){
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, adicionar))){
			for(Registro registro : registros){
				bw.write(registro.toCSV());
				bw.newLine();
			}
            registrarAcesso(path);
		}
		catch(IOException erro){
			erro.printStackTrace();
		}
	}

    // Grava registro em um arquivo
    public static void gravarRegistro(String path, Registro registro, boolean adicionar){
        List<Registro> registros = new ArrayList<Registro>();
        registros.add(registro);

        gravarRegistros(path, registros, adicionar);
    }

    // Ler arquivo e adiciona em uma lista
    public static List<String> ler(String path) {
        List<String> linhas = new ArrayList<String>();
        String linha = "";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while(true){
                linha = br.readLine();
                if (linha == null) { break; };
                linhas.add(linha);
            }
            registrarAcesso(path);
        }
        catch(IOException e){
          System.out.println(e);
        }
        return linhas;
    }

    // Lê um arquivo com registros e retorna convertido em uma lista de registros
    public static List<Registro> lerRegistros(String path) {
        List<String> linhas = ler(path);
        return Registro.manyFromCSV(linhas.toArray(new String[0]));
    }

    // Retorna nome de acordo com o sexo
    public static List<String> getNomes(char sexo) {
        int index = sexo == 'F' ? 0 : 1 ;
        if(nomes.isEmpty()){
            nomes.add(lerNomes('F'));
            nomes.add(lerNomes('M'));
        }

        return nomes.get(index);
    }

    // Retorna número de acessos por arquivo
    public static HashMap<String, Integer> getAcessosPorArquivo() {
        return acessosPorArquivo;
    }

    // Registra um acesso
    private static void registrarAcesso(String path){
        String nomeDoArquivo = path.substring(path.lastIndexOf("/") + 1);

        Object acessos = acessosPorArquivo.get(nomeDoArquivo);
        if(acessos == null) { 
            acessosPorArquivo.put(nomeDoArquivo, 1);
            return;
        }
        acessosPorArquivo.put(nomeDoArquivo, (int)acessos+1);
    }

    // Retorna um sobrenome
    public static List<String> getSobrenomes() {
        if(sobrenomes.isEmpty()){
            sobrenomes = lerSobrenomes();
        }

        return sobrenomes;
    }

    // Lê nomes em arquivo de acordo com o sexo
    public static List<String> lerNomes(char sexo){
        String path = "arquivos//nomes//";
        path += sexo == 'F' ? "femininos" : "masculinos" ;
        return ler(path+".csv");
    }

    // Lê sobrenomes
    public static List<String> lerSobrenomes(){
        String path = "arquivos//nomes//sobrenomes";
        return ler(path+".csv");
    }

    // Lê um intervalo de registros de um arquivo
    public static List<Registro> lerRegistrosNoIntervalo(String path, int primeiraLinha, int ultimaLinha) {
        int quantidadeLinhas = ultimaLinha - primeiraLinha;
        String[] linhas = new String[quantidadeLinhas];

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (int i = 0; i < primeiraLinha; i++) {
                br.readLine();
            }

            for (int i = 0; i < quantidadeLinhas; i++){
                linhas[i] = br.readLine();
            }
        }
        catch(IOException e){
          System.out.println(e);
        }
        return Registro.manyFromCSV(linhas);
    }

    // Deleta arquivos de uma execução anterior
    public static void deletarArquivosPrevios(){
        deletarArquivo("arquivos//dadosIniciais.csv");
        for(int i = 1; i < 9; i++){
            deletarArquivo("arquivos//caminho" + i + ".csv");
        }
        deletarArquivo("arquivos//dadosOrdenados.csv");
    }

    // Deleta um arquivo
    private static void deletarArquivo(String path){
        File arquivo = new File(path);
        arquivo.delete();
    }

}
