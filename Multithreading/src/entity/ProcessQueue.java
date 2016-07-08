package entity;

import insertStrategy.InsertStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class represents queue, to which processes are added from {@link ProcessStream} and from
 * which this processes are polled by {@link CPU} for processing. While adding new process
 * to this queue, it can add it to internal list or directly to the cpu (the strategy is encapsulated
 * in {@link InsertStrategy}).
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ProcessQueue {

    /**
     * insertion strategies for each {@link CPU} object
     */
    private Map<Integer, InsertStrategy> insertionBehavior;

    /**
     * statistical information about queue
     */
    private QueueStatistics queueStatistics;

    /**
     * queue
     */
    private List<Process> queue = new LinkedList<>();

    /**
     * cpu threads
     */
    private List<CPU> cpus;

    /**
     * lock object
     */
    public static final Object lock = new Object();

    public ProcessQueue(Map<Integer, InsertStrategy> insertionBehavior, QueueStatistics queueStatistics) {
        this.insertionBehavior = insertionBehavior;
        this.queueStatistics = queueStatistics;
    }

    /**
     * adds process using insertion strategy
     *
     * @param process process that will be added for processing
     */
    public void addProcess(Process process) {
        int processStreamID = process.getStreamID();
        if (!insertionBehavior.containsKey(processStreamID)) {
            throw new IllegalArgumentException("can't add process from " + processStreamID + " stream. No insertion strategy");
        }
        insertionBehavior.get(processStreamID).addProcess(process, this);
    }


    /**
     * adds process to queue and notifies all waiting objects
     *
     * @param process process to be added to the queue
     */
    public synchronized void addProcessToQueue(Process process) {
        queue.add(process);
        System.out.println("notifyAll");
        synchronized (lock) {
            updateMaxQueueLength();
            lock.notifyAll();
        }
    }

    /**
     * updates maximum queue's length value, if current queue length is bigger than
     * maximum was
     */
    private void updateMaxQueueLength() {
        int queLength = queue.size() + 1;
        System.out.println("queLength = " + queLength);
        System.out.println("queueStatistics = " + queueStatistics.getMaxQueueLength());
        if (queLength > queueStatistics.getMaxQueueLength()) {
            queueStatistics.setMaxQueueLength(queLength);
        }
    }

    /**
     * returns first element from the queue. If there's no elements - thread is waiting until
     * it'll be notified. Possibly, after notifying null could be returned.
     *
     * @return process from the first position of the queue, null as a result of race condition
     */
    public Process pollLast() {
        Process result = null;
        synchronized (lock) {
            if (queue.size() == 0) {
                try {
                    System.out.println("cpu " + ((CPU) Thread.currentThread()).getCpuID() + "waits");
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
            result = queue.size() == 0 ? null : queue.get(0);
            if (result != null) {
                queue.remove(0);
            }
        }
        return result;
    }

    public List<CPU> getCpus() {
        return cpus;
    }

    public void setCpus(List<CPU> cpus) {
        this.cpus = cpus;
    }

    public QueueStatistics getQueueStatistics() {
        return queueStatistics;
    }
}