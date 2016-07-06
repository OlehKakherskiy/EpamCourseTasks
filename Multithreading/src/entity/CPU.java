package entity;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class CPU extends Thread {

    protected ProcessQueue queue;

    /**
     * process stream id, whose generated process is executing now
     */
    private volatile int processStreamID = -1; //TODO: is volatile need?

    private volatile Process nextProcessToExec; //TODO: volatile

    private int cpuID;

    public CPU(ProcessQueue queue, int cpuID) {
        this.queue = queue;
        this.cpuID = cpuID;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            Process process = getProcess();
            if (process == null) {
                continue;
            }
            System.out.println("cpu" + cpuID + "gets process from " + process.getStreamID() + " stream");
            processStreamID = process.getStreamID();
            if (Thread.currentThread().isInterrupted()) {
                continue;
            }
            try {
                System.out.println("cpuID:" + cpuID + ",executionTime=" + process.getTimeLength());
                sleep(process.getTimeLength());
                processStreamID = -1;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    protected Process getProcess() {
        return (nextProcessToExec == null) ? queue.pollLast() : getNextForcedProcess();
    }

    private Process getNextForcedProcess() {
        Process buf = nextProcessToExec;
        nextProcessToExec = null;
        return buf;
    }

    public int getProcessStreamID() {
        return processStreamID;
    }

    public void setNextProcessToExec(Process nextProcessToExec) {
//        synchronized (ProcessQueue.lock) {
//            ProcessQueue.lock.notifyAll();
//            System.out.println("notifyAll");
        this.nextProcessToExec = nextProcessToExec;
//        }

    }

    public int getCpuID() {
        return cpuID;
    }
}