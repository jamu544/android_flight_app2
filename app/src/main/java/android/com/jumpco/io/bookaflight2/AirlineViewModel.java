package android.com.jumpco.io.bookaflight2;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AirlineViewModel  extends AndroidViewModel {

    private AirlineRepository repository;
    private LiveData<List<Airline>> allAirlines;


    private LiveData<List<Booking>> allBookings;

    public AirlineViewModel(@NonNull Application application) {
        super(application);
        repository = new AirlineRepository(application);
        allAirlines = repository.getAllAirlines();
        allBookings = repository.getAllBookings();
    }

    public void insertBookingViewModel(Booking booking){
        repository.insertBooking(booking);
    }
    public void updateBookingViewModel(Booking booking){
        repository.updateBooking(booking);

    }
    public void deleteBookingViewModel(Booking booking){
        repository.deleteBooking(booking);

    }
    public void deleteAllBookingViewModel(Booking booking){
        repository.deleteAllBookings();

    }

    public LiveData<List<Booking>> getAllBookingViewModel() {
        return allBookings;
    }






































    public void insert(Airline airline){
        repository.insert(airline);
    }
    public void update(Airline airline){
        repository.update(airline);

    }
    public void delete(Airline airline){
        repository.delete(airline);

    }
    public void deleteAllAirtlines(Airline airline){
        repository.deleteAllNotes();

    }

    public LiveData<List<Airline>> getAllAirlines() {
        return allAirlines;
    }




}
