package android.com.jumpco.io.bookaflight2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AirlineViewModel airlineViewModel;

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
                Toast.makeText(MainActivity.this,"onChanged", Toast.LENGTH_SHORT).show();

                airlineAdapter.setAirlines(airlines);

            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // close the primary database to ensure all the transactions are merged
        AirlineDatabase.getInstance(getApplicationContext()).close();
    }
}