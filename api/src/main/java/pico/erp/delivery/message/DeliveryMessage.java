package pico.erp.delivery.message;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pico.erp.shared.data.ContentInputStream;

public interface DeliveryMessage {

  List<ContentInputStream> getAttachments();

  String getBody();

  String getTitle();

  @Getter
  @Builder
  @AllArgsConstructor
  class Impl implements DeliveryMessage {

    private String title;

    private String body;

    private List<ContentInputStream> attachments;

  }

}
