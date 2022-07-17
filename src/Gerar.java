import java.util.List;
import java.util.ArrayList;

public class Gerar {
	static public int inteiro(int min, int max){
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

	static public Registro registro(int id){
		Registro registro = new Registro();
		registro.setId(id);
		registro.preencherAleatorio();
		return registro;
	}

	static public List<Registro> registros(int quantidade){
		List<Registro> registros = new ArrayList<Registro>();
		for(int i = 0; i < quantidade; i++){
			registros.add((Registro) registro(i));
		}
		return registros;
	}

    static public String nome(){
		int maxChars = ((80-2)/3);
		String[] nomes = new String[3];
		for(int i = 0; i < 3; i++){
			nomes[i] = umNome(maxChars);
		}
		return String.join(" ", nomes);
	}

	static public String umNome(int maxChars){
		int charAmount = Gerar.inteiro(3, maxChars);
		String nome = "";
		for(int i = 0; i < charAmount; i++){
			int numero = Gerar.inteiro(97, 122);
			if(i == 0){
				nome += (char)(numero-32);//maiuscula
			}else{
				nome += (char)numero;
			}
		}
		return nome;
	}
    
    static public int idade(){
		return Gerar.inteiro(10, 50);
	}

	static public char sexo(){
		String letras = "MF";
		int numero = Gerar.inteiro(0, 1);
		char sexo = letras.charAt(numero);
		return sexo;
	}

	static public String cpf(){
		String cpf = "";
		for(int i = 0; i < 3; i++){
			cpf += String.format("%03d", Gerar.inteiro(0, 999));
			if (i!=2){ cpf += "."; };
		}

		cpf += String.format("-%02d", Gerar.inteiro(0, 99));
		return cpf;
	}

	static public String cep(){
		String cep = "";
		cep += String.format("%05d-", Gerar.inteiro(0, 99999));
		cep += String.format("%03d", Gerar.inteiro(0, 99));
		return cep;
	}
}
