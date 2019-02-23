package pico.erp.delivery;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Value;
import pico.erp.delivery.subject.DeliverySubjectId;
import pico.erp.shared.TypeDefinitions;
import pico.erp.shared.event.Event;

public interface DeliveryMessages {


  interface Create {

    @Data
    class Request {

      @Valid
      @NotNull
      DeliveryId id;

      @Valid
      @NotNull
      DeliverySubjectId subjectId;

      @NotNull
      @Size(max = TypeDefinitions.EXTERNAL_ID_LENGTH)
      String key;

    }

    @Value
    class Response {

      Collection<Event> events;

    }

  }

}
