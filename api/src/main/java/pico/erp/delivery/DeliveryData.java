package pico.erp.delivery;

import java.time.LocalDateTime;
import lombok.Data;
import pico.erp.delivery.subject.DeliverySubjectId;

@Data
public class DeliveryData {

  DeliveryId id;

  DeliverySubjectId subjectId;

  LocalDateTime createdDate;

  String key;

}
