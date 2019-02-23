package pico.erp.delivery.subject;

import java.util.List;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pico.erp.shared.data.ContentInputStream;

public interface DeliverySubjectDefinition<K, C> {

  List<ContentInputStream> getAttachments(K key);

  C getContext(K key);

  DeliverySubjectId getId();

  String getName();

  K toKey(String key);

  String toString(K key);

  @Getter
  @Builder
  @AllArgsConstructor
  class Impl<K, C> implements DeliverySubjectDefinition<K, C> {

    DeliverySubjectId id;

    String name;

    Function<K, C> contextGetter;

    Function<K, List<ContentInputStream>> attachmentsGetter;

    Function<String, K> keyConverter;

    Function<K, String> keySerializer;

    @Override
    public List<ContentInputStream> getAttachments(K key) {
      return attachmentsGetter.apply(key);
    }

    @Override
    public C getContext(K key) {
      return contextGetter.apply(key);
    }

    @Override
    public K toKey(String key) {
      return keyConverter.apply(key);
    }

    @Override
    public String toString(K key) {
      return keySerializer.apply(key);
    }

  }

}
