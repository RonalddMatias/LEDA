package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(element != null){
			if(this.isFull()){
				throw new QueueOverflowException();
			}
			this.list.insert(element);
		}
		
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(this.isEmpty()){
			throw new QueueUnderflowException();
		}
		T result = this.head();
		this.list.removeFirst();
		return result;
	}

	@Override
	public T head() {
		T result = null;
		if (!list.isEmpty()) {
			// pegando o primeiro
			result = list.toArray()[0];
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.size ==  this.list.size();
	}

}
