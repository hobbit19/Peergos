package defiance.dht;

public class Node
{
    public static enum State {Good, Waiting, Lost}
    public long lastSeen;
    public State state;
    public NodeID node;
    public static final long NEIGHBOUR_TIMEOUT = 10*60*1000L; // 10 minutes

    public Node(NodeID node)
    {
        this(node, true);
    }

    public Node(NodeID node, boolean seen)
    {
        if (seen)
            lastSeen = System.currentTimeMillis();
        state = State.Good;
        this.node = node;
    }

    public boolean isLost()
    {
        return state == State.Lost;
    }

    public boolean isGood()
    {
        return state == State.Good;
    }

    public boolean isWaiting()
    {
        return state == State.Waiting;
    }

    public void receivedContact()
    {
        lastSeen = System.currentTimeMillis();
        state = State.Good;
    }

    public boolean isRecent()
    {
        return System.currentTimeMillis() < lastSeen + NEIGHBOUR_TIMEOUT;
    }

    public void sendECHO()
    {
        state = State.Waiting;
    }

    public void setTimedOut()
    {
        state = State.Lost;
    }
}