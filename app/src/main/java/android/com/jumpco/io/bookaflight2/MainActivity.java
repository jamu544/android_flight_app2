package android.com.jumpco.io.bookaflight2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AirlineViewModel airlineViewModel;

    public static final int ADD_BOOKING_REQUEST = 1;
    public static final int EDIT_BOOKING_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final AirlineAdapter airlineAdapter = new AirlineAdapter();
        recyclerView.setAdapter(airlineAdapter);


        airlineViewModel = new ViewModelProvider(this).get(AirlineViewModel.class);
        airlineViewModel.getAllAirlines().observe(this, new Observer<List<Airline>>() {
            @Override
            public void onChanged(List<Airline> airlines) {
                //update RecyclerView
                airlineAdapter.setAirlines(airlines);

            }
        });

        airlineAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onItemClick(Airline airline) {

                Intent intent = new Intent(MainActivity.this, BookOrUpdateFlightDetailsActivity.class);
                intent.putExtra(Constant.EXTRA_FLIGHT_NAME, airline.nameOfAirline);
                Toast.makeText(MainActivity.this,airline.nameOfAirline, Toast.LENGTH_SHORT).show();

                startActivityForResult(intent, ADD_BOOKING_REQUEST);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_BOOKING_REQUEST && resultCode == RESULT_OK) {


            int id = data.getIntExtra(Constant.EXTRA_ID,-1);
            String flightName = data.getStringExtra(Constant.EXTRA_FLIGHT_NAME);
       //     String basePriceForAdults = data.getStringExtra(Constant.EXTRA_BASE_PRICE_FOR_ADULTS);
            String basePriceForChildren = data.getStringExtra(Constant.EXTRA_BASE_PRICE_FOR_CHILDREN);
            int numberOfAdults   = data.getIntExtra(Constant.EXTRA_NO_OF_ADULTS, 1);
            int numberOfChildren = data.getIntExtra(Constant.EXTRA_NO_OF_CHILDREN,1);
            String travelTo      = data.getStringExtra(Constant.EXTRA_TRAVEL_TO);
            String traveloFrom   = data.getStringExtra(Constant.EXTRA_TRAVEL_FROM);
            String departingDate = data.getStringExtra(Constant.EXTRA_DEPART_DATE);
            String arrivalDate   = data.getStringExtra(Constant.EXTRA_ARRIVAL_DATE);


            // saving to the database
            Booking booking = new Booking(id,flightName,"22",basePriceForChildren,numberOfAdults,
                    numberOfChildren,travelTo,traveloFrom,departingDate,arrivalDate);
            airlineViewModel.insertBookingViewModel(booking);

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();

//        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
//            int id = data.getIntExtra(AddEditNoteActivity.EXTRA_ID, -1);
//
//            if (id == -1) {
//                Toast.makeText(this, "Note can't be saved", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
//            String description = data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
//            int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1);
//
//            Note note = new Note(title, description, priority);
//            note.setId(id);
//            noteViewModel.update(note);
//            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
//
//
        } else {
            Toast.makeText(this, "Booking not saved", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // close the primary database to ensure all the transactions are merged
        AirlineDatabase.getInstance(getApplicationContext()).close();
    }
}