package queue;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		last = null;
		size = 0;
	}

	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new Iterator<E>(){
		    int index = 0;
            QueueNode<E> currentNode = last;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public E next() {
                if(hasNext()){
                    currentNode = currentNode.next;
                    index++;
                    return currentNode.element;
                }
                else throw new NoSuchElementException("synd");
            }
        };
	}

	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	x the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E x) {
		QueueNode<E> newNode = new QueueNode<>(x);
		if (last != null) {
			newNode.next = last.next;
			last.next = newNode;
			last = newNode;
		} else {
			last = newNode;
			newNode.next = newNode;
		}
		size++;
		return true;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if (size == 1) {
			E oldest = last.element;
			last = null;
			size--;
			return oldest;
		} else if (last != null) {
			QueueNode<E> oldest = last.next;
			last.next = oldest.next;
			size--;
			return oldest.element;
		}
		else return null;
	}

	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if (last != null)
			return last.next.element;
		else return null;
	}

	public boolean append(FifoQueue<E> other){	//Vi måste göra så att den andra kön töms samtidigt. Står i uppgiften.

		if(other.last == null ) return false;
		else if(this.last == null) {
			this.last = other.last;
            this.size = other.size;
			return true;
		} else if(other == this) throw new IllegalArgumentException("You can not append a queue on it self");
		else {
			QueueNode<E> otherfirst = other.last.next;
			other.last.next = this.last.next;
			this.last.next = otherfirst;
			this.last = other.last;
            this.size += other.size;
			return true;
		}
	}


	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}

	}

}
