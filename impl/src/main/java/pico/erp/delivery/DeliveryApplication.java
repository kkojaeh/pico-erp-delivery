package pico.erp.delivery;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;
import kkojaeh.spring.boot.component.ComponentBean;
import kkojaeh.spring.boot.component.SpringBootComponent;
import kkojaeh.spring.boot.component.SpringBootComponentBuilder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pico.erp.ComponentDefinition;
import pico.erp.delivery.DeliveryApi.Roles;
import pico.erp.shared.SharedConfiguration;
import pico.erp.shared.data.Role;

@Slf4j
@SpringBootComponent("delivery")
@EntityScan
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "dateTimeProvider")
@SpringBootApplication
@Import(value = {
  SharedConfiguration.class
})
public class DeliveryApplication implements ComponentDefinition {

  public static void main(String[] args) {
    new SpringBootComponentBuilder()
      .component(DeliveryApplication.class)
      .run(args);
  }

  @Bean
  @ComponentBean(host = false)
  public Role deliveryAccessor() {
    return Roles.DELIVERY_ACCESSOR;
  }

  @Bean
  public MustacheFactory mustacheFactory() {
    val factory = new DefaultMustacheFactory();
    return factory;
  }

  @Bean
  @ComponentBean(host = false)
  public Role deliveryCharger() {
    return Roles.DELIVERY_CHARGER;
  }

  @Bean
  @ComponentBean(host = false)
  public Role deliveryManager() {
    return Roles.DELIVERY_MANAGER;
  }

  @Override
  public Class<?> getComponentClass() {
    return DeliveryApplication.class;
  }

}
