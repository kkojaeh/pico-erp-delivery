package pico.erp.delivery.subject;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper
public abstract class DeliverySubjectMapper {

  public DeliverySubject jpa(DeliverySubjectEntity entity) {
    return DeliverySubject.builder()
      .id(entity.getId())
      .name(entity.getName())
      .titleTemplate(entity.getTitleTemplate())
      .bodyTemplate(entity.getBodyTemplate())
      .build();
  }

  @Mappings({
    @Mapping(target = "createdBy", ignore = true),
    @Mapping(target = "createdDate", ignore = true),
    @Mapping(target = "lastModifiedBy", ignore = true),
    @Mapping(target = "lastModifiedDate", ignore = true)
  })
  public abstract DeliverySubjectEntity jpa(DeliverySubject deliverySubject);

  public abstract DeliverySubjectData map(DeliverySubject deliverySubject);

  public abstract DeliverySubjectMessages.Update.Request map(
    DeliverySubjectRequests.UpdateRequest request);

  public abstract void pass(DeliverySubjectEntity from, @MappingTarget DeliverySubjectEntity to);

}
