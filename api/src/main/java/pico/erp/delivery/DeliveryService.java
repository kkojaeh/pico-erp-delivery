package pico.erp.delivery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import pico.erp.delivery.result.DeliveryResultData;

public interface DeliveryService {

  DeliveryData create(@Valid DeliveryRequests.CreateRequest request);

  DeliveryResultData deliver(@Valid DeliveryRequests.DeliverRequest request);

  boolean exists(@NotNull DeliveryId id);

  DeliveryData get(@NotNull DeliveryId id);

}
