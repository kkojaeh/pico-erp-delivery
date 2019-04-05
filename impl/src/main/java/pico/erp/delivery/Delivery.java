package pico.erp.delivery;

import java.io.Serializable;
import java.time.OffsetDateTime;
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
import pico.erp.delivery.subject.DeliverySubjectId;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@ToString
public class Delivery implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  DeliveryId id;

  DeliverySubjectId subjectId;

  OffsetDateTime createdDate;

  String key;

  public DeliveryMessages.Create.Response apply(DeliveryMessages.Create.Request request) {
    id = request.getId();
    subjectId = request.getSubjectId();
    key = request.getKey();
    createdDate = OffsetDateTime.now();
    return new DeliveryMessages.Create.Response(
      Arrays.asList(new DeliveryEvents.CreatedEvent(id))
    );
  }


}
