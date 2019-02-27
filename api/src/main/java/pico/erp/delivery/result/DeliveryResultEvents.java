package pico.erp.delivery.result;

import lombok.ToString;
import lombok.Value;
import pico.erp.shared.event.Event;

public interface DeliveryResultEvents {

  @ToString
  @Value
  class CreatedEvent implements Event {

    public final static String CHANNEL = "event.delivery-result.created";

    private DeliveryResultId id;

    public String channel() {
      return CHANNEL;
    }

  }

  @ToString
  @Value
  class ErrorOccurredEvent implements Event {

    public final static String CHANNEL = "event.delivery-result.error-occurred";

    private DeliveryResultId id;

    private String stacktrace;

    public String channel() {
      return CHANNEL;
    }

  }

}
