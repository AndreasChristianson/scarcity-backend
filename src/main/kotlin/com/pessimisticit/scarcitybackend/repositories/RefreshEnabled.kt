package com.pessimisticit.scarcitybackend.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable
import javax.persistence.EntityManager
import javax.transaction.Transactional

@NoRepositoryBean
interface EnhancedRepository<T, ID : Serializable> : JpaRepository<T, ID> {
    fun refresh(t: T)
}

class EnhancedRepositoryImpl<T, ID : Serializable>(
    entityInformation: JpaEntityInformation<T, ID>,
    val entityManager: EntityManager
) : SimpleJpaRepository<T, ID>(
    entityInformation,
    entityManager
), EnhancedRepository<T, ID> {
    @Transactional
    override fun refresh(t: T) {
        entityManager.refresh(t)
    }
}
/*
@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
  void refresh(T t);
}


public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
    implements CustomRepository<T, ID> {

  private final EntityManager entityManager;

  public CustomRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public void refresh(T t) {
    entityManager.refresh(t);
  }
}
 */