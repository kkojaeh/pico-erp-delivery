package pico.erp.delivery;

import java.time.OffsetDateTime;
import lombok.Data;
import pico.erp.delivery.subject.DeliverySubjectId;
import pico.erp.user.UserId;

@Data
public class DeliveryData {

  DeliveryId id;

  DeliverySubjectId subjectId;

  UserId creatorId;

  OffsetDateTime createdDate;

}
