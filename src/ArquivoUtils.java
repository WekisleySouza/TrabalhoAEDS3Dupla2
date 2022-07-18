import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtils {
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

    public static List<Registro> ler(String path) {
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

        return Registro.manyFromCSV(linhas.toArray(new String[0]));
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

    public static List<Registro> lerIntervalo(String path, int primeiraLinha, int ultimaLinha) {
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
