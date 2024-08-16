package vn.edu.likelion.farm_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Columns;

import java.util.UUID;

@Entity
@Table(name = "tbl_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity {

    @Column
    String username;

    @Column
    String password;

    @Column
    String firstName;

    @Column
    String lastName;

    @Column
    int gender;

    @Column
    String email;

    @Column
    String phone;

    @Column
    String urlAvatar;

    @Column
    String urlBanner;

    @Column
    String local;


}
