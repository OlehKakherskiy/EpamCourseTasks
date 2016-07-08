package entity;

/**
 * Class represents producer in this model.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ProcessStream implements Runnable {

    /**
     * process count, that will be produced by this producer
     */
    private int processCount;

    /**
     * start time boundary
     */
    private double startBounding;

    /**
     * end time boundary
     */
    private double finishBounding;

    /**
     * stream id
     */
    private int streamID;

    /**
     * queue, to which produced processes will be added
     */
    private ProcessQueue queue;

    public ProcessStream(int processCount, double startBounding, double finishBounding, int streamID, ProcessQueue queue) {
        check(processCount, startBounding, finishBounding, streamID);
        this.processCount = processCount;
        this.startBounding = startBounding;
        this.finishBounding = finishBounding;
        this.streamID = streamID;
        this.queue = queue;
    }

    @Override
    public void run() {
        startGeneration();
    }


    /**
     * generates all processes. After each generation stream is blocked by {@link #blockProcessStream()}
     */
    public void startGeneration() {
        for (int i = 0; i < processCount; i++) {
            queue.addProcess(generateProcess());
            try {
                blockProcessStream();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * generate new process
     *
     * @return process
     */
    private Process generateProcess() {
        return new Process(random(), streamID);
    }

    /**
     * returns random int in range [startBounding, endBounding]
     *
     * @return random int in range [startBounding, endBounding]
     */
    private int random() {
        return (int) (startBounding + Math.random() * (finishBounding - startBounding));
    }

    /**
     * blocks stream for time equal to {@link #random()}/2.
     *
     * @throws InterruptedException
     */
    private void blockProcessStream() throws InterruptedException {
        Thread.sleep(random() / 2);
    }

    private void check(double... number) {
        for (double i : number) {
            if (i < 0) {
                throw new IllegalArgumentException("can't be less then 0");
            }
        }
    }

    public int getProcessCount() {
        return processCount;
    }
}