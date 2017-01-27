package testqueue;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import java.util.Iterator;

import queue.FifoQueue;

public class TestFifoQueue {
	private FifoQueue<Integer> myIntQueue;
	private FifoQueue<String> myStringQueue;
	private Iterator<String> itStrings;
	private Iterator<Integer> itInts;


	public void createIt(){
		itStrings = myStringQueue.iterator();
		itInts = myIntQueue.iterator();
	}

	@Before
	public void setUp() throws Exception {
		myIntQueue = new FifoQueue<Integer>();
		myStringQueue = new FifoQueue<String>();
		//skapar inga iterators då man vill ha lagt till element innan den skapas.
	}

	@After
	public void tearDown() throws Exception {
		myIntQueue = null;
		myStringQueue = null;
		itInts = null;
		itStrings = null;
	}

	/**
	 * Test if a newly created queue is empty.
	 */
	@Test
	public final void testNewFifoQueue() {
		assertTrue(myIntQueue.isEmpty());
		assertTrue(myIntQueue.size() == 0);
	}

	/** Test a single offer followed by a single peek. */
	@Test
	public final void testPeek() {
		myIntQueue.offer(1);
		int i = myIntQueue.peek();
		assertEquals("peek on queue of size 1", 1, i);
		assertTrue(myIntQueue.size() == 1);
	}

	/**
	 * Test a single offer followed by a single poll.
	 */
	@Test
	public final void testPoll() {
		myIntQueue.offer(1);
		int i = myIntQueue.poll();
		assertEquals("poll on queue of size 1", 1, i);
		assertTrue("Wrong size after poll", myIntQueue.size() == 0);
	}

	/**
	 * Test peek of empty queue.
	 */
	@Test
	public final void testPeekOfEmpty() {
		assertEquals("Front of empty queue not null", null, myIntQueue.peek());
	}

	/**
	 * Test poll of empty queue.
	 */
	@Test
	public final void testPollOfEmpty() {
		assertEquals("Poll of empty queue should return null", null, myIntQueue
				.poll());
	}

	/**
	 * Test that implementation works for a queue of strings.
	 */
	@Test
	public final void testStringQueue() {
		myStringQueue.offer("First");
		myStringQueue.offer("Second");
		myStringQueue.offer("Third");
		assertTrue("Wrong size of queue", myStringQueue.size() == 3);
		assertEquals("peek on queue of strings", "First", myStringQueue.peek());
		assertEquals("String First expected", "First", myStringQueue.poll());
		assertEquals("String Second expected", "Second", myStringQueue.poll());
		assertEquals("String Third expected", "Third", myStringQueue.poll());
		assertTrue("Queue of strings should be empty", myStringQueue.isEmpty());
	}


	/**
	 * Test that polling gives elements in right order.
	 */
	@Test
	public final void testOrder() {
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		myIntQueue.offer(3);
		myIntQueue.offer(4);
		myIntQueue.offer(5);
		for (int i = 1; i <= 5; i++) {
			int k = myIntQueue.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		assertTrue("Queue not empty", myIntQueue.isEmpty());
	}
	
	/**
	 * Test that polling all elements gives an empty queue.
	 */
	@Test
	public final void testMakeQueueEmpty() {
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		myIntQueue.poll();
		myIntQueue.poll();
		assertTrue("Wrong size after poll", myIntQueue.size() == 0);
		assertTrue("Queue not empty after poll", myIntQueue.isEmpty());
		myIntQueue.offer(3);
		myIntQueue.offer(4);
		assertTrue("Wrong size after offer", myIntQueue.size() == 2);
		for (int i = 3; i <= 4; i++) {
			int k = myIntQueue.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		assertTrue("Wrong size after poll", myIntQueue.size() == 0);
		assertTrue("Queue not empty after poll", myIntQueue.isEmpty());
	}

	/** Testar om iteratorns hasNext returnerar false vid tom kö.*/
	@Test
	public final void testIteratorOnEmpty() {
		//----------HasNext fungerar?

		//skapa iteratorer
		itStrings = myStringQueue.iterator();
		itInts = myIntQueue.iterator();

		//Strings
		assertTrue("hasNext returnerar true felaktigt.", itStrings.hasNext() == false);
		
		//Ints
		assertTrue("hasNext returnerar true felaktigt.", itInts.hasNext() == false);
		
		//---------Felaktigt bruk av Next() ger rätt error?
		try {
			itStrings.next();
		} catch (Exception e) {
			assertTrue("Inkorrekt hantering av next() på saknat element.",
					e instanceof NoSuchElementException);
		}
		try {
			itInts.next();
		} catch (Exception e) {
			assertTrue("Inkorrekt hantering av next() på saknat element.",
					e instanceof NoSuchElementException);
		}
	}


	@Test
	public final void testIteratorOneElement() {

		myIntQueue.offer(1);
		itInts = myIntQueue.iterator();

		assertTrue("iterators hasNext() doesn't return true", itInts.hasNext());
		assertEquals( "int iterator result isn't equal to 1 ",itInts.next().equals(1));
		assertFalse("iterators hasNext() doesn't return false after been emptied", itInts.hasNext());
	}

	@Test
	public final void testIteratorfilled(){

		int[] check = new int[]{1,2,5,2,4,7};
		int[] result = new int[6];

		myIntQueue.offer(1);
		myIntQueue.offer(2);
		myIntQueue.offer(5);
		myIntQueue.offer(2);
		myIntQueue.offer(4);
		myIntQueue.offer(7);

		itInts = myIntQueue.iterator();

		int i = 0;

		while(itInts.hasNext()){
			result[i] = itInts.next();
			i++;
		}

		assertArrayEquals("Array created with iterator isn't equal to int[]{1,2,5,2,4,7}", check, result);

	}

}
