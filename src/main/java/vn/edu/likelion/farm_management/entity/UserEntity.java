package vn.edu.likelion.farm_management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.edu.likelion.farm_management.common.enums.Role;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tbl_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseEntity implements UserDetails {

    String username;

    @Column(nullable = false)
    String password;


    String firstName;


    String lastName;


    int gender;

    @Column(unique = true,nullable = false)
    String email;

    @Column()
    String phone;

    @Column
    String urlAvatar;

    @Column
    String urlBanner;

    @Column
    String local;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {

        if (this.role == Role.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
          //  return List.of(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("USER"));
        }

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
    @Override public String getPassword() {
        return password;
    }

    @Override public String getUsername() {
        return email;

    }




}
