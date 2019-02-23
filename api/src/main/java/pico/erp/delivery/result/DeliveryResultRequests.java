package pico.erp.delivery.result;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pico.erp.delivery.DeliveryId;
import pico.erp.delivery.DeliveryMethodKind;
import pico.erp.shared.TypeDefinitions;
import pico.erp.user.UserId;

public interface DeliveryResultRequests {


  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class CreateRequest {

    @Valid
    @NotNull
    DeliveryResultId id;

    @Valid
    @NotNull
    DeliveryId deliveryId;

    @NotNull
    DeliveryMethodKind method;

    @NotNull
    @Size(max = TypeDefinitions.ID_LENGTH)
    String address;

    boolean successful;

    @Valid
    @NotNull
    UserId requesterId;

  }

}
