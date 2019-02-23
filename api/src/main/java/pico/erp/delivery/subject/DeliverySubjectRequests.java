package pico.erp.delivery.subject;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pico.erp.shared.TypeDefinitions;

public interface DeliverySubjectRequests {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class CreateRequest {

    @Valid
    @NotNull
    DeliverySubjectId id;

    @NotNull
    @Size(max = TypeDefinitions.NAME_LENGTH)
    String name;

  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class UpdateRequest {

    @Valid
    @NotNull
    DeliverySubjectId id;

    @NotNull
    @Size(max = TypeDefinitions.NAME_LENGTH)
    String name;

    @NotNull
    @Size(max = TypeDefinitions.NAME_X3_LENGTH)
    String titleTemplate;

    @NotNull
    @Size(max = TypeDefinitions.CLOB_LENGTH)
    String bodyTemplate;

  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class MakeRequest<K> {

    @Valid
    @NotNull
    DeliverySubjectId id;

    @Valid
    @NotNull
    K key;

  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  class TestRequest {

    @Valid
    @NotNull
    DeliverySubjectId id;

    @Valid
    @NotNull
    String key;

    @Size(max = TypeDefinitions.CLOB_LENGTH)
    String template;

  }

}
