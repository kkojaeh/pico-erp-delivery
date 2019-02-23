package pico.erp.delivery;

import java.util.Optional;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
interface DeliveryEntityRepository extends CrudRepository<DeliveryEntity, DeliveryId> {

}

@Repository
@Transactional
public class DeliveryRepositoryJpa implements DeliveryRepository {

  @Autowired
  private DeliveryEntityRepository repository;

  @Autowired
  private DeliveryMapper mapper;

  @Override
  public Delivery create(Delivery delivery) {
    val entity = mapper.entity(delivery);
    val created = repository.save(entity);
    return mapper.domain(created);
  }

  @Override
  public void deleteBy(DeliveryId id) {
    repository.delete(id);
  }

  @Override
  public boolean exists(DeliveryId id) {
    return repository.exists(id);
  }

  @Override
  public Optional<Delivery> findBy(DeliveryId id) {
    return Optional.ofNullable(repository.findOne(id))
      .map(mapper::domain);
  }

  @Override
  public void update(Delivery delivery) {
    val entity = repository.findOne(delivery.getId());
    mapper.pass(mapper.entity(delivery), entity);
    repository.save(entity);
  }
}
