package pico.erp.delivery;

import lombok.SneakyThrows;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import pico.erp.delivery.message.DeliveryMessage;
import pico.erp.delivery.send.DeliverySendRequests;
import pico.erp.delivery.send.FaxDeliverySendService;
import pico.erp.fax.FaxId;
import pico.erp.fax.FaxRequests;
import pico.erp.fax.FaxService;

public class TwilioAwsS3FaxDeliverySendService implements FaxDeliverySendService {

  @Lazy
  @Autowired
  private FaxService faxService;

  @SneakyThrows
  @Override
  public void send(DeliverySendRequests.SendRequest request, DeliveryMessage message) {
    for (val attachment : message.getAttachments()) {
      faxService.send(
        FaxRequests.SendRequest.builder()
          .id(FaxId.generate())
          .requesterId(request.getRequesterId())
          .description(attachment.getName())
          .faxNumber(request.getAddress())
          .content(attachment)
          .build()
      );
    }
  }


}
