package pico.erp.delivery.subject;

import java.io.Serializable;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliverySubject implements Serializable {

  private static final long serialVersionUID = 1L;

  DeliverySubjectId id;

  String name;

  String titleTemplate;

  String bodyTemplate;

  public DeliverySubjectMessages.Create.Response apply(
    DeliverySubjectMessages.Create.Request request) {
    this.id = request.getId();
    this.name = request.getName();
    return new DeliverySubjectMessages.Create.Response(
      Arrays.asList(new DeliverySubjectEvents.CreatedEvent(id))
    );
  }

  public DeliverySubjectMessages.Update.Response apply(
    DeliverySubjectMessages.Update.Request request) {
    this.name = request.getName();
    this.titleTemplate = request.getTitleTemplate();
    this.bodyTemplate = request.getBodyTemplate();
    return new DeliverySubjectMessages.Update.Response(
      Arrays.asList(new DeliverySubjectEvents.UpdatedEvent(id))
    );
  }

}
