package pico.erp.delivery.result;

import java.util.List;
import java.util.stream.Collectors;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pico.erp.delivery.DeliveryId;
import pico.erp.shared.Public;
import pico.erp.shared.event.EventPublisher;

@SuppressWarnings("Duplicates")
@Service
@Public
@Transactional
@Validated
public class DeliveryResultServiceLogic implements DeliveryResultService {

  @Autowired
  private DeliveryResultRepository deliveryResultRepository;

  @Autowired
  private EventPublisher eventPublisher;

  @Autowired
  private DeliveryResultMapper mapper;

  @Override
  public DeliveryResultData create(DeliveryResultRequests.CreateRequest request) {
    val item = new DeliveryResult();
    val response = item.apply(mapper.map(request));
    if (deliveryResultRepository.exists(item.getId())) {
      throw new DeliveryResultExceptions.AlreadyExistsException();
    }
    val created = deliveryResultRepository.create(item);
    eventPublisher.publishEvents(response.getEvents());
    return mapper.map(created);
  }

  @Override
  public boolean exists(DeliveryResultId id) {
    return deliveryResultRepository.exists(id);
  }

  @Override
  public DeliveryResultData get(DeliveryResultId id) {
    return deliveryResultRepository.findBy(id)
      .map(mapper::map)
      .orElseThrow(DeliveryResultExceptions.NotFoundException::new);
  }

  @Override
  public List<DeliveryResultData> getAll(DeliveryId deliveryId) {
    return deliveryResultRepository.findAllBy(deliveryId)
      .map(mapper::map)
      .collect(Collectors.toList());
  }

}
