package vn.edu.likelion.farm_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name ="tbl_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Column()
    private String firstName;
    @Column
    private String lastName;
    @Column(unique=true , nullable=false)
    private String email;
    @Column
    private String username;

    // private String password;

    private String googleId;



}
