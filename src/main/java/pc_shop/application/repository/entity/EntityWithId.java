package pc_shop.application.repository.entity;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityWithId<ID> {

    public abstract ID getId();
}
