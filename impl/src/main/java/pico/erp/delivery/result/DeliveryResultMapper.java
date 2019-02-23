package pico.erp.delivery.result;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper
public abstract class DeliveryResultMapper {

  public DeliveryResult jpa(DeliveryResultEntity entity) {
    return DeliveryResult.builder()
      .id(entity.getId())
      .deliveryId(entity.getDeliveryId())
      .method(entity.getMethod())
      .address(entity.getAddress())
      .successful(entity.isSuccessful())
      .requesterId(entity.getRequesterId())
      .requestedDate(entity.getRequestedDate())
      .build();
  }

  @Mappings({
  })
  public abstract DeliveryResultEntity jpa(DeliveryResult deliveryResult);

  public abstract DeliveryResultData map(DeliveryResult deliveryResult);

  public abstract DeliveryResultMessages.Create.Request map(
    DeliveryResultRequests.CreateRequest request);

  public abstract void pass(DeliveryResultEntity from, @MappingTarget DeliveryResultEntity to);

}
