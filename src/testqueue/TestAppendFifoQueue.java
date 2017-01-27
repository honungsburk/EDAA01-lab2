package testqueue;


        import static org.junit.Assert.*;
        import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;

        import queue.FifoQueue;

public class TestAppendFifoQueue {
    private FifoQueue<Integer> firstQueue;
    private FifoQueue<Integer> secondQueue;

    @Before
    public void setUp() throws Exception {
        firstQueue= new FifoQueue<Integer>();
        secondQueue = new FifoQueue<Integer>();
    }

    @After
    public void tearDown() throws Exception {
        firstQueue = null;
        secondQueue = null;
    }


    @Test
    public void testTwoEmpty(){
        assertFalse("Should not be able to append two empty queues.",firstQueue.append(secondQueue));
        assertTrue("Size isn't 0.", firstQueue.size() == 0);
    }

    @Test
    public void testFirstEmpty(){
        secondQueue.offer(1);
        secondQueue.offer(2);
        secondQueue.offer(3);
        secondQueue.offer(4);
        secondQueue.offer(5);

        assertTrue("Could not append a queue on an empty queue",firstQueue.append(secondQueue));
        assertTrue("Size isn't correct, " + firstQueue.size() + " shold be" + 5, firstQueue.size() == 5);
    }

    @Test
    public void testSecondEmpty(){
        firstQueue.offer(1);
        firstQueue.offer(2);
        firstQueue.offer(3);
        firstQueue.offer(4);
        firstQueue.offer(5);

        assertFalse("Should not be able to append an empty queue", firstQueue.append(secondQueue));
        assertTrue("Size isn't correct, " + firstQueue.size() + " shold be" + 5, firstQueue.size() == 5);
    }

    @Test
    public void testTwoQueues(){
        firstQueue.offer(1);
        firstQueue.offer(2);
        firstQueue.offer(3);
        firstQueue.offer(4);
        firstQueue.offer(5);

        secondQueue.offer(1);
        secondQueue.offer(2);
        secondQueue.offer(3);
        secondQueue.offer(4);
        secondQueue.offer(5);

        assertTrue("Could not append two non-empty queues", firstQueue.append(secondQueue));
        assertTrue("Size isn't correct, " + firstQueue.size() + " shold be" + 10, firstQueue.size() == 10);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendingOnItSelf(){
        firstQueue.offer(1);
        firstQueue.offer(2);
        firstQueue.offer(3);
        firstQueue.offer(4);
        firstQueue.offer(5);

            firstQueue.append(firstQueue);

    }
}
