package pico.erp.delivery;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pico.erp.shared.ApplicationInitializer;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
public class DeliveryInitializer implements ApplicationInitializer {

  @Lazy
  @Autowired
  private List<DeliveryInitializable> initializables;

  @Override
  public void initialize() {
    initializables.stream().forEach(DeliveryInitializable::initialize);
  }

  public interface DeliveryInitializable {

    void initialize();

  }
}
