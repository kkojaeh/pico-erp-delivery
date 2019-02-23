package pico.erp.delivery.result;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Value;
import pico.erp.delivery.DeliveryId;
import pico.erp.delivery.DeliveryMethodKind;
import pico.erp.shared.TypeDefinitions;
import pico.erp.shared.event.Event;
import pico.erp.user.UserId;

public interface DeliveryResultMessages {


  interface Create {

    @Data
    class Request {

      @Valid
      @NotNull
      DeliveryResultId id;

      @Valid
      @NotNull
      DeliveryId deliveryId;

      @NotNull
      DeliveryMethodKind method;

      @NotNull
      @Size(max = TypeDefinitions.ID_LENGTH)
      String address;

      boolean successful;

      @Valid
      @NotNull
      UserId requesterId;

    }

    @Value
    class Response {

      Collection<Event> events;

    }

  }

}
