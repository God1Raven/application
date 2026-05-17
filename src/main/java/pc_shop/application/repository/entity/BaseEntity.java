package pc_shop.application.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@MappedSuperclass
public abstract class BaseEntity<ID> extends EntityWithId<ID> {

    @Column(name = "created_at", nullable = false, length = 40, updatable = false)
    @CreatedDate
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, length = 40)
    @LastModifiedDate
    private Instant updatedAt;
}
