package vetor;

public class TestarVetor {

	public static void main(String[] args) {
		// preencha esse metodo com codigo para testar a classe vetor.
		// À medida que voce evoluir no exercício o conteúdo deste mpetodo
		// também será modificado.

		Vetor<String> vetor1 = new Vetor<String>(5);
		vetor1.inserir("Ronaldd Matias");
		vetor1.inserir("Leda 2023.1");
		vetor1.inserir("Flamengo Campeão Mundial");
		vetor1.inserir("Futeb");
		vetor1.inserir("Eminem");
		System.out.println(vetor1);

		System.out.println(vetor1.isCheio());
		System.out.println(vetor1.isVazio());


		Vetor<Aluno> alunos = new Vetor<>(10);
		
		Aluno a1 = new Aluno("José", 5.0);
		Aluno a2 = new Aluno("Ronaldo", 3.2);
		Aluno a3 = new Aluno("Matias", 10.0);
		Aluno a4 = new Aluno("Araujo", 1.1);
		Aluno a5 = new Aluno("Henrique", 9.0);

		alunos.inserir(a1);
		alunos.inserir(a2);
		alunos.inserir(a3);
		alunos.inserir(a4);
		alunos.inserir(a5);

		alunos.remover(a5);

		try {
			alunos.procurar(a5);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}; 

		// System.out.println(alunos.maximo());
		System.out.println(alunos.minimo());


	}
}
