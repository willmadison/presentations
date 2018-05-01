package com.willmadison.legacycodekatas.examples.wrapping;

import com.willmadison.legacycodekatas.fulfillment.warehouse.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class Wrapped {

    private static final Logger logger = LoggerFactory.getLogger(Wrapped.class);

    // START SNIPPET OMIT
    public void processMessages() {
        long start = System.currentTimeMillis();
        doProcessMessages();
        logger.info("Message processing completed in: {} ms", 
            System.currentTimeMillis() - start);
    }

    private void doProcessMessages() {
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
