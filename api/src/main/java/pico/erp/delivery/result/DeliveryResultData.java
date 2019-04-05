package pico.erp.delivery.result;

import java.time.OffsetDateTime;
import lombok.Data;
import pico.erp.delivery.DeliveryId;
import pico.erp.delivery.DeliveryMethodKind;
import pico.erp.user.UserId;

@Data
public class DeliveryResultData {

  DeliveryResultId id;

  DeliveryId deliveryId;

  DeliveryMethodKind method;

  String address;

  boolean successful;

  OffsetDateTime requestedDate;

  UserId requesterId;

}
