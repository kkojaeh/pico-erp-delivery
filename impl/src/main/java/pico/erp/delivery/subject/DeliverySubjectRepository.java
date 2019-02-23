package pico.erp.delivery.subject;

import java.util.Optional;
import java.util.stream.Stream;
import javax.validation.constraints.NotNull;

public interface DeliverySubjectRepository {

  DeliverySubject create(DeliverySubject deliverySubject);

  void deleteBy(DeliverySubjectId id);

  boolean exists(DeliverySubjectId id);

  Stream<DeliverySubject> findAll();

  Optional<DeliverySubject> findBy(@NotNull DeliverySubjectId id);

  void update(@NotNull DeliverySubject deliverySubject);

}
