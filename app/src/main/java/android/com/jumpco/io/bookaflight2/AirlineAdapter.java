package android.com.jumpco.io.bookaflight2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AirlineAdapter extends RecyclerView.Adapter<AirlineAdapter.AirlineHolder>{

    private List<Airline> airlines = new ArrayList<>();

    private OnClickListener listener;


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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition(); //ALT CTRL L to format
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(airlines.get(position));

                    }

                }
            });
        }
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;

    }

    //I will use this method to get object of the selected airline
    public Airline getAirlineAt(int position) {
        return airlines.get(position);
    }
}
