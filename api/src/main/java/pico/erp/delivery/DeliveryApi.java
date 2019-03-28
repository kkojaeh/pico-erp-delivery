package pico.erp.delivery;

import javax.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pico.erp.shared.data.Role;

public final class DeliveryApi {

  @RequiredArgsConstructor
  public enum Roles implements Role {

    DELIVERY_ACCESSOR,

    DELIVERY_CHARGER,

    DELIVERY_MANAGER;

    @Id
    @Getter
    private final String id = name();

  }

}
