package vn.edu.likelion.farm_management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.UUID;

@Entity
@Table(name ="tbl_user")
@Data
public class UserEntity {

    @Id
    private String id;
    @Column
    private String username;


}
