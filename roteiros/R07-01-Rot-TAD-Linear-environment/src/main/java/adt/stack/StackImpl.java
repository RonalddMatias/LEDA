package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() { // retorna o elemento do top
		T top = null;
		if(!isEmpty()){
			top = this.array[this.top];
		}
		return top;
	}

	@Override
	public boolean isEmpty() {
		return top == -1; // se o top for -1 ele retorna true (vazia), se não false (tem elemento)
	}

	@Override
	public boolean isFull() {
		boolean result = false;
		if(this.top == this.array.length-1){
			result = true;
		}
		return result;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		// ver se tem espaço
		if(isFull()){
			throw new StackOverflowException();
		} else {
			this.top += 1;
			this.array[top] = element;
		}
		
	}

	@Override
	public T pop() throws StackUnderflowException {
		// tenho que ver se ela não esta vazia para poder remover elementos
		if(isEmpty()){
			throw new StackUnderflowException();
		}
		return this.array[this.top--];
	}

}
