package android.com.jumpco.io.bookaflight2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Airline.class,Booking.class}, version = 1,exportSchema = false)
public abstract class AirlineDatabase extends RoomDatabase {
    private static AirlineDatabase instance;

    public abstract AirlineDao airlineDao();

    public abstract BookingDao bookingDao();

    public static synchronized AirlineDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AirlineDatabase.class,"airline_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static AirlineDatabase.Callback roomCallback = new AirlineDatabase.Callback() {
        /**
         * Called when the database is created for the first time. This is called after all the
         * tables are created.
         *
         * @param db The database.
         */
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {

        private AirlineDao airlineDao;

        public PopulateDbAsyncTask (AirlineDatabase db){
            airlineDao = db.airlineDao();
        }
        /**
         * @param voids
         * @deprecated
         */
        @Override
        protected Void doInBackground(Void... voids) {

            airlineDao.insert(new Airline(5,"Qatar Airways",500,1,0));
            airlineDao.insert(new Airline(4,"Emirates",5000,0,0));
            airlineDao.insert(new Airline(3,"British Airways",200,0,0));
            airlineDao.insert(new Airline(2,"Etihad Airways",200,0,0));
            airlineDao.insert(new Airline(1,"United Airlines",200,0,0));
            airlineDao.insert(new Airline(0,"SAA",200,0,0));

            return null;
        }
    }


}
