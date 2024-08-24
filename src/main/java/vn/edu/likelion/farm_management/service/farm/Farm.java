package vn.edu.likelion.farm_management.service.farm;

public class Farm {
    private String name;
    private double plantedArea;

    // Constructor
    public Farm(String name, double plantedArea) {
        this.name = name;
        this.plantedArea = plantedArea;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPlantedArea() {
        return plantedArea;
    }

    public void setPlantedArea(double plantedArea) {
        this.plantedArea = plantedArea;
    }
}
