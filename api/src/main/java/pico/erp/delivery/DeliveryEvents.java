package pico.erp.delivery;

import lombok.ToString;
import lombok.Value;
import pico.erp.shared.event.Event;

public interface DeliveryEvents {

  @ToString
  @Value
  class CreatedEvent implements Event {

    public final static String CHANNEL = "event.delivery.created";

    private DeliveryId id;

    public String channel() {
      return CHANNEL;
    }

  }

  @ToString
  @Value
  class ErrorOccurredEvent implements Event {

    public final static String CHANNEL = "event.delivery.error-occurred";

    private DeliveryId id;

    private String stacktrace;

    public String channel() {
      return CHANNEL;
    }

  }

}
