package entity;

/**
 * @author Oleh Kakherskyi (olehkakherskiy@gmail.com)
 */
public class Process {

    private int timeLength;

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
