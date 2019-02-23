package pico.erp.delivery

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import pico.erp.delivery.send.FaxDeliverySendService
import pico.erp.delivery.send.MailDeliverySendService
import pico.erp.delivery.subject.DeliverySubjectDefinition
import pico.erp.delivery.subject.DeliverySubjectId
import pico.erp.delivery.subject.DeliverySubjectRequests
import pico.erp.delivery.subject.DeliverySubjectService
import pico.erp.shared.IntegrationConfiguration
import pico.erp.shared.Public
import pico.erp.user.UserId
import spock.lang.Specification

@SpringBootTest(classes = [IntegrationConfiguration])
@Transactional
@Rollback
@ActiveProfiles("test")
@Configuration
class DeliveryServiceSpec extends Specification {


  @Autowired
  MailDeliverySendService emailDeliveryDefinition

  @Autowired
  FaxDeliverySendService faxDeliveryDefinition

  @Public
  @Bean
  MailDeliverySendService testEmailDeliveryDefinition() {
    return Mock(MailDeliverySendService)
  }

  @Public
  @Bean
  FaxDeliverySendService testFaxDeliveryDefinition() {
    return Mock(FaxDeliverySendService)
  }

  @Public
  @Bean
  DeliverySubjectDefinition testDeliverySubjectDefinition() {
    return DeliverySubjectDefinition.Impl.builder()
      .id(subjectId)
      .name("테스트")
      .attachmentsGetter({ k -> Collections.emptyList() })
      .contextGetter({ k -> [name: "테스트", key: k] })
      .keyConverter({ k -> k })
      .keySerializer({ k -> k })
      .build()
  }

  @Lazy
  @Autowired
  DeliveryService deliveryService

  @Lazy
  @Autowired
  DeliverySubjectService deliverySubjectService


  static def titleTemplate = """{{name}} Hello {{key}}"""

  static def bodyTemplate = """body {{name}} Hello {{key}}"""

  static def subjectId = DeliverySubjectId.from("TEST")

  def id = DeliveryId.from("test")

  def requesterId = UserId.from("kjh")


  def setup() {
    deliverySubjectService.update(
      new DeliverySubjectRequests.UpdateRequest(
        id: subjectId,
        name: "테스트",
        titleTemplate: titleTemplate,
        bodyTemplate: bodyTemplate
      )
    )
  }

  def create() {
    deliveryService.create(
      new DeliveryRequests.CreateRequest(
        id: id,
        subjectId: subjectId,
        key: "key1"
      )
    )
  }

  def deliver(method, address) {
    deliveryService.deliver(
      new DeliveryRequests.DeliverRequest(
        id: id,
        method: method,
        address: address,
        requesterId: requesterId
      )
    )
  }

  def "FAX 전송"() {
    when:
    create()
    def result = deliver(DeliveryMethodKind.FAX, "+021111111")

    then:
    result.address == "+021111111"
    result.method == DeliveryMethodKind.FAX
    result.successful == true
  }

}
