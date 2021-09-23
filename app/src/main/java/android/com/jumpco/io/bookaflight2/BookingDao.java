package android.com.jumpco.io.bookaflight2;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BookingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Booking booking);
    @Update
    void update(Booking booking);
    @Delete
    void delete(Booking booking);
    @Query("DELETE FROM Booking")
    void deleteAllBookings();
    @Query("SELECT * FROM Booking ORDER BY id DESC")
    LiveData<List<Booking>> getAllBookings();
}
