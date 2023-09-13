package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isEmpty()){
			this.head++;
			this.tail++;
			this.array[tail] = element;
			this.elements++;
		}

		if(!isFull()){
			this.tail = (this.tail + 1) % this.array.length;
			this.array[this.tail] = element;
			this.elements++;
		} else{
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result;
		if(!isEmpty()){
			result = array[this.head];
			this.head = (this.head + 1) % this.array.length;
			this.elements--;
			return result;

		} else {
			throw new QueueUnderflowException();
		}
	}

	@Override
	public T head() {
		T result;
		if(isEmpty()){
			result = null;
		} else {
			result = this.array[head];
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return this.elements == 0;
	}

	@Override
	public boolean isFull() {
		return this.elements == this.array.length;
	}

}
