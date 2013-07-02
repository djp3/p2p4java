package edu.uci.ics.luci.p2p4java.impl.endpoint;

import edu.uci.ics.luci.p2p4java.endpoint.Message;

/**
 * Simple data structure to associate a message with it's write listener,
 * used by asynchronous messengers.
 * @author Iain McGinniss (iain.mcginniss@onedrum.com)
 */
public class QueuedMessage {

    private Message message;
    private MessageWriteListener writeListener;

    public QueuedMessage(Message msg, MessageWriteListener writeListener) {
        this.message = msg;
        this.writeListener = writeListener;
    }

    public Message getMessage() {
        return message;
    }

    public MessageWriteListener getWriteListener() {
        return writeListener;
    }

}
