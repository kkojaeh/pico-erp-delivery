package pico.erp.delivery.subject;

import static org.springframework.util.StringUtils.isEmpty;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import pico.erp.shared.Public;
import pico.erp.shared.jpa.QueryDslJpaSupport;

@Service
@Public
@Transactional(readOnly = true)
@Validated
public class DeliverySubjectQueryJpa implements DeliverySubjectQuery {

  private final QDeliverySubjectEntity deliverySubject = QDeliverySubjectEntity.deliverySubjectEntity;

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private QueryDslJpaSupport queryDslJpaSupport;

  @Override
  public Page<DeliverySubjectView> retrieve(DeliverySubjectView.Filter filter, Pageable pageable) {
    val query = new JPAQuery<DeliverySubjectView>(entityManager);
    val select = Projections.bean(DeliverySubjectView.class,
      deliverySubject.id,
      deliverySubject.name,
      deliverySubject.createdBy,
      deliverySubject.createdDate
    );

    query.select(select);
    query.from(deliverySubject);

    val builder = new BooleanBuilder();

    if (!isEmpty(filter.getName())) {
      builder.and(deliverySubject.name
        .likeIgnoreCase(queryDslJpaSupport.toLikeKeyword("%", filter.getName(), "%")));
    }

    query.where(builder);
    return queryDslJpaSupport.paging(query, pageable, select);
  }
}
