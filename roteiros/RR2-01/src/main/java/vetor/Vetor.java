package vetor;

import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor<T extends Comparable<T>> { // -> O vetor vai guardar apenas objetos comparáveis

	// O array interno onde os objetos manipulados são guardados
	private T[] arrayInterno;

	// O tamanho que o array interno terá
	private int tamanho;

	// Indice que guarda a proxima posição vazia do array interno
	private int indice;

	// O Comparators a serem utilizados
	private Comparator<T> comparadorMaximo;
	private Comparator<T> comparadorMinimo;

	public Vetor(int tamanho) {
		super();
		this.tamanho = tamanho;
		this.indice = -1;
		this.arrayInterno = (T[]) new Comparable[tamanho];
	}

	public void setComparadorMaximo(Comparator comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(T o) {
		indice++;
		arrayInterno[indice] = o;
	}

	// Remove um objeto do vetor
	public T remover(T o) {
		T output = null;
		for(int i = 0; i < arrayInterno.length; i++){
			if(arrayInterno[i] != null){
				if (o.compareTo(arrayInterno[i]) == 0) {
				output = arrayInterno[i];
				arrayInterno[i] = null;
				indice--;
				return output;
			}
			}
			
		}
		throw new RuntimeException("Elemento não encontrado");
	}

	// Procura um elemento no vetor
	public T procurar(T o) {
		for(int i = 0; i < arrayInterno.length; i++){
			if(arrayInterno[i] != null){
				if(o.compareTo(arrayInterno[i]) == 0) {
				return arrayInterno[i];
			}
			}
			
		}
		throw new RuntimeException("Elemento não encontrado");
	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		return indice == -1;
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		return indice == tamanho -1;
	}

	public T maximo(){
		T output = null;
		if(!isVazio()){
			output = arrayInterno[0];
			for(int i = 0; i <= arrayInterno.length; i++){
				if(arrayInterno[i] != null){
					if(comparadorMaximo.compare(output, arrayInterno[i]) < 0){
					output = arrayInterno[i];
				}
				}
				
			}
		}
		return output;
	}

	public T minimo(){
		T output = null;

		if(!isVazio()){
			output = arrayInterno[0];
			for(int i = 0; i < arrayInterno.length; i++){
				if(comparadorMinimo.compare(output, arrayInterno[i]) < 0){
					output = arrayInterno[i];
				}
			}
		}
		return output;
	}

}

class ComparadorMaximo implements Comparator<Aluno>{

	@Override
	public int compare(Aluno aluno1, Aluno aluno2) {
		return (int) (aluno1.getMedia() - aluno2.getMedia());	
	}
}

class ComparadorMinimo implements Comparator<Aluno>{

	@Override
	public int compare(Aluno aluno1, Aluno aluno2) {
		return (int) (aluno2.getMedia() - aluno1.getMedia());
	}
} 