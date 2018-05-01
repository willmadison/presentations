package com.willmadison.legacycodekatas.examples.sprouting;

import com.willmadison.legacycodekatas.fulfillment.orders.Order;

import java.util.Collection;

public class PreSproutMethod {

    // START SNIPPET OMIT
    public void escalate(Collection<Order> orders) {
        orders.forEach(this::escalateOrder);
    }

    private void escalateOrder(Order order) {

    }
    // END SNIPPET OMIT
}
