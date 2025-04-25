package vn.edu.likelion.farm_management.common.enums;

/**
 * Role -
 *
 * @param
 * @return
 * @throws
 */
public enum Role {
    ADMIN("admin"),
    USER("user");

    private String role;

    Role(String role){
        this.role = role;
    }
    public String getValue(){
        return role;
    }
}
