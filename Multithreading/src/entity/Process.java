package entity;

/**
 * Class represents object that produces by {@link ProcessStream} and consumes by {@link CPU}
 *
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Process {

    /**
     * time need for this process execution
     */
    private int timeLength;

    /**
     * {@link ProcessStream} id
     */
    private int streamID;

    public Process(int timeLength, int streamID) {
        this.timeLength = timeLength;
        this.streamID = streamID;
    }

    public int getTimeLength() {
        return timeLength;
    }

    public int getStreamID() {
        return streamID;
    }
}
