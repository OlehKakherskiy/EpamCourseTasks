import entity.CPU;
import entity.ProcessQueue;
import entity.ProcessStream;
import entity.QueueStatistics;
import insertStrategy.InsertStrategy;
import insertStrategy.InsertStrategyFirstStream;
import insertStrategy.InsertStrategySecondStream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        int[] processCount = {150, 100};
        int[][] generatorParams = {{250, 255}, {100, 150}};

        QueueStatistics queueStatistics = new QueueStatistics(processCount.length);

        int[] processStreamID = {1, 2};

        Map<Integer, InsertStrategy> insertStrategyMap = new HashMap<>();
        insertStrategyMap.put(processStreamID[0], new InsertStrategyFirstStream());
        insertStrategyMap.put(processStreamID[1], new InsertStrategySecondStream());

        ProcessQueue queue = new ProcessQueue(insertStrategyMap, queueStatistics);

        List<CPU> cpuList = Arrays.asList(new CPU(queue, processStreamID[0]), new CPU(queue, processStreamID[1]));
        queue.setCpus(cpuList);

        Thread[] processStream = new Thread[processCount.length];

        for (int i = 0; i < processStream.length; i++) {
            processStream[i] = new Thread(new ProcessStream(processCount[i], generatorParams[i][0],
                    generatorParams[i][1], processStreamID[i], queue));
        }

        Stream.of(processStream).forEach(Thread::start);
        cpuList.stream().forEach(CPU::start);
        for (Thread stream : processStream) {
            stream.join();
        }
        System.out.println(queueStatistics.printStatistics(processCount, processStreamID));
    }
}
