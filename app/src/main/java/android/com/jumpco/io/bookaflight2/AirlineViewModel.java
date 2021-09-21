package android.com.jumpco.io.bookaflight2;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AirlineViewModel  extends AndroidViewModel {

    private AirlineRepository repository;
    private LiveData<List<Airline>> allAirlines;

    public AirlineViewModel(@NonNull Application application) {
        super(application);
        repository = new AirlineRepository(application);
        allAirlines = repository.getAllAirlines();
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
