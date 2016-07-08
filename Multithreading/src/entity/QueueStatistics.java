package entity;

/**
 * Class encapsulates execution statistics for each cpu and queue
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class QueueStatistics {

    /**
     * maximum queue length
     */
    private int maxQueueLength = 0;

    /**
     * process count, that are not executed (by each cpu)
     */
    private volatile int[] streamProcessLostCount;


    public QueueStatistics(int streamsCount) {
        streamProcessLostCount = new int[streamsCount];
    }

    public String printStatistics(int[] processCount, int[] streamId) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < streamProcessLostCount.length; i++) {
            builder.append("Statistics for process stream").append(streamId[i]).append(":\n");
            System.out.println(processCount[i]);
            System.out.println(streamProcessLostCount[i]);
            double lossPercentage = (streamProcessLostCount[i] == 0) ? 0 : ((double) streamProcessLostCount[i] / processCount[i]) * 100;
            builder.append("loss percentage = ").append(lossPercentage).append(";\n");
        }
        return builder.append("max queue len=" + maxQueueLength).toString();
    }

    public synchronized void incrementLossCount(int processStreamID) {
        streamProcessLostCount[processStreamID]++;
    }


    public int getMaxQueueLength() {
        return maxQueueLength;
    }

    public void setMaxQueueLength(int maxQueueLength) {
        this.maxQueueLength = maxQueueLength;
    }
}