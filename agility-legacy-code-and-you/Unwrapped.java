package com.willmadison.legacycodekatas.examples.wrapping;

import com.willmadison.legacycodekatas.fulfillment.warehouse.Message;
import sun.misc.resources.Messages;

import java.util.Collection;
import java.util.Collections;

public class Unwrapped {

    // START SNIPPET OMIT
    public void processMessages() {
        Collection<Message> messages = fetchMessages();
        messages.forEach(m -> process(m));
    }

    private void process(Message message) {
        // Do something interesting with the message....
    }

    private Collection<Message> fetchMessages() {
        return Collections.emptySet();
    }
    // END SNIPPET OMIT
}
