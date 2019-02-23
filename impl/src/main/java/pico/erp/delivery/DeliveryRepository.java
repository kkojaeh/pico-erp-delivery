package pico.erp.delivery;

import java.util.Optional;
import javax.validation.constraints.NotNull;

public interface DeliveryRepository {

  Delivery create(@NotNull Delivery delivery);

  void deleteBy(@NotNull DeliveryId id);

  boolean exists(@NotNull DeliveryId id);

  Optional<Delivery> findBy(@NotNull DeliveryId id);

  void update(@NotNull Delivery delivery);


}
