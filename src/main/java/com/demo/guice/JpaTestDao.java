package com.demo.guice;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import javax.persistence.EntityManager;

/**
 * @author make
 * @creare 16/10/2018
 */
@Singleton
public class JpaTestDao {

    @Inject
    private Provider<EntityManager> entityManagerProvider;

    public JpaTestEntity findById(long id) {
        return entityManagerProvider.get().find(JpaTestEntity.class, id);
    }

    @Transactional
    public void refresh(JpaTestEntity jpaTestEntity) {
        entityManagerProvider.get().persist(jpaTestEntity);
    }

    public void remove(JpaTestEntity jpaTestEntity) {
        entityManagerProvider.get().remove(jpaTestEntity);
    }


}
