package pico.erp.delivery;


import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import pico.erp.delivery.subject.DeliverySubjectId;
import pico.erp.shared.TypeDefinitions;

@Entity(name = "Delivery")
@Table(name = "DLV_DELIVERY")
@Data
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @EmbeddedId
  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "ID", length = TypeDefinitions.UUID_BINARY_LENGTH))
  })
  DeliveryId id;

  @AttributeOverrides({
    @AttributeOverride(name = "value", column = @Column(name = "SUBJECT_ID", length = TypeDefinitions.ID_LENGTH))
  })
  DeliverySubjectId subjectId;

  @Column(length = TypeDefinitions.EXTERNAL_ID_LENGTH)
  String key;

  @Column(updatable = false)
  OffsetDateTime createdDate;

}
