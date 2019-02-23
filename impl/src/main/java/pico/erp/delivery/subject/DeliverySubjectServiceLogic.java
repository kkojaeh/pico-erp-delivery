package pico.erp.delivery.subject;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pico.erp.delivery.DeliveryInitializer.DeliveryInitializable;
import pico.erp.delivery.message.DeliveryMessage;
import pico.erp.delivery.subject.DeliverySubjectRequests.MakeRequest;
import pico.erp.shared.Public;
import pico.erp.shared.event.EventPublisher;

@SuppressWarnings("Duplicates")
@Service
@Public
@Transactional
@Validated
public class DeliverySubjectServiceLogic implements DeliverySubjectService, DeliveryInitializable {

  private final Map<DeliverySubjectId, DeliverySubjectDefinition> mapping = new HashMap<>();

  @Autowired
  private DeliverySubjectRepository deliverySubjectRepository;

  @Autowired
  private EventPublisher eventPublisher;

  @Autowired
  private DeliverySubjectMapper mapper;

  @Lazy
  @Autowired
  private List<DeliverySubjectDefinition> definitions;

  @Autowired
  private MustacheFactory mustacheFactory;

  @Override
  public <K> K convert(DeliverySubjectId id, String key) {
    if (!mapping.containsKey(id)) {
      throw new DeliverySubjectExceptions.NotFoundException();
    }
    val definition = mapping.get(id);
    return (K) definition.toKey(key);
  }

  @Override
  public <K> String convert(DeliverySubjectId id, K key) {
    if (!mapping.containsKey(id)) {
      throw new DeliverySubjectExceptions.NotFoundException();
    }
    val definition = mapping.get(id);
    return definition.toString(key);
  }

  @Override
  public boolean exists(DeliverySubjectId id) {
    return deliverySubjectRepository.exists(id);
  }

  @Override
  public DeliverySubjectData get(DeliverySubjectId id) {
    return deliverySubjectRepository.findBy(id)
      .map(mapper::map)
      .orElseThrow(DeliverySubjectExceptions.NotFoundException::new);
  }

  @Override
  public void initialize() {
    val targets = definitions.stream().collect(Collectors.toMap(d -> d.getId(), d -> d));
    mapping.putAll(
      definitions.stream()
        .collect(Collectors.toMap(d -> d.getId(), d -> d))
    );
    deliverySubjectRepository.findAll()
      .forEach(deliveryType -> targets.remove(deliveryType.getId()));
    targets.values().forEach(definition -> {
      val deliveryType = new DeliverySubject();
      val request = DeliverySubjectMessages.Create.Request.builder()
        .id(definition.getId())
        .name(definition.getName())
        .build();
      val response = deliveryType.apply(request);
      deliverySubjectRepository.create(deliveryType);
      eventPublisher.publishEvents(response.getEvents());
    });

  }

  @Override
  public DeliveryMessage make(MakeRequest request) {
    val deliveryType = deliverySubjectRepository.findBy(request.getId())
      .orElseThrow(DeliverySubjectExceptions.NotFoundException::new);
    val definition = mapping.get(request.getId());
    val titleTemplate = deliveryType.getTitleTemplate();
    val bodyTemplate = deliveryType.getBodyTemplate();
    val context = definition.getContext(request.getKey());
    val attachments = definition.getAttachments(request.getKey());
    val titleMustache = mustacheFactory
      .compile(new StringReader(titleTemplate), deliveryType.getId().getValue());
    val bodyMustache = mustacheFactory
      .compile(new StringReader(bodyTemplate), deliveryType.getId().getValue());
    return DeliveryMessage.Impl.builder()
      .title(write(titleMustache, context))
      .body(write(bodyMustache, context))
      .attachments(attachments)
      .build();
  }

  @Override
  public void update(DeliverySubjectRequests.UpdateRequest request) {
    val deliveryType = deliverySubjectRepository.findBy(request.getId())
      .orElseThrow(DeliverySubjectExceptions.NotFoundException::new);
    val response = deliveryType.apply(mapper.map(request));
    deliverySubjectRepository.update(deliveryType);
    eventPublisher.publishEvents(response.getEvents());
  }

  @SneakyThrows
  private String write(Mustache mustache, Object context) {
    StringWriter writer = new StringWriter();
    mustache.execute(writer, context).flush();
    return writer.toString();
  }


}
