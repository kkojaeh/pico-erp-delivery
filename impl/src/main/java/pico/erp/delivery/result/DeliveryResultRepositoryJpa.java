package pico.erp.delivery.result;

import java.util.Optional;
import java.util.stream.Stream;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pico.erp.delivery.DeliveryId;

@Repository
interface DeliveryResultEntityRepository extends
  CrudRepository<DeliveryResultEntity, DeliveryResultId> {

  @Query("SELECT r FROM DeliveryResult r WHERE r.deliveryId = :deliveryId ORDER BY r.requestedDate DESC")
  Stream<DeliveryResultEntity> findAllBy(@Param("deliveryId") DeliveryId deliveryId);

}

@Repository
@Transactional
public class DeliveryResultRepositoryJpa implements DeliveryResultRepository {

  @Autowired
  private DeliveryResultEntityRepository repository;

  @Autowired
  private DeliveryResultMapper mapper;

  @Override
  public DeliveryResult create(DeliveryResult deliveryResult) {
    val entity = mapper.jpa(deliveryResult);
    val created = repository.save(entity);
    return mapper.jpa(created);
  }

  @Override
  public void deleteBy(DeliveryResultId id) {
    repository.deleteById(id);
  }

  @Override
  public boolean exists(DeliveryResultId id) {
    return repository.existsById(id);
  }

  @Override
  public Stream<DeliveryResult> findAllBy(DeliveryId deliveryId) {
    return repository.findAllBy(deliveryId)
      .map(mapper::jpa);
  }

  @Override
  public Optional<DeliveryResult> findBy(DeliveryResultId id) {
    return repository.findById(id)
      .map(mapper::jpa);
  }

  @Override
  public void update(DeliveryResult deliveryResult) {
    val entity = repository.findById(deliveryResult.getId()).get();
    mapper.pass(mapper.jpa(deliveryResult), entity);
    repository.save(entity);
  }

}
