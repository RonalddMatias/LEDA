package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.getData() == null;
	}

	@Override
	public int size() {
		int size = 0;

		if (isEmpty()){
			return size;
		} else {
			SingleLinkedListNode<T> aux = getHead(); // é necessário para não perder a referência
			while(!aux.isNIL()){ // enquanto não for nulo, incrementa
				size++;
				aux = aux.getNext(); // apontando para o proximo
			}
			return size;	
		}
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = this.getHead();

		if(element != null){
			while(!aux.isNIL() && !aux.getData().equals(element)){
				aux = aux.getNext();
			}
		}

		return aux.getData();

		
	}

	@Override
	public void insert(T element) {
		if(element != null){
			SingleLinkedListNode<T> aux = getHead();
			if(aux.isNIL()){
				this.setHead(new SingleLinkedListNode<T>(element, aux));
			} else {
				while(!aux.isNIL()){ // enquanto o auxiliar não for nulo
					aux = aux.getNext();
				}

				aux.setData(element); // setando o valor
				aux.setNext(new SingleLinkedListNode<T>()); // apontando para outro node

			}
		}
		
		// verificar se o head é nulo
		// se for nulo, eu crio o meu novo no e ja faço todas as ref.
		// senão, eu percorro até o fim da lista (.next == null)
	}

	@Override
	public void remove(T element) {
		
		if(element != null && !isEmpty()){
			SingleLinkedListNode<T> aux = getHead();

			if(aux.getData().equals(element)){ // se o head for o elemento procurado eu ja seto ele apontando para o proximo
				this.setHead(aux.getNext());
			} else {
				while(!aux.isNIL() && !aux.getData().equals(element)){
					aux = aux.getNext();
				}
				if(!aux.isNIL()){
					SingleLinkedListNode<T> nextAux = aux.getNext();
					aux.setData(nextAux.getData());
					aux.setNext(nextAux.getNext());
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		int count = 0;
		SingleLinkedListNode<T> node = this.getHead();

		while (!node.isNIL()) {
			array[count++] = node.getData();
			node = node.getNext();
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
