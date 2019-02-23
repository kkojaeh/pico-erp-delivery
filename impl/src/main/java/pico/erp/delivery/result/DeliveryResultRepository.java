package pico.erp.delivery.result;

import java.util.Optional;
import java.util.stream.Stream;
import javax.validation.constraints.NotNull;
import pico.erp.delivery.DeliveryId;

public interface DeliveryResultRepository {

  DeliveryResult create(@NotNull DeliveryResult deliveryResult);

  void deleteBy(@NotNull DeliveryResultId id);

  boolean exists(@NotNull DeliveryResultId id);

  Stream<DeliveryResult> findAllBy(@NotNull DeliveryId deliveryId);

  Optional<DeliveryResult> findBy(@NotNull DeliveryResultId id);

  void update(@NotNull DeliveryResult deliveryResult);

}
