package insertStrategy;

import entity.CPU;
import entity.Process;
import entity.ProcessQueue;

/**
 * Class encapsulates strategy of adding process from the second {@link entity.ProcessStream}
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class InsertStrategySecondStream implements InsertStrategy {

    /**
     * {@inheritDoc}
     * <p>
     * <p>
     * if the second cpu is free, adds directly process to this cpu, otherwise
     * adds process to the queue
     * </p>
     *
     * @param process process that should be executed
     * @param queue   queue, to which process can be added
     */
    @Override
    public void addProcess(Process process, ProcessQueue queue) {
        CPU cpu2 = queue.getCpus().get(1);
        System.out.println("stream2 checks cpu2 process number execution");
        if (cpu2.getProcessStreamID() == -1) {
            System.out.println("stream2: cpu2 is free");
            cpu2.setNextProcessToExec(process);
        } else {
            System.out.println("stream2: cpu2 is busy. Add to queue");
            queue.addProcessToQueue(process);
        }
    }
}