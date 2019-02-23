package pico.erp.delivery.send;

import pico.erp.delivery.message.DeliveryMessage;

public interface MailDeliverySendService {

  void send(DeliverySendRequests.SendRequest request, DeliveryMessage message);

}
