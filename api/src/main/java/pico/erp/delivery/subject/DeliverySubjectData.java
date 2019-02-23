package pico.erp.delivery.subject;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliverySubjectData {

  DeliverySubjectId id;

  String name;

  String titleTemplate;

  String bodyTemplate;

}
