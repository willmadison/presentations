package com.willmadison.legacycodekatas.examples.skeletonization;

import com.willmadison.legacycodekatas.fulfillment.warehouse.Message;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Skeletonized {
    private static final int MAX_BACKGROUND_WORKERS = 10;

    // START SNIPPET OMIT
    public Collection<Collection<Message>> batchMessages(Collection<Message> messages) {
        if (hasEnoughMessagesForBatching(messages)) {
            return partition(messages);
        }
        return Collections.singleton(messages);
    }

    private boolean hasEnoughMessagesForBatching(Collection<Message> messages) {
        return !CollectionUtils.isEmpty(messages);
    }

    private Collection<Collection<Message>> partition(Collection<Message> messages) {
       // partitioning logic here as before....
    }
    // END SNIPPET OMIT
}
