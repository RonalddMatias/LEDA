package adt.linkedList;

import java.util.Arrays;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl(){
		super.head = new DoubleLinkedListNode<T>();
		this.last = new DoubleLinkedListNode<T>();
	}

	@Override
	public T search(T element){
		T retorno = null;
		if (element != null) {
			DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) this.getHead();
			DoubleLinkedListNode<T> auxLast = (DoubleLinkedListNode<T>) this.getLast();	

			while(auxHead != auxLast && auxHead.next != auxLast &&  auxHead.data != element && auxLast.data != element){ // andando com o head e o last ao mesmo tempo
				auxHead = (DoubleLinkedListNode<T>) auxHead.next;
				auxLast = auxLast.previous;
			}
			
			if(auxHead.data == element){
				 retorno = auxHead.data;
			}
			if(auxLast.data == element){
				retorno = auxLast.data;
			}
		}

		return retorno;
	}

	@Override
	public void insert(T element){
		if (element != null) {
			if (isEmpty()) {
				this.last = new DoubleLinkedListNode<T>(element,
				new DoubleLinkedListNode<>(), new DoubleLinkedListNode<>());

				this.head = this.last;
			} else {
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element,
				new DoubleLinkedListNode<>(), this.last);
	
				this.last.next = newNode;
				setLast(newNode);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if(element != null) {
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>();
			newHead.setData(element); // setando o valor do novo node
			newHead.setPrevious(new DoubleLinkedListNode<>()); // setando o previus como um novo node NIL
			newHead.setNext(this.getHead()); 
			((DoubleLinkedListNode<T>) head).setPrevious(newHead);
			newHead.getPrevious().setNext(newHead);
			
			if(this.head.isNIL()) {
				this.setLast(newHead);;
			}
			this.setHead(newHead);
		}
	}

	@Override
	public void removeFirst() {
		if(!isEmpty() && !this.getHead().isNIL()){
			this.setHead(this.getHead().getNext()); // setando o head como sendo o proximo

			if(this.getHead().isNIL()){
				
			}
		}
	}

	@Override
	public void removeLast() {
		if(!this.isEmpty() && !this.getLast().isNIL()) {
			this.setLast(this.getLast().getPrevious());
			
			if(this.getLast().isNIL()) {
				this.setHead(this.getLast());
				
			}else {
				this.getLast().setNext(new DoubleLinkedListNode<>());
				((DoubleLinkedListNode<T>) this.getLast().getNext()).setPrevious(this.getLast());
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
}
