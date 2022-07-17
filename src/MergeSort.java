import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    private List<Registro> registrosMerge;
    private int acessos = 0;
    private int comparacoes = 0;
    private int trocas = 0;
    private Cronometro cronometro = new Cronometro();
    
    public MergeSort(List<Registro> listaRegistros){
        this.registrosMerge = new ArrayList<Registro>(listaRegistros);
        cronometro.comecar();
        this.performaOrdenacao(listaRegistros, this.registrosMerge, 0, listaRegistros.size() - 1);
        cronometro.parar();
    }


    // Ordena o vetor usando MergeSort
    private void performaOrdenacao(List<Registro> registros, List<Registro> registrosMerge, int inicio, int fim) {
        if(inicio < fim) {
            this.comparacoes++;
            int meio = (inicio + fim) / 2;
            performaOrdenacao(registros, registrosMerge, inicio, meio);
            performaOrdenacao(registros, registrosMerge, meio + 1, fim);
            merge(registros, registrosMerge, inicio, meio, fim);
        }
    }

    // Método que realiza as trocas dp MergeSort.
    public void merge(List<Registro> registros, List<Registro> registrosMerge, int inicio, int meio, int fim) {
        for(int k = inicio; k <= fim; k++) {
            registrosMerge.set(k, registros.get(k));
            acessos++;
        }
        int i = inicio;
        int j = meio + 1;
        
        for(int k = inicio; k <= fim; k++) {
            if(i > meio) {
                registros.set(k, registrosMerge.get(j++));
                this.acessos++;
                this.trocas++;
                this.comparacoes++;

            }else if (j > fim){
                registros.set(k, registrosMerge.get(i++));
                this.acessos++;
                this.trocas++;
                this.comparacoes += 2;

            }else if(Registro.comparaRegistros(registrosMerge.get(i), registrosMerge.get(j)) == -1) {
                registros.set(k, registrosMerge.get(i++));
                this.trocas++;
                this.acessos += 3;
                this.comparacoes += 3;
            }else {
                registros.set(k, registrosMerge.get(j++));
                this.trocas++;
                this.acessos += 3;
                this.comparacoes += 3;
            }
        }
    }

    public long getTempo() { 
        return cronometro.getTempo(); 
    }

    // GETTER E SETTTERS
    public int getTrocas() { return trocas; }

    public int getAcessos() { return acessos; }

    public int getComparacoes() { return comparacoes; }
    
}