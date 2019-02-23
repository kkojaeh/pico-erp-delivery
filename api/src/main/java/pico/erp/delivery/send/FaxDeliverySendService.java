package pico.erp.delivery.send;

import pico.erp.delivery.message.DeliveryMessage;

public interface FaxDeliverySendService {

  void send(DeliverySendRequests.SendRequest request, DeliveryMessage message);

}
