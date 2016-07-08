package insertStrategy;

import entity.Process;
import entity.ProcessQueue;

/**
 * Class encapsulates strategy of adding process from the first {@link entity.ProcessStream}
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class InsertStrategyFirstStream implements InsertStrategy {

    /**
     * processQueue, to which process can be added
     */
    private ProcessQueue processQueue;

    /**
     * {@inheritDoc}
     * <p>
     * <p>
     * if first cpu is free, adds process directly to cpu for execution.
     * If {@link #cpu1ProcessesFirstStreamProcess()} is true -
     * {@link #tryAddToSecondCPU(Process)} invoked. Else process is lost.
     * </p>
     *
     * @param process process that should be executed
     * @param queue   queue, to which process can be added
     */
    @Override
    public void addProcess(Process process, ProcessQueue queue) {
        this.processQueue = queue;
        if (queue.getCpus().get(0).getProcessStreamID() == -1) {
            System.out.println("first stream adds process to first cpu");
            queue.getCpus().get(0).setNextProcessToExec(process);
            return;
        }

        System.out.println("first stream checks second cpu");
        if (cpu1ProcessesFirstStreamProcess()) { //cpu1 is executing process from first stream
            tryAddToSecondCPU(process);
        } else { //spu2 is executing process from second stream
            System.out.println("process from first stream is lost");
            incrementLossCount();
        }
    }

    /**
     * Checks whether the first cpu is executing process from the first stream
     *
     * @return true if process is executing process from the first stream
     */
    private boolean cpu1ProcessesFirstStreamProcess() {
        System.out.println("second cpu is processing process from stream " + processQueue.getCpus().get(0).getProcessStreamID());
        return processQueue.getCpus().get(0).getProcessStreamID() == 1;
    }

    /**
     * if the second cpu is free, directly adds process to it, otherwise process is lost.
     *
     * @param process current process
     */
    private void tryAddToSecondCPU(Process process) {
        if (cpu2isFree()) {
            System.out.println("first stream adds process to second cpu");
            processQueue.getCpus().get(1).setNextProcessToExec(process);
        } else {
            System.out.println("process from first stream is lost");
            incrementLossCount();
        }
    }

    /**
     * checks whether the second cpu isn't executing any process.
     *
     * @return true if the second cpu is free
     */
    private boolean cpu2isFree() {
        return processQueue.getCpus().get(1).getProcessStreamID() == -1;
    }

    /**
     * increments loss count for the first cpu
     */
    private void incrementLossCount() {
        processQueue.getQueueStatistics().incrementLossCount(0);
    }
}