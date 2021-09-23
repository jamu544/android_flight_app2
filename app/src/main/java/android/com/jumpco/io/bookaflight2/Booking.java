package android.com.jumpco.io.bookaflight2;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Booking {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String flightName;
    private String basePriceForAdults;
    private String basePriceForChildren;
    private int numberOfAdults;
    private int numberOfChildren;
    private String travelTo;
    private String traveloFrom;
    private String departingDate;
    private String arrivalDate;


    public Booking(int id, String flightName, String basePriceForAdults, String basePriceForChildren,
                   int numberOfAdults, int numberOfChildren, String travelTo,
                   String traveloFrom, String departingDate, String arrivalDate) {
        this.id = id;
        this.flightName = flightName;
        this.basePriceForAdults = basePriceForAdults;
        this.basePriceForChildren = basePriceForChildren;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.travelTo = travelTo;
        this.traveloFrom = traveloFrom;
        this.departingDate = departingDate;
        this.arrivalDate = arrivalDate;
    }

    public int getId() {
        return id;
    }

  //  public void setId(int id) {
//        this.id = id;
//    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getBasePriceForAdults() {
        return basePriceForAdults;
    }

    public void setBasePriceForAdults(String basePriceForAdults) {
        this.basePriceForAdults = basePriceForAdults;
    }

    public String getBasePriceForChildren() {
        return basePriceForChildren;
    }

    public void setBasePriceForChildren(String basePriceForChildren) {
        this.basePriceForChildren = basePriceForChildren;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getTravelTo() {
        return travelTo;
    }

    public void setTravelTo(String travelTo) {
        this.travelTo = travelTo;
    }

    public String getTraveloFrom() {
        return traveloFrom;
    }

    public void setTraveloFrom(String traveloFrom) {
        this.traveloFrom = traveloFrom;
    }

    public String getDepartingDate() {
        return departingDate;
    }

    public void setDepartingDate(String departingDate) {
        this.departingDate = departingDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", flightName='" + flightName + '\'' +
                ", basePriceForAdults=" + basePriceForAdults +
                ", basePriceForChildren=" + basePriceForChildren +
                ", numberOfAdults=" + numberOfAdults +
                ", numberOfChildren=" + numberOfChildren +
                ", travelTo='" + travelTo + '\'' +
                ", traveloFrom='" + traveloFrom + '\'' +
                ", departingDate='" + departingDate + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                '}';
    }
}
