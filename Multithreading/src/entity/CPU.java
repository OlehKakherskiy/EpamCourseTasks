package entity;

/**
 * Class represents consumer object in this system.
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class CPU extends Thread {

    /**
     * queue, from which cpu takes processes
     */
    protected ProcessQueue queue;

    /**
     * process stream id, whose generated process is executing now
     */
    private volatile int processStreamID = -1; //TODO: is volatile need?

    /**
     * process, that was added directly from process stream
     */
    private volatile Process nextProcessToExec; //TODO: volatile

    /**
     * cpu ID
     */
    private int cpuID;

    public CPU(ProcessQueue queue, int cpuID) {
        this.queue = queue;
        this.cpuID = cpuID;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            Process process = null;
            while (process == null) {
                process = getProcess();
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

    /**
     * returns process, added directly (if there is one) or from queue
     *
     * @return process from queue or directly added
     */
    private Process getProcess() {
        return (nextProcessToExec == null) ? queue.pollLast() : getNextForcedProcess();
    }

    /**
     * returns directly added process
     *
     * @return directly added process
     */
    private Process getNextForcedProcess() {
        Process buf = nextProcessToExec;
        nextProcessToExec = null;
        return buf;
    }

    public int getProcessStreamID() {
        return processStreamID;
    }

    /**
     * added process directly to this process for execution
     *
     * @param nextProcessToExec process that will be added directly to cpu
     */
    public void setNextProcessToExec(Process nextProcessToExec) {
        synchronized (ProcessQueue.lock) {
            ProcessQueue.lock.notifyAll();
            System.out.println("notifyAll");
            this.nextProcessToExec = nextProcessToExec;
        }
    }

    public int getCpuID() {
        return cpuID;
    }
}