package pico.erp.delivery;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import pico.erp.shared.ApplicationId;
import pico.erp.shared.ApplicationStarter;
import pico.erp.shared.SpringBootConfigs;
import pico.erp.shared.impl.ApplicationImpl;
import pico.erp.user.UserApi;

@Slf4j
@SpringBootConfigs
public class DeliveryApplication implements ApplicationStarter {

  public static final String CONFIG_NAME = "delivery/application";

  public static final Properties DEFAULT_PROPERTIES = new Properties();

  static {
    DEFAULT_PROPERTIES.put("spring.config.name", CONFIG_NAME);
  }

  public static SpringApplication application() {
    return new SpringApplicationBuilder(DeliveryApplication.class)
      .properties(DEFAULT_PROPERTIES)
      .web(false)
      .build();
  }

  @SneakyThrows
  public static void main(String[] args) {
    application().run(args);
  }

  @Override
  public Set<ApplicationId> getDependencies() {
    return Stream.of(UserApi.ID).collect(Collectors.toSet());
  }

  @Override
  public ApplicationId getId() {
    return DeliveryApi.ID;
  }

  @Override
  public boolean isWeb() {
    return false;
  }

  @Bean
  public MustacheFactory mustacheFactory() {
    val factory = new DefaultMustacheFactory();
    return factory;
  }

  @Override
  public pico.erp.shared.Application start(String... args) {
    return new ApplicationImpl(application().run(args));
  }

}