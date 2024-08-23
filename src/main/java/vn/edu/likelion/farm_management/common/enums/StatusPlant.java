package vn.edu.likelion.farm_management.common.enums;

/**
 * StatusPlant -
 *
 * @param
 * @return
 * @throws
 */
public enum StatusPlant {
    UNPLANNED(0,"Unplanned"),
    SEEDLING(1,"Seedling"),
    VEGETATIVE(2,"Vegetative"),
    FLOWERING(3,"Flowering"),
    FRUITING(4,"Fruiting"),
    HARVESTED(5,"Harvested");


    private final int code;
    private final String displayName;

    StatusPlant(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
}
