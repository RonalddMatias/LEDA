package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		T result = null;
		if(!isEmpty()){
			result = array[0];
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		boolean result = false;
		if(this.tail == this.array.length-1){
			result = true;
		}
		return result;
	}

	// mover todos os elementos da esquerda 
	private void shiftLeft() {
		for(int i = 0; i <= this.tail; i++){
				array[i] = array[i+1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {

		if(!isFull()){
			this.array[++this.tail] = element;
		} else {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result;
		if(!isEmpty()){
			result = array[0];	
			shiftLeft();
			tail--;
			return result;
		} else {
			throw new QueueUnderflowException();
		}

		
	}

}
