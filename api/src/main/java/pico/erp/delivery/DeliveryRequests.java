package pico.erp.delivery;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pico.erp.delivery.subject.DeliverySubjectId;
import pico.erp.shared.TypeDefinitions;
import pico.erp.user.UserId;

public interface DeliveryRequests {


  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  class CreateRequest<K> {

    @Valid
    @NotNull
    DeliveryId id;

    @Valid
    @NotNull
    DeliverySubjectId subjectId;

    @Valid
    @NotNull
    K key;

  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  class DeliverRequest {

    @Valid
    @NotNull
    DeliveryId id;

    @Valid
    @NotNull
    DeliveryMethodKind method;

    @NotNull
    @Column(length = TypeDefinitions.ID_LENGTH)
    String address;

    @Valid
    @NotNull
    UserId requesterId;

  }

}
