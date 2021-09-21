package android.com.jumpco.io.bookaflight2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AirlineAdapter extends RecyclerView.Adapter<AirlineAdapter.AirlineHolder>{

    private List<Airline> airlines = new ArrayList<>();


    @NonNull
    @Override
    public AirlineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.airline_item, parent, false);

        return new AirlineHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull AirlineHolder holder, int position) {
        Airline airline = airlines.get(position);
        holder.textViewAirline.setText(airline.getNameOfAirline());
        holder.textViewPrice.setText(String.valueOf(airline.getBasePriceForPerson()));
        holder.textViewReference.setText(String.valueOf(airline.isBooked()));

    }

    @Override
    public int getItemCount() {
        return airlines.size();
    }
    public void setAirlines(List<Airline> airlines){
        this.airlines = airlines;
        notifyDataSetChanged();

    }

    public class AirlineHolder extends RecyclerView.ViewHolder {
        private TextView textViewAirline;
        private TextView textViewPrice;
        private TextView textViewReference;

        public AirlineHolder(@NonNull View itemView) {
            super(itemView);
            textViewAirline = itemView.findViewById(R.id.text_view_airline);
            textViewPrice = itemView.findViewById(R.id.text_view_price);
            textViewReference = itemView.findViewById(R.id.text_view_book_reference);
        }
    }
}
