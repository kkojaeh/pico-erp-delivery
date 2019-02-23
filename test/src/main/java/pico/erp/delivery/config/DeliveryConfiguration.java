package pico.erp.delivery.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pico.erp.delivery.message.DeliveryMessage;
import pico.erp.delivery.send.DeliverySendRequests.SendRequest;
import pico.erp.delivery.send.FaxDeliverySendService;
import pico.erp.delivery.send.MailDeliverySendService;
import pico.erp.shared.Public;

@Configuration
public class DeliveryConfiguration {

  @Public
  @Bean
  @ConditionalOnMissingBean(FaxDeliverySendService.class)
  public FaxDeliverySendService noOpFaxDeliverySendService() {
    return new FaxDeliverySendService() {


      @Override
      public void send(SendRequest request, DeliveryMessage message) {
        System.out.println("mail send :" + request + " : " + message);
      }
    };
  }

  @Public
  @Bean
  @ConditionalOnMissingBean(MailDeliverySendService.class)
  public MailDeliverySendService noOpMailDeliverySendService() {
    return new MailDeliverySendService() {

      @Override
      public void send(SendRequest request, DeliveryMessage message) {
        System.out.println("mail send :" + request + " : " + message);
      }
    };
  }

}
