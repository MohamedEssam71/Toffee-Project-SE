package actors.Attachtments;

/**
 * <h3>Address Model Class</h3>
 * It handle all address details about the user
 * @author Mohamed Essam
 * @author Maya Ayman
 */
public class Address {
    private String governorate;
    private String district;
    private String street;
    private String landmark;
    private Integer buildingNumber;
    private Integer floor;
    private Integer flatNumber;

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    /**
     * This method concatenates all address details in one String
     * @return String the address detailed in one line
     */
    public String toString() {
        return governorate + ", " + district + ", " + street + ", "
                + landmark + ", " + buildingNumber + ", " + floor + ", " + flatNumber;
    }
}
