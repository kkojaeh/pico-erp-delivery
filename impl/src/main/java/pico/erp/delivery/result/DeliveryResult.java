package pico.erp.delivery.result;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import pico.erp.delivery.DeliveryId;
import pico.erp.delivery.DeliveryMessages;
import pico.erp.delivery.DeliveryMethodKind;
import pico.erp.user.UserId;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@ToString
public class DeliveryResult implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  DeliveryResultId id;

  DeliveryId deliveryId;

  DeliveryMethodKind method;

  String address;

  boolean successful;

  LocalDateTime requestedDate;

  UserId requesterId;

  public DeliveryMessages.Create.Response apply(DeliveryResultMessages.Create.Request request) {
    id = request.getId();
    deliveryId = request.getDeliveryId();
    method = request.getMethod();
    address = request.getAddress();
    successful = request.isSuccessful();
    requesterId = request.getRequesterId();
    requestedDate = LocalDateTime.now();
    return new DeliveryMessages.Create.Response(
      Arrays.asList(new DeliveryResultEvents.CreatedEvent(id))
    );
  }


}
