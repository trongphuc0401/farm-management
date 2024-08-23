package vn.edu.likelion.farm_management.common.enums;

/**
 * StatusFarm -
 *
 * @param
 * @return
 * @throws
 */

public enum StatusFarm {
    ACTIVE("Active"),
    FALLOW("Fallow");

    private final String displayName;

     StatusFarm(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}
