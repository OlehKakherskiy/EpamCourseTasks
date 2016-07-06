package insertStrategy;

import entity.Process;
import entity.ProcessQueue;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class InsertStrategyFirstStream implements InsertStrategy {

    private ProcessQueue processQueue;

    @Override
    public void addProcess(Process process, ProcessQueue queue) {
        this.processQueue = queue;
        if (queue.getCpus().get(0).getProcessStreamID() == -1) {
            System.out.println("first stream adds process to first cpu");
            queue.getCpus().get(0).setNextProcessToExec(process);
            return;
        }

        System.out.println("first stream checks second cpu");
        if (cpu1ProcessesFirstStreamProcess(queue)) { //cpu1 is executing process from first stream
            tryAddToSecondCPU(process);
        } else { //spu2 is executing process from second stream
            System.out.println("process from first stream is lost");
            incrementLossCount();
        }
    }

    private void tryAddToSecondCPU(Process process) {
        if (cpu2isFree(processQueue)) {
            System.out.println("first stream adds process to second cpu");
            (processQueue.getCpus().get(1)).setNextProcessToExec(process); //TODO:
        } else {
            System.out.println("process from first stream is lost");
            incrementLossCount();
        }
    }

    private boolean cpu1ProcessesFirstStreamProcess(ProcessQueue queue) {
        System.out.println("second cpu is processing process from stream " + queue.getCpus().get(0).getProcessStreamID());
        return queue.getCpus().get(0).getProcessStreamID() == 1;
    }

    private boolean cpu2isFree(ProcessQueue queue) {
        return queue.getCpus().get(1).getProcessStreamID() == -1;
    }

    private void incrementLossCount() {
        processQueue.getQueueStatistics().incrementLossCount(0);
    }
}