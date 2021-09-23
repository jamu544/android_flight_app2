package android.com.jumpco.io.bookaflight2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BookOrUpdateFlightDetailsActivity extends AppCompatActivity {

    private TextView textViewFlightName;
    private NumberPicker numberPickerNoOfAdults;
    private NumberPicker numberPickerNoOfChildren;
    private Spinner spinnerTravelTo;
    private Spinner spinnerTravelFrom;
    private EditText editTextDepartDate;
    private EditText editTextArrivalDate;
    private Button buttonSaveBooking;

    private EditText editTextBasePriceForChildren;
    private EditText editTextBasePriceForAdults;
    private String traveloFrom;
    private String travelTo;



    private String[] colors1 = {"  Black","  Brown","  Red","  Orange","  Yellow","  Green","  Blue","  Violet","  Grey","  White"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_or_update_flight_details);
        init();









        buttonSaveBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmBookingDailog();
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
        editTextBasePriceForChildren = findViewById(R.id.edit_text_chidlren_base_price);
        spinnerTravelTo = findViewById(R.id.spinner_travel_to);
        spinnerTravelFrom = findViewById(R.id.spinner_travel_from);

        // travel from spinner and adapter
        ArrayAdapter<CharSequence> adapterTravelFrom = ArrayAdapter.createFromResource(
                this, R.array.destinations, android.R.layout.simple_spinner_item);
        adapterTravelFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTravelFrom.setAdapter(adapterTravelFrom);
        spinnerTravelFrom.setOnItemSelectedListener(new MyOnItemSelectedListener());

        // travel to spinner and adapter
        ArrayAdapter<CharSequence> adapterTravelTo = ArrayAdapter.createFromResource(
                this, R.array.destinations, android.R.layout.simple_spinner_item);
        adapterTravelTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTravelTo.setAdapter(adapterTravelTo);
        spinnerTravelTo.setOnItemSelectedListener(new MyOnItemSelectedListener());















        editTextDepartDate = findViewById(R.id.edit_text_depart_date);
        editTextArrivalDate = findViewById(R.id.edit_text_arrive_date);

        numberPickerNoOfAdults.setMinValue(1);
        numberPickerNoOfAdults.setMaxValue(5);

        numberPickerNoOfChildren.setMinValue(1);
        numberPickerNoOfChildren.setMaxValue(5);
        buttonSaveBooking = findViewById(R.id.button_save_booking);
    }

    //save data to booking table
    public void saveBooking() {

        String flightName = textViewFlightName.getText().toString();
    //    String basePriceForAdults = editTextBasePriceForAdults.getText().toString();
        String basePriceForChildren = editTextBasePriceForChildren.getText().toString();
        int numberOfAdults = numberPickerNoOfAdults.getValue();
        int numberOfChildren = numberPickerNoOfChildren.getValue();
         travelTo =  (String) spinnerTravelTo.getSelectedItem();
        traveloFrom =    (String) spinnerTravelFrom.getSelectedItem();


        String departingDate = editTextDepartDate.getText().toString();
        String arrivalDate = editTextArrivalDate.getText().toString();

        Intent data = new Intent();
        data.putExtra(Constant.EXTRA_FLIGHT_NAME, flightName);
  //      data.putExtra(Constant.EXTRA_BASE_PRICE_FOR_ADULTS, basePriceForAdults);
        data.putExtra(Constant.EXTRA_BASE_PRICE_FOR_CHILDREN, basePriceForChildren);
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
        setResult(RESULT_OK, data);
        finish();


    }

    //show dailog
    public void confirmBookingDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm Booking");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                saveBooking();
                Toast.makeText(BookOrUpdateFlightDetailsActivity.this, "Booking successful!", Toast.LENGTH_SHORT).show();

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

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
            Toast.makeText(BookOrUpdateFlightDetailsActivity.this, "The planet is " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }


}