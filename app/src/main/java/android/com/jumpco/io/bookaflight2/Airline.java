package android.com.jumpco.io.bookaflight2;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Airline {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    public String nameOfAirline;
    public int basePriceForPerson;
    public int isBooked;
    public int isDeleted;

    public Airline(int id, String nameOfAirline, int basePriceForPerson, int isBooked, int isDeleted) {
        this.id = id;
        this.nameOfAirline = nameOfAirline;
        this.basePriceForPerson = basePriceForPerson;
        this.isBooked = isBooked;
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfAirline() {
        return nameOfAirline;
    }

    public void setNameOfAirline(String nameOfAirline) {
        this.nameOfAirline = nameOfAirline;
    }

    public int getBasePriceForPerson() {
        return basePriceForPerson;
    }

    public void setBasePriceForPerson(int basePriceForPerson) {
        this.basePriceForPerson = basePriceForPerson;
    }

    public int isBooked() {
        return isBooked;
    }

    public void setBooked(int booked) {
        isBooked = booked;
    }

    public int isDeleted() {
        return isDeleted;
    }

    public void setDeleted(int deleted) {
        isDeleted = deleted;
    }
}
