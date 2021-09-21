package android.com.jumpco.io.bookaflight2;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class AirlineRepository {
    private AirlineDao airlineDao;
    private LiveData<List<Airline>> allAirlines;

    public AirlineRepository(Application application){
        AirlineDatabase database = AirlineDatabase.getInstance(application);
        airlineDao = database.airlineDao();
        allAirlines = airlineDao.getAllAirlines();
    }
    public void insert(Airline airline){
        new InsertAirlineAsyncTask(airlineDao).execute(airline);

    }
    public void update(Airline airline){
        new UpdateAirlineAsyncTask(airlineDao).execute(airline);

    }
    public void delete(Airline airline){
        new DeleteAirlineAsyncTask(airlineDao).execute(airline);
    }

    public void deleteAllNotes(){
        new DeleteAllAirlineAsyncTask(airlineDao).execute();
    }

    public LiveData<List<Airline>> getAllAirlines(){
        return allAirlines;
    }

    private static class InsertAirlineAsyncTask extends AsyncTask<Airline,Void,Void> {

        private AirlineDao airlineDao;
        private InsertAirlineAsyncTask(AirlineDao airlineDao){
            this.airlineDao = airlineDao;
        }


        @Override
        protected Void doInBackground(Airline... airlines) {
            airlineDao.insert(airlines[0]);
            return null;
        }
    }
    private static class UpdateAirlineAsyncTask extends AsyncTask<Airline,Void,Void> {

        private AirlineDao airlineDao;
        private UpdateAirlineAsyncTask(AirlineDao airlineDao){
            this.airlineDao = airlineDao;
        }


        @Override
        protected Void doInBackground(Airline... airlines) {
            airlineDao.update(airlines[0]);
            return null;
        }
    }
    private static class DeleteAirlineAsyncTask extends AsyncTask<Airline,Void,Void> {

        private AirlineDao airlineDao;
        private DeleteAirlineAsyncTask(AirlineDao airlineDao){
            this.airlineDao = airlineDao;
        }

        @Override
        protected Void doInBackground(Airline... airlines) {
            airlineDao.delete(airlines[0]);
            return null;
        }
    }
    private static class DeleteAllAirlineAsyncTask extends AsyncTask<Void,Void,Void> {

        private AirlineDao airlineDao;
        private DeleteAllAirlineAsyncTask(AirlineDao airlineDao){
            this.airlineDao = airlineDao;
        }

        /**
         * @param
         * @deprecated
         */
        @Override
        protected Void doInBackground(Void... voids) {
            airlineDao.deleteAllAirlines();
            return null;
        }
    }

}
