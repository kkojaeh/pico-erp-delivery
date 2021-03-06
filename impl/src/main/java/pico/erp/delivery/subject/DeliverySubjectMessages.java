package pico.erp.delivery.subject;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import pico.erp.shared.TypeDefinitions;
import pico.erp.shared.event.Event;

public interface DeliverySubjectMessages {

  interface Create {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    class Request {

      @Valid
      @NotNull
      DeliverySubjectId id;

      @NotNull
      @Size(max = TypeDefinitions.NAME_LENGTH)
      String name;

    }

    @Value
    class Response {

      Collection<Event> events;

    }
  }

  interface Update {

    @Data
    class Request {

      @NotNull
      @Size(max = TypeDefinitions.NAME_LENGTH)
      String name;

      @NotNull
      @Size(max = TypeDefinitions.NAME_X3_LENGTH)
      String titleTemplate;

      @NotNull
      @Size(max = TypeDefinitions.CLOB_LENGTH)
      String bodyTemplate;

    }

    @Value
    class Response {

      Collection<Event> events;

    }
  }


}
