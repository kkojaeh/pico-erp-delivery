package pico.erp.delivery.subject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface DeliverySubjectExceptions {

  @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "delivery-subject.not.found.exception")
  class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

  }
}
