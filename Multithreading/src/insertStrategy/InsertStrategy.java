package insertStrategy;

import entity.Process;
import entity.ProcessQueue;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public interface InsertStrategy {

    void addProcess(Process process, ProcessQueue queue);

}