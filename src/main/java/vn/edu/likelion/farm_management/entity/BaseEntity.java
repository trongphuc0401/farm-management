package vn.edu.likelion.farm_management.entity;

/**
 * BaseEntity -
 *
 * @param
 * @return
 * @throws
 */
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
import vn.edu.likelion.farm_management.common.utils.UniqueID;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * BaseEntity -
 *
 * @param
 * @return
 * @throws
 */

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    String id;

    @Column(nullable = true, updatable = false)
    LocalDateTime createAt;

    @Column(nullable = true, insertable = false)
    LocalDateTime updateAt;

    @Column
    int isDeleted;

    @PrePersist
    protected void onCreate() {

        if (id == null) {
            id = UniqueID.getUUID();
        }
        this.createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }
}