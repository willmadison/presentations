package com.willmadison.legacycodekatas.examples.sprouting;

import com.willmadison.legacycodekatas.fulfillment.orders.Order;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class PostSproutMethod {

    //START SNIPPET OMIT
    public void escalate(Collection<Order> orders) {
        Collection<Order> stales = staleOrders(orders);
        stales.forEach(this::escalateOrder);
    }

    private void escalateOrder(Order order) {
        // escalation logic here...
    }

    public Collection<Order> staleOrders(Collection<Order> orders) {
        return orders.stream().filter(o -> o.lastUpdate.isBefore(LocalDateTime.now().minusHours(4)))
                .collect(Collectors.toList());
    }
    //END SNIPPET OMIT
}
