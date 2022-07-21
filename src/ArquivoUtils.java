import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtils {
    private static List<List<String>> nomes = new ArrayList<List<String>>();
    private static List<String> sobrenomes = new ArrayList<String>();

    public static void gravarRegistros(String path, List<Registro> registros, boolean adicionar){
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, adicionar))){
			for(Registro registro : registros){
				bw.write(registro.toCSV());
				bw.newLine();
			}
		}
		catch(IOException erro){
			erro.printStackTrace();
		}
	}

    public static void gravarRegistro(String path, Registro registro, boolean adicionar){
        List<Registro> registros = new ArrayList<Registro>();
        registros.add(registro);

        gravarRegistros(path, registros, adicionar);
    }

    public static List<String> ler(String path) {
        List<String> linhas = new ArrayList<String>();
        String linha = "";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while(true){
                linha = br.readLine();
                if (linha == null) { break; };
                linhas.add(linha);
            }
        }
        catch(IOException e){
          System.out.println(e);
        }

        return linhas;
    }

    public static List<Registro> lerRegistros(String path) {
        List<String> linhas = ler(path);
        return Registro.manyFromCSV(linhas.toArray(new String[0]));
    }

    public static List<String> getNomes(char sexo) {
        int index = sexo == 'F' ? 0 : 1 ;
        if(nomes.isEmpty()){
            nomes.add(lerNomes('F'));
            nomes.add(lerNomes('M'));
        }

        return nomes.get(index);
    }

    public static List<String> getSobrenomes() {
        if(sobrenomes.isEmpty()){
            sobrenomes = lerSobrenomes();
        }

        return sobrenomes;
    }

    public static List<String> lerNomes(char sexo){
        String path = "arquivos//nomes//";
        path += sexo == 'F' ? "femininos" : "masculinos" ;
        return ler(path+".csv");
    }

    public static List<String> lerSobrenomes(){
        String path = "arquivos//nomes//sobrenomes";
        return ler(path+".csv");
    }

    // Criei essa função também:
    public static String lerNome(String path, int linha){
        String parteNome = "";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (int i = 0; i < linha; i++) {
                br.readLine();
            }
            parteNome = br.readLine();
        }
        catch(IOException e){
          System.out.println(e);
        }
        return parteNome;
    }

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

    public static void deletarArquivosPrevios(){
        deletarArquivo("arquivos//dadosIniciais.csv");
        for(int i = 1; i < 9; i++){
            deletarArquivo("arquivos//caminho" + i + ".csv");
        }
        deletarArquivo("arquivos//dadosOrdenados.csv");
    }

    private static void deletarArquivo(String path){
        File arquivo = new File(path);
        arquivo.delete();
    }

}
