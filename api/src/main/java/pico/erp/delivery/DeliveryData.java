package pico.erp.delivery;

import java.time.OffsetDateTime;
import lombok.Data;
import pico.erp.delivery.subject.DeliverySubjectId;

@Data
public class DeliveryData {

  DeliveryId id;

  DeliverySubjectId subjectId;

  OffsetDateTime createdDate;

  String key;

}
