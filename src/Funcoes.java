public class Funcoes {
    public static int gera_numero_aleatorio(int min, int max){
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static long pegaTempo(){
        return (long) System.nanoTime();
    }

    public static int comparaRegistros(Registro a, Registro b){
        int cepA = Integer.parseInt(a.getCep().replace("-", ""));
        int cepB = Integer.parseInt(b.getCep().replace("-", ""));
        int resposta = 1;
        if(cepA < cepB){
            resposta = -1;
        }else if(cepA == cepB){
            if(a.getSexo() == 'F' && b.getSexo() == 'M'){
                resposta = -1;
            }else if(a.getSexo() == b.getSexo()){
                if(a.getIdade() < b.getIdade()){
                    resposta = -1;
                }else if(a.getIdade() == b.getIdade()){
                    int ordem = a.getNome().compareTo(b.getNome());
                    if(ordem == (-2)){
                        resposta = -1;
                    }else if(ordem == 0){
                        resposta = 0;
                    }
                }
            }
        }
        return resposta;
    }
}
