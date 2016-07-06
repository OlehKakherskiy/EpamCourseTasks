package entity;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class ProcessStream implements Runnable {

    private int processCount;

    private double startBounding;

    private double finishBounding;

    private int streamID;

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


    public void startGeneration() {
        for (int i = 0; i < processCount; i++) {
            queue.addProcess(generateProcess());
            try {
                blockProcessStream();
            } catch (InterruptedException e) {
                e.printStackTrace(); //TODO: add exception handling
            }
        }
    }

    private Process generateProcess() {
        return new Process(random(), streamID);
    }

    private int random() {
        return (int) (startBounding + Math.random() * (finishBounding - startBounding));
    }

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