package pico.erp.delivery.send;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pico.erp.user.UserId;

public interface DeliverySendRequests {


  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  class SendRequest {

    @Valid
    @NotNull
    UserId requesterId;

    @Valid
    @NotNull
    String address;

  }

}
