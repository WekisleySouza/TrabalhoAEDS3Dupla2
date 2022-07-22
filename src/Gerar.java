import java.util.List;
import java.util.ArrayList;

public class Gerar {
	// Gera número inteiro
	static public int inteiro(int min, int max){
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

	// Gera um registro
	static public Registro registro(int id){
		Registro registro = new Registro();
		registro.setId(id);
		registro.preencherAleatorio();
		return registro;
	}

	// Gera um certo número de registros
	static public List<Registro> registros(int quantidade){
		List<Registro> registros = new ArrayList<Registro>();
		for(int i = 0; i < quantidade; i++){
			registros.add((Registro) registro(i));
		}
		return registros;
	}

	// Gera um nome
	static public String nome(char sexo){
		List<String> nomes = ArquivoUtils.getNomes(sexo);
		int size = nomes.size();
		int index = inteiro(0, size-1);
		return nomes.get(index);

	}

	// Gera um sobrenome
	static public String sobrenome(){
		List<String> sobrenomes = ArquivoUtils.getSobrenomes();
		int size = sobrenomes.size();
		int index = inteiro(0, size-1);
		return sobrenomes.get(index);
	}

	// Gera um nome completo
	static public String nomeCompleto(char sexo){
        String completo = String.format(
			"%s %s %s",
			nome(sexo),
			sobrenome(),
			sobrenome()
			);
		return completo;
	}
    
	// Gera idade
    static public int idade(){
		return Gerar.inteiro(10, 50);
	}

	// Gera sexo
	static public char sexo(){
		String letras = "MF";
		int numero = Gerar.inteiro(0, 1);
		char sexo = letras.charAt(numero);
		return sexo;
	}

	// Gera cpf
	static public String cpf(){
		String cpf = "";
		for(int i = 0; i < 3; i++){
			cpf += String.format("%03d", Gerar.inteiro(0, 999));
			if (i!=2){ cpf += "."; };
		}

		cpf += String.format("-%02d", Gerar.inteiro(0, 99));
		return cpf;
	}

	// Gera cep
	static public String cep(){
		String[] ceps = {
			"36180-000", "25500-000", "36140-000", "37510-234", "12240-000",
			"34567-111", "76456-322", "45654-987", "98765-216", "40000-000"
			};
			
		return ceps[Gerar.inteiro(0, 10-1)];
	}
}
