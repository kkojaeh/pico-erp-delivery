package pico.erp.delivery;

import java.io.PrintWriter;
import java.io.StringWriter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pico.erp.delivery.result.DeliveryResultData;
import pico.erp.delivery.result.DeliveryResultId;
import pico.erp.delivery.result.DeliveryResultRequests;
import pico.erp.delivery.result.DeliveryResultService;
import pico.erp.delivery.send.DeliverySendRequests.SendRequest;
import pico.erp.delivery.send.FaxDeliverySendService;
import pico.erp.delivery.send.MailDeliverySendService;
import pico.erp.delivery.subject.DeliverySubjectRequests;
import pico.erp.delivery.subject.DeliverySubjectService;
import pico.erp.shared.Public;
import pico.erp.shared.event.EventPublisher;

@Service
@Public
@Transactional
@Validated
public class DeliveryServiceLogic implements DeliveryService {

  @Autowired
  private DeliveryRepository deliveryRepository;

  @Lazy
  @Autowired
  private DeliveryResultService deliveryResultService;

  @Lazy
  @Autowired
  private DeliverySubjectService deliverySubjectService;

  @Autowired
  private EventPublisher eventPublisher;

  @Autowired
  private DeliveryMapper mapper;

  @Lazy
  @Autowired
  private MailDeliverySendService mailDeliverySendService;

  @Lazy
  @Autowired
  private FaxDeliverySendService faxDeliverySendService;

  @Override
  public DeliveryData create(DeliveryRequests.CreateRequest request) {
    val delivery = new Delivery();
    val response = delivery.apply(mapper.map(request));
    if (deliveryRepository.exists(delivery.getId())) {
      throw new DeliveryExceptions.AlreadyExistsException();
    }
    val created = deliveryRepository.create(delivery);
    eventPublisher.publishEvents(response.getEvents());
    return mapper.map(created);
  }

  @Override
  public DeliveryResultData deliver(DeliveryRequests.DeliverRequest request) {
    val delivery = deliveryRepository.findBy(request.getId())
      .orElseThrow(DeliveryExceptions.NotFoundException::new);
    val subjectId = delivery.getSubjectId();
    val key = deliverySubjectService.convert(subjectId, delivery.getKey());
    val address = request.getAddress();

    val message = deliverySubjectService.make(
      DeliverySubjectRequests.MakeRequest.builder()
        .id(subjectId)
        .key(key)
        .build()
    );
    val method = request.getMethod();
    val sendRequest = SendRequest.builder()
      .address(request.getAddress())
      .requesterId(request.getRequesterId())
      .build();
    boolean successful = true;
    try {
      if (method == DeliveryMethodKind.MAIL) {
        mailDeliverySendService.send(sendRequest, message);
      } else if (method == DeliveryMethodKind.FAX) {
        faxDeliverySendService.send(sendRequest, message);
      }
    } catch (Throwable t) {
      val writer = new StringWriter();
      t.printStackTrace(new PrintWriter(writer));
      eventPublisher.publishEvent(
        new DeliveryEvents.ErrorOccurredEvent(delivery.getId(), writer.toString())
      );
      successful = false;
    }
    val result = deliveryResultService.create(
      DeliveryResultRequests.CreateRequest.builder()
        .id(DeliveryResultId.generate())
        .deliveryId(delivery.getId())
        .method(method)
        .address(address)
        .successful(successful)
        .requesterId(request.getRequesterId())
        .build()
    );
    return result;
  }

  @Override
  public boolean exists(DeliveryId id) {
    return deliveryRepository.exists(id);
  }


  @Override
  public DeliveryData get(DeliveryId id) {
    return deliveryRepository.findBy(id)
      .map(mapper::map)
      .orElseThrow(DeliveryExceptions.NotFoundException::new);
  }


}
