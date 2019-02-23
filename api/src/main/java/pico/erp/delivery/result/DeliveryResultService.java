package pico.erp.delivery.result;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import pico.erp.delivery.DeliveryId;

public interface DeliveryResultService {

  DeliveryResultData create(@Valid DeliveryResultRequests.CreateRequest request);

  boolean exists(@NotNull DeliveryResultId id);

  DeliveryResultData get(@NotNull DeliveryResultId id);

  List<DeliveryResultData> getAll(@Valid @NotNull DeliveryId deliveryId);

}
