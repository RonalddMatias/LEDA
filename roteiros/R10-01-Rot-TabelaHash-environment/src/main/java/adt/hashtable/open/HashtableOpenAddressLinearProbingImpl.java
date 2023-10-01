package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}


	// com probingLinear
	@Override
	public void insert(T element) {
		//Verificar se ta cheia
		if(isFull()){
			throw new HashtableOverflowException();
		} else {
			if(element != null){
				int probe = 0;

				while(probe < table.length){
					int hash = hashFunction(element, probe);

					if(this.table[hash] == null || this.table[hash].equals(deletedElement)){
						this.table[hash] = element;
						this.elements++;
						break; // parar o meu loop
					}
					probe++; // se ja tiver um elemento(Colisão) eu devo andar com o meu probe
					this.COLLISIONS += 1;
				}
			}
			
		}
	}
 
	@Override
	public void remove(T element) {
		if(!isEmpty() && element != null){
			int probe = 0;

			while(probe < this.table.length){
				int hash = hashFunction(element, probe);


				// vendo se o elemento é igual 
				if(this.table[hash] != null){
					if(this.table[hash].equals(element)){
						this.table[hash] = new DELETED();
						this.elements--;
						break;
					}
				}
				probe++;
			}
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		if(element != null){
			int probe = 0;

			while(probe < this.table.length){
				int hash = hashFunction(element, probe);

				if(this.table[hash] != null){
					if(this.table[hash].equals(element)){
						result = (T) this.table[hash];
						break;
					}
				}
				probe++;
			}
		}
		return result;
	}

	@Override
	public int indexOf(T element) { // retorna o hash do elemento
		int index = -1;
		if(!isEmpty() && element != null){
			int probe = 0;
			
			while(probe < table.length){
				int hash = hashFunction(element, probe);

				if(this.table[hash] != null){
					if(this.table[hash].equals(element)){
						index = hash;
						break;
					}
				}
				probe++;
			}
		}
		return index;
	}

	private int hashFunction(T element, int probe){
		return ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, probe);
	}

}
