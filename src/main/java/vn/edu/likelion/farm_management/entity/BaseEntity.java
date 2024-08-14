package vn.edu.likelion.farm_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
import vn.edu.likelion.farm_management.utils.UniqueID;

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

public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = true , updatable = false )
    private LocalDateTime createTime;

    @Column(nullable = true , insertable = false)
    private LocalDateTime updateTime;

    @PrePersist
    protected void onCreate() {

        if (id == null) {
            id = UniqueID.getUUID();
        }
        this.createTime = LocalDateTime.now();

        updateTime = LocalDateTime.now();

    }
}
