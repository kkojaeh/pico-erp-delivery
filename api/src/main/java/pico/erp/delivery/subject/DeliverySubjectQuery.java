package pico.erp.delivery.subject;

import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliverySubjectQuery {

  Page<DeliverySubjectView> retrieve(@NotNull DeliverySubjectView.Filter filter,
    @NotNull Pageable pageable);

}
