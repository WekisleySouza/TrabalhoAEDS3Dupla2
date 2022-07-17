import java.util.ArrayList;
import java.util.List;

public class Tabela {
    private List<String> linhas;
    

    public Tabela(){
        linhas = new ArrayList<>();
    }
   
    public void adicionaLinha(String linha) {
        linhas.add(linha);
    }  

    public void adicionaNaLinha(int index, String extra) {
        String linha = linhas.get(index) + extra;
        linhas.set(index, linha);
    }

    public void adicionaNaUltimaLinha(String extra) {
        int index = linhas.size()-1;
        String linha = linhas.get(index) + extra;
        linhas.set(index, linha);
    }

    public void adicionarEspacos(int index, int quantidade) {
        String extra = "";
        for(int i = 0; i < quantidade; i++) {
            extra += " ";
        } 
        adicionaNaLinha(index, extra);
    }

    public void normalizaLinha(int index, int tamanhoEsperado){
        int tamanhoLinha = tamanhoLinha(index);
        int espacosFaltando = tamanhoEsperado - tamanhoLinha;
        adicionarEspacos(index, espacosFaltando);
    }

    public int tamanhoLinha(int index){
        return linhas.get(index).length();
    }

    public void normalizaTodasLinhas(int tamanhoEsperado){
        for(int i = 0; i < linhas.size(); i++) {
            normalizaLinha(i, tamanhoEsperado);
        }   
    }

    public static String inteiroPelaDireita(int inteiro, int tamanhoString){
        String inteiroString = String.valueOf(inteiro);
        int espacosFaltando = tamanhoString - inteiroString.length();
        for(int i = 0; i < espacosFaltando; i++) {
            inteiroString = " " + inteiroString;
        } 
        return inteiroString;
    }

    public void imprimir(){
        for(int i = 0; i < this.linhas.size(); i++) {
            System.out.println(this.linhas.get(i));
        }
    }
}
