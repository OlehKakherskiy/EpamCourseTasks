package entity;

import insertStrategy.InsertStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ProcessQueue {

    private Map<Integer, InsertStrategy> insertionBehavior;

    private QueueStatistics queueStatistics;

    private List<Process> queue = new LinkedList<>();

    private List<CPU> cpus;

    public static final Object lock = new Object();

    public ProcessQueue(Map<Integer, InsertStrategy> insertionBehavior, QueueStatistics queueStatistics) {
        this.insertionBehavior = insertionBehavior;
        this.queueStatistics = queueStatistics;
    }

    public void addProcess(Process process) {
        int processStreamID = process.getStreamID();
        if (!insertionBehavior.containsKey(processStreamID)) {
            throw new IllegalArgumentException("can't add process from " + processStreamID + " stream. No insertion strategy");
        }
        insertionBehavior.get(processStreamID).addProcess(process, this);
    }


    public synchronized void addProcessToQueue(Process process) {
        queue.add(process);
//        System.out.println("notifyAll");
//        synchronized (lock) {
        updateMaxQueueLength();
//            lock.notifyAll();
//        }
    }

    private void updateMaxQueueLength() {
        int queLength = queue.size() + 1;
        System.out.println("queLength = " + queLength);
        System.out.println("queueStatistics = " + queueStatistics.getMaxQueueLength());
        if (queLength > queueStatistics.getMaxQueueLength()) {
            queueStatistics.setMaxQueueLength(queLength);
        }
    }

    public synchronized Process pollLast() {
//        synchronized (lock) {
        if (queue.size() == 0) {
            return null;
//                try {
//                    System.out.println("cpu " + ((CPU) Thread.currentThread()).getCpuID() + "waits");
//                    lock.wait();
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }

        }//tODO:
        Process buffer = queue.size() == 0 ? null : queue.get(queue.size() - 1);
        if (buffer != null) {
            queue.remove(queue.size() - 1);
        }
        return buffer;
//        }
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