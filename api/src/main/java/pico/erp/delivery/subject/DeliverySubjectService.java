package pico.erp.delivery.subject;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import pico.erp.delivery.message.DeliveryMessage;

public interface DeliverySubjectService {

  <K> K convert(@Valid @NotNull DeliverySubjectId id, @Valid @NotNull String key);

  <K> String convert(@Valid @NotNull DeliverySubjectId id, @Valid @NotNull K key);

  boolean exists(@Valid @NotNull DeliverySubjectId id);

  DeliverySubjectData get(@Valid @NotNull DeliverySubjectId id);

  DeliveryMessage make(@Valid @NotNull DeliverySubjectRequests.MakeRequest request);

  void update(@Valid @NotNull DeliverySubjectRequests.UpdateRequest request);

}
