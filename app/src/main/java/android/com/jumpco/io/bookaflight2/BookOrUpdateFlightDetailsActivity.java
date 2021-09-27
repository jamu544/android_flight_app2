package android.com.jumpco.io.bookaflight2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookOrUpdateFlightDetailsActivity extends AppCompatActivity {

    private TextView textViewFlightName;
    private NumberPicker numberPickerNoOfAdults;
    private NumberPicker numberPickerNoOfChildren;
    private Spinner spinnerTravelTo;
    private Spinner spinnerTravelFrom;
    private EditText editTextDepartDate;
    private EditText editTextArrivalDate;
    private Button buttonSaveBooking;
    private EditText editTextBasePriceForAdults;

    DatePickerDialog datePickerDialog;
    private String travelTo;


    private String flightNamefromMainActivity;
    private int ticketBasePrice;

    int numberOfAdults;
    int numberOfChildren;
    String traveloFrom;
    String departingDate;
    String arrivalDate;

    private String todaysDate;



    //ticket calculation values

    private long kidsBasePrice;
    private int totalCostForAdults;
    private int totalCostsForKids;
    private double totalCostOfTheTickets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_or_update_flight_details);
        setTitle("Flight Details:");



        todaysDate = getDateOfTheDayOfBooking();
        init();
        Intent intent = getIntent();
        flightNamefromMainActivity = intent.getStringExtra(Constant.EXTRA_FLIGHT_NAME);
        ticketBasePrice = intent.getIntExtra(Constant.EXTRA_BASE_PRICE,1);



        textViewFlightName.setText(flightNamefromMainActivity);


        buttonSaveBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveBooking();
            }
        });

