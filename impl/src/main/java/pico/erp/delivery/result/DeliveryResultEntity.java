package pico.erp.delivery.result;


import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pico.erp.delivery.DeliveryId;
import pico.erp.delivery.DeliveryMethodKind;
import pico.erp.shared.TypeDefinitions;
import pico.erp.user.UserId;

@Entity(name = "DeliveryResult")
@Table(name = "DLV_DELIVERY_RESULT", indexes = {
})
@Data
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryResultEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "ID", length = TypeDefinitions.UUID_BINARY_LENGTH))
  })
  DeliveryResultId id;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "DELIVERY_ID", length = TypeDefinitions.UUID_BINARY_LENGTH))
  })
  DeliveryId deliveryId;

  @Column(length = TypeDefinitions.ENUM_LENGTH)
  @Enumerated(EnumType.STRING)
  DeliveryMethodKind method;

  @Column(length = TypeDefinitions.ID_LENGTH)
  String address;

  boolean successful;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "REQUESTER_ID", length = TypeDefinitions.ID_LENGTH))
  })
  UserId requesterId;

  OffsetDateTime requestedDate;

}
