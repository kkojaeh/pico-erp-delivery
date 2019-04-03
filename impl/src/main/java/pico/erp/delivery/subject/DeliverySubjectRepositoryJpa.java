package pico.erp.delivery.subject;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
interface DeliverySubjectEntityRepository extends
  CrudRepository<DeliverySubjectEntity, DeliverySubjectId> {

}

@Repository
@Transactional
public class DeliverySubjectRepositoryJpa implements DeliverySubjectRepository {

  @Autowired
  private DeliverySubjectEntityRepository repository;

  @Autowired
  private DeliverySubjectMapper mapper;

  @Override
  public DeliverySubject create(DeliverySubject deliverySubject) {
    val entity = mapper.jpa(deliverySubject);
    val created = repository.save(entity);
    return mapper.jpa(created);
  }

  @Override
  public void deleteBy(DeliverySubjectId id) {
    repository.deleteById(id);
  }

  @Override
  public boolean exists(DeliverySubjectId id) {
    return repository.existsById(id);
  }

  @Override
  public Stream<DeliverySubject> findAll() {
    return StreamSupport.stream(repository.findAll().spliterator(), false)
      .map(mapper::jpa);
  }

  @Override
  public Optional<DeliverySubject> findBy(DeliverySubjectId id) {
    return repository.findById(id)
      .map(mapper::jpa);
  }

  @Override
  public void update(DeliverySubject deliverySubject) {
    val entity = repository.findById(deliverySubject.getId()).get();
    mapper.pass(mapper.jpa(deliverySubject), entity);
    repository.save(entity);
  }

}
