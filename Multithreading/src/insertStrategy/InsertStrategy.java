package insertStrategy;

import entity.Process;
import entity.ProcessQueue;

/**
 * Contract of adding process' special strategy of special {@link entity.ProcessStream}
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public interface InsertStrategy {

    /**
     * After method execution process should be added to the place, from which it can be
     * consumed by cpu for execution
     *
     * @param process process that should be executed
     * @param queue   queue, to which process can be added
     */
    void addProcess(Process process, ProcessQueue queue);

}