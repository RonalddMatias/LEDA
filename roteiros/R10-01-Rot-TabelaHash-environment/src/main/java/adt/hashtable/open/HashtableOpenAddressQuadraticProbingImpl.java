package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(isFull()){
			throw new HashtableOverflowException();
		} else {
			if(element != null && search(element) == null){
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
	public int indexOf(T element) {
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
