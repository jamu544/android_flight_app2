package android.com.jumpco.io.bookaflight2;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AirlineDao {
    @Insert
    void insert(Airline airline);
    @Update
    void update(Airline airline);
    @Delete
    void delete(Airline airline);
    @Query("DELETE FROM Airline")
    void deleteAllAirlines();
    @Query("SELECT * FROM Airline ORDER BY id DESC")
    LiveData<List<Airline>> getAllAirlines();

}
