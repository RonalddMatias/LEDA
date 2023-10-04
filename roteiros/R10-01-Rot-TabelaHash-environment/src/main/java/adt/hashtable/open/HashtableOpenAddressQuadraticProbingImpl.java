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
				boolean found = false;

				while(!found && probe < table.length){
					int hash = hashFunction(element, probe);

					if(this.table[hash] == null || this.table[hash].equals(deletedElement)){
						this.table[hash] = element;
						this.elements++;
						found = true; 
					} else {
						probe++; 
						this.COLLISIONS += 1;
					}
					
				}
			}
			
		}
	}

	@Override
	public void remove(T element) {
		if(!isEmpty() && element != null){
			int index = indexOf(element);
			 if(index != -1){ 
				this.table[index] = new DELETED();
				--this.elements; 
			 }
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		if (element != null) {
			int index = indexOf(element);
			if (index != -1)
				result = (T) this.table[index];
		}
		return result;
	}

	@Override
	public int indexOf(T element) {
		int result = -1;
		if (element != null) {
			int i = 0;
			while (result == -1 && i < this.capacity()) {
				int hash = hashFunction(element, i);
	
				if (table[hash] != null && table[hash].equals(element)) {
					result = hash;
				} else {
					++i;
				}
			}
		}
		return result;
	}

	private int hashFunction(T element, int probe){
		return ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, probe);
	}
}
