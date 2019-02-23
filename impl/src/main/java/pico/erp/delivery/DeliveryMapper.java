package pico.erp.delivery;

import lombok.val;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import pico.erp.delivery.subject.DeliverySubjectService;

@Mapper
public abstract class DeliveryMapper {

  @Lazy
  @Autowired
  protected DeliverySubjectService deliverySubjectService;

  @AfterMapping
  protected void afterMapping(DeliveryRequests.CreateRequest request,
    @MappingTarget DeliveryMessages.Create.Request message) {
    val key = deliverySubjectService.convert(request.getSubjectId(), request.getKey());
    message.setKey(key);
  }

  public Delivery domain(DeliveryEntity entity) {
    return Delivery.builder()
      .id(entity.getId())
      .subjectId(entity.getSubjectId())
      .key(entity.getKey())
      .createdDate(entity.getCreatedDate())
      .build();
  }

  public abstract DeliveryEntity entity(Delivery delivery);

  @Mappings({
    @Mapping(target = "key", ignore = true)
  })
  public abstract DeliveryMessages.Create.Request map(DeliveryRequests.CreateRequest request);

  public abstract DeliveryData map(Delivery delivery);

  public abstract void pass(DeliveryEntity from, @MappingTarget DeliveryEntity to);


}
