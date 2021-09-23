package android.com.jumpco.io.bookaflight2;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class AirlineRepository {
    //prepopulated liyt of airlines
    private AirlineDao airlineDao;
    private LiveData<List<Airline>> allAirlines;


    private BookingDao bookingDao;
    private LiveData<List<Booking>> allBookings;



    public AirlineRepository(Application application){
        AirlineDatabase database = AirlineDatabase.getInstance(application);
        airlineDao = database.airlineDao();
        allAirlines = airlineDao.getAllAirlines();


        bookingDao = database.bookingDao();
        allBookings = bookingDao.getAllBookings();
    }

    // insert a new booking
    public void insertBooking(Booking booking){
        new InsertBookingAsyncTask(bookingDao).execute(booking);

    }
    //update the selected booking
    public void updateBooking(Booking booking){
        new UpdateBookingAsyncTask(bookingDao).execute(booking);

    }
    //delete selected booking
    public void deleteBooking(Booking booking){
        new DeleteBookingAsyncTask(bookingDao).execute(booking);
    }

    // delete all available bookings
    public void deleteAllBookings(){
        new DeleteAllBookingAsyncTask(bookingDao).execute();
    }

    public LiveData<List<Booking>> getAllBookings(){
        return allBookings;
    }

    private static class InsertBookingAsyncTask extends AsyncTask<Booking,Void,Void> {

        private BookingDao bookingDao;
        private InsertBookingAsyncTask(BookingDao bookingDao){
            this.bookingDao = bookingDao;
        }


        @Override
        protected Void doInBackground(Booking... bookings) {
            bookingDao.insert(bookings[0]);
            return null;
        }
    }

    private static class UpdateBookingAsyncTask extends AsyncTask<Booking,Void,Void> {

        private BookingDao bookingDao;
        private UpdateBookingAsyncTask(BookingDao bookingDao){
            this.bookingDao = bookingDao;
        }


        @Override
        protected Void doInBackground(Booking... bookings) {
            bookingDao.update(bookings[0]);
            return null;
        }
    }

    private static class DeleteBookingAsyncTask extends AsyncTask<Booking,Void,Void> {

        private BookingDao bookingDao;
        private DeleteBookingAsyncTask(BookingDao bookingDao){
            this.bookingDao = bookingDao;
        }

        @Override
        protected Void doInBackground(Booking... bookings) {
            bookingDao.delete(bookings[0]);
            return null;
        }
    }

    private static class DeleteAllBookingAsyncTask extends AsyncTask<Void,Void,Void> {

        private BookingDao bookingDao;
        private DeleteAllBookingAsyncTask(BookingDao bookingDao){this.bookingDao = bookingDao;}

        /**
         * @param
         * @deprecated
         */
        @Override
        protected Void doInBackground(Void... voids) {
            bookingDao.deleteAllBookings();
            return null;
        }
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
