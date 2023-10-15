package adt.linkedList;

import java.util.ArrayList;
import java.util.List;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		int size;
		if (this.isEmpty())
			size = 0;
		else
			size = 1 + this.getNext().size();

		return size;
	}

	@Override
	public T search(T element) {
		T out = null;
		if (element != null && !this.isEmpty()) {
			if (this.getData().equals(element)) {
				out = this.getData();
			} else {
				out = this.getNext().search(element);
			}
		}
		return out;
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			data = element;
			next = new RecursiveSingleLinkedListImpl<>();
		} else {
			next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			if (getData().equals(element)) {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			} else {
				this.getNext().remove(element);
			}
		}
	}

	private void format(List<T> list) {
		if (!isEmpty()) {
			list.add(data);
			next.format(list);
		}
	}

	@Override
	public T[] toArray() {
		List<T> arr = new ArrayList<>();
		format(arr);
		return (T[]) arr.toArray();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