//        if (intent.hasExtra(EXTRA_ID)) {
//            setTitle("Edit Note");
//            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
//            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
//            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
//
//        } else {
//            setTitle("Add Note");
//        }


    }

    public void init() {
        textViewFlightName = findViewById(R.id.text_view_booked_flight_name);
        numberPickerNoOfAdults = findViewById(R.id.number_picker_adults);
        numberPickerNoOfChildren = findViewById(R.id.number_picker_children);
        spinnerTravelTo = findViewById(R.id.spinner_travel_to);
        spinnerTravelFrom = findViewById(R.id.spinner_travel_from);

        // travel from spinner and adapter
        ArrayAdapter<CharSequence> adapterTravelFrom = ArrayAdapter.createFromResource(
                this, R.array.destinations, android.R.layout.simple_spinner_item);
        adapterTravelFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTravelFrom.setAdapter(adapterTravelFrom);
        spinnerTravelFrom.setOnItemSelectedListener(new android.com.jumpco.io.bookaflight2.BookOrUpdateFlightDetailsActivity.DestinationOnItemSelectedListener());

        // travel to spinner and adapter
        ArrayAdapter<CharSequence> adapterTravelTo = ArrayAdapter.createFromResource(
                this, R.array.destinations, android.R.layout.simple_spinner_item);
        adapterTravelTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTravelTo.setAdapter(adapterTravelTo);
        spinnerTravelTo.setOnItemSelectedListener(new android.com.jumpco.io.bookaflight2.BookOrUpdateFlightDetailsActivity.DestinationOnItemSelectedListener());

        editTextDepartDate = findViewById(R.id.edit_text_depart_date);
        editTextArrivalDate = findViewById(R.id.edit_text_arrive_date);

        editTextDepartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(BookOrUpdateFlightDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editTextDepartDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }

        });


        editTextArrivalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(BookOrUpdateFlightDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editTextArrivalDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }

        });
        numberPickerNoOfAdults.setMinValue(1);
        numberPickerNoOfAdults.setMaxValue(5);

        numberPickerNoOfChildren.setMinValue(1);
        numberPickerNoOfChildren.setMaxValue(5);
        buttonSaveBooking = findViewById(R.id.button_save_booking);
    }

    //save data to booking table
    public void saveBooking() {






    //    String basePriceForAdults = editTextBasePriceForAdults.getText().toString();
         numberOfAdults = numberPickerNoOfAdults.getValue();
         numberOfChildren = numberPickerNoOfChildren.getValue();
         travelTo = (String) spinnerTravelTo.getSelectedItem();
         traveloFrom = (String) spinnerTravelFrom.getSelectedItem();
         departingDate = editTextDepartDate.getText().toString();
         arrivalDate = editTextArrivalDate.getText().toString();

        Intent data = new Intent();
        data.putExtra(Constant.EXTRA_FLIGHT_NAME, flightNamefromMainActivity);
  //      data.putExtra(Constant.EXTRA_BASE_PRICE_FOR_ADULTS, basePriceForAdults);
  //      data.putExtra(Constant.EXTRA_BASE_PRICE_FOR_CHILDREN, basePriceForChildren);
        data.putExtra(Constant.EXTRA_NO_OF_ADULTS, numberOfAdults);
        data.putExtra(Constant.EXTRA_NO_OF_CHILDREN, numberOfChildren);
        data.putExtra(Constant.EXTRA_TRAVEL_TO, travelTo);
        data.putExtra(Constant.EXTRA_TRAVEL_FROM, traveloFrom);
        data.putExtra(Constant.EXTRA_DEPART_DATE, departingDate);
        data.putExtra(Constant.EXTRA_ARRIVAL_DATE, arrivalDate);
//        int id = getIntent().getIntExtra(EXTRA_ID,-1);
//        if(id != -1){
//            data.putExtra(EXTRA_ID,id);
//        }

        kidsBasePrice = calculateKidsBasePrice(ticketBasePrice);

        totalCostForAdults = totalCostOfTheTicketForAdults(ticketBasePrice,numberOfAdults);

        totalCostsForKids = (int) totalCostOfTheTicketForKids(kidsBasePrice,numberOfChildren);

        totalCostOfTheTickets =  totalPriceOfTheBooking(totalCostsForKids, totalCostForAdults);



        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm Booking");
        builder.setMessage("Today : "+todaysDate+"\n"
                +"Flight     "+flightNamefromMainActivity+"\n"+
                "Depart     "+departingDate+"  "+traveloFrom+"\n"+
                "Arrive       "+arrivalDate + "  "+travelTo+"\n"+
                "------------------ "+"\n"+
                "Adults     "+numberOfAdults+"x"+ticketBasePrice+"\n"+
                "Kids         "+numberOfChildren+"x" +kidsBasePrice+
                ""+"\n"+
                "Total Price    "+totalCostOfTheTickets+"\n");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                setResult(RESULT_OK, data);
                finish();
//                saveBooking();
                Toast.makeText(BookOrUpdateFlightDetailsActivity.this,
                        "Booking successful!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }

    //show dailog
    public void confirmBookingDailog() {

    }

    //inner interface for setting destinations
    public class DestinationOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
            Toast.makeText(BookOrUpdateFlightDetailsActivity.this, "Selected: " +
                    parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }

    //calculate 30% off from the base price for kids
    private long calculateKidsBasePrice(int ticketBasePrice){

        double amount = ticketBasePrice;
        double results = (amount / 100.0f) * 30;

        return (long) results;
    }

    private double totalCostOfTheTicketForKids(double kidsBasePrice, int numberOfChildren){
        double totalCost  =  numberOfChildren * kidsBasePrice;

        return totalCost;
    }

    // sum of the ticket
    private long totalPriceOfTheBooking(int childrenTotalPrice,int totalCostOfTheTicketForAdults){

        long sum = childrenTotalPrice + totalCostOfTheTicketForAdults;
        return sum;
    }

    // calculate the base price of the ticket for the selected number of adults
    private int totalCostOfTheTicketForAdults(int ticketBasePrice,int numberOfAdults ){

        int totalCost  = numberOfAdults * ticketBasePrice;

        return totalCost;
    }



    @Override // disable on Back button
    public void onBackPressed() {  }

    //get the date of the day a booking is made
    private String getDateOfTheDayOfBooking() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }



}