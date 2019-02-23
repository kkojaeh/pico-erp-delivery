package pico.erp.delivery.send;

import javax.mail.util.ByteArrayDataSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import pico.erp.delivery.message.DeliveryMessage;
import pico.erp.delivery.send.DeliverySendRequests.SendRequest;

public class SpringMailDeliverySendService implements MailDeliverySendService {

  private final JavaMailSender sender;

  private final String fromEmail;

  private final String fromName;

  public SpringMailDeliverySendService(Config config) {
    sender = config.getSender();
    fromEmail = config.getFromEmail();
    fromName = config.getFromName();
  }

  @SneakyThrows
  @Override
  public void send(SendRequest request, DeliveryMessage message) {
    val mimeMessage = sender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    helper.setTo(request.getAddress());
    helper.setSubject(message.getTitle());
    helper.setText(message.getBody());
    helper.setFrom(fromEmail, fromName);
    for (val attachment : message.getAttachments()) {
      helper.addAttachment(
        attachment.getName(),
        new ByteArrayDataSource(attachment, attachment.getContentType())
      );
    }
    sender.send(mimeMessage);
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @Data
  public static class Config {

    JavaMailSender sender;

    String fromEmail;

    String fromName;

  }


}
