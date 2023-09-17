package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()){
			throw new QueueOverflowException();
		}

		try{
			this.stack1.push(element);
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public T dequeue() throws QueueUnderflowException {

		if(isEmpty()){
			throw new QueueUnderflowException();
		}

		T head = null;
		desempilhaParaStack2(this.stack1, this.stack2);

		try{
			head = this.stack2.pop();
		} catch (StackUnderflowException e){
			e.printStackTrace();
		}
		empilhaParaStack1(this.stack1, this.stack2);

		return head;
	}

	private void empilhaParaStack1(Stack<T> stack1, Stack<T> stack2) {
		while(!this.stack2.isEmpty()){
			T elemento = null;
			try{
				elemento = this.stack2.pop();
			} catch (StackUnderflowException e){ // pega o elemento a ser removido
				e.printStackTrace();
			}

			try {
				this.stack1.push(elemento);
			} catch (StackOverflowException e){ // adiciona o elemento a ser removido
				e.printStackTrace();
			}
			
		}
	}

	private void desempilhaParaStack2(Stack<T> stack1, Stack<T> stack2) {
		while(!this.stack1.isEmpty()){
			T elemento = null;

			try{
				elemento = this.stack1.pop(); // pega o elemento a ser removido
			} catch (StackUnderflowException e){
				e.printStackTrace();
			}

			try{
				this.stack2.push(elemento);	
			} catch (StackOverflowException e){ // adiciona o elemento a ser removido
				e.printStackTrace();
			}
		}
	}

	@Override
	public T head() { 
		if(isEmpty()){
			return null;
		}
		T result;
		
		desempilhaParaStack2(this.stack1, this.stack2);
		result = this.stack2.top();
		empilhaParaStack1(this.stack1, this.stack2);
		return result;
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

}
