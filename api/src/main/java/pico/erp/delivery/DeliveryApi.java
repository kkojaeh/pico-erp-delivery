package pico.erp.delivery;

import javax.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pico.erp.shared.ApplicationId;
import pico.erp.shared.data.Role;

public final class DeliveryApi {

  public static ApplicationId ID = ApplicationId.from("delivery");

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
