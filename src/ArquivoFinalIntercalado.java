import java.util.List;
import java.util.ArrayList;

public class ArquivoFinalIntercalado extends Cronometrado{
    private List<Registro> filaParaGravar;
    private final static int MAX_GRAVACOES_POR_VEZ = 100000;

    // Executa os processos de intercalação:
    public void executar(){
        this.filaParaGravar = new ArrayList<Registro>();
        cronometro.comecar();
        List<CaminhoExistente> caminhos = todosOsCaminhos();
        intercalaCaminhos(caminhos);
        cronometro.parar();
    }

    // Retorna o caminho do arquivo final:
    private static String finalPath() {
        return "arquivos//dadosOrdenados.csv";
    }

    // Intercala caminhos:
    private void intercalaCaminhos(List<CaminhoExistente> caminhos) {
        while(!caminhos.isEmpty()) {
            Registro menorRegistro = retiraMenorRegistro(caminhos);
            colocarNaFilaDeGravacaoNoRegistroFinal(menorRegistro);
        }
    }

    // Coloca um registro na lista de gravação:
    private void colocarNaFilaDeGravacaoNoRegistroFinal(Registro registro){
        filaParaGravar.add(registro);
        if(filaParaGravar.size() >= MAX_GRAVACOES_POR_VEZ){
            ArquivoUtils.gravarRegistros(finalPath(), filaParaGravar, true);
            filaParaGravar.clear();
        }
    }

    // Retira menor registro entre as listas que estão sendo intercaladas:
    private static Registro retiraMenorRegistro(List<CaminhoExistente> caminhos){
        CaminhoExistente menorCaminho = menorPrimeiroRegistro(caminhos);
        Registro menorRegistro = menorCaminho.primeiroRegistro();
        menorCaminho.getRegistros().remove(0);
        if (menorCaminho.isEmpty()){
            caminhos.remove(menorCaminho);
        }
        return menorRegistro;
    }

    // Verifica qual é o menor registro entre as listas:
    private static CaminhoExistente menorPrimeiroRegistro(List<CaminhoExistente> caminhos) {
        CaminhoExistente menorCaminho = caminhos.get(0);
        for(CaminhoExistente caminho : caminhos){
            if(caminho.comparaPrimeiroRegistro(menorCaminho) == -1){
                menorCaminho = caminho;
            }
        }
        return menorCaminho;
    }

    // Adiciona caminhos a uma lista:
    private static List<CaminhoExistente> todosOsCaminhos(){
        List<CaminhoExistente> caminhos = new ArrayList<>();
        for(int i=0; i<8; i++){
            CaminhoExistente caminho = new CaminhoExistente(i);
            caminhos.add(caminho);
        }

        return caminhos;
    }
}
