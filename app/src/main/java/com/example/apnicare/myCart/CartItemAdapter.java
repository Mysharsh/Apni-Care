package com.example.apnicare.myCart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnicare.R;
import com.example.apnicare.RetrofitClient;
import com.example.apnicare.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {
    private TextView cartTotal;
    private Context context;
    private List<Datum> data;
    int id;
    SharedPrefManager sharedPrefManager;

    public CartItemAdapter(Context context, List<Datum> data , TextView cartTotal) {
        this.context = context;
        this.data = data;
        this.cartTotal=cartTotal;
    }

    //    @NonNull
//    @Override
//    public CartItemAdapter<ViewHolder> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_card,parent,false);
//        return new ViewHolder(view);
//    }
@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_card,parent,false);
    return new ViewHolder(view);
}

    @Override
    public int getItemCount() {
        return data.size();
    }



    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.ViewHolder holder, int position) {

        Datum cartresponse=data.get(position);
        holder.productname.setText(cartresponse.getDrug().getName());
        holder.price.setText(cartresponse.getPrice().toString());
        holder.mrp.setText(cartresponse.getDrug().getMrp().toString());



    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productname;
        TextView price,delete,mrp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            itemView.setOnClickListener(this::onClick);
            mrp=itemView.findViewById(R.id.mrp);
            delete=itemView.findViewById(R.id.deletebtn);
            productname = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.prodctMRP);
            sharedPrefManager=new SharedPrefManager(context);


            price.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "click on Mrp", Toast.LENGTH_SHORT).show();
                }
            });
            productname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "click on name", Toast.LENGTH_SHORT).show();
                }
            });
           delete.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int id;
            id=data.get(getAdapterPosition()).getId();
            deleteitem(id);
//            Intent refresh = new Intent(context,CartActivity.class);
//            startActivity(refresh);
//            context.finish();
//            Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show();
//            notifyDataSetChanged();



        }
    }

    private void deleteitem(int id) {
        Call<CartItemDeleteResponse> call= RetrofitClient.getInstance().getApi().getData(id,"Bearer "+sharedPrefManager.getData().getAccess_token());
        call.enqueue(new Callback<CartItemDeleteResponse>() {
            @Override
            public void onResponse(Call<CartItemDeleteResponse> call, Response<CartItemDeleteResponse> response) {
                CartItemDeleteResponse cartItemDeleteResponse=response.body();
                if (response.isSuccessful());
//                updateTotal();
                Toast.makeText(context,cartItemDeleteResponse.getData().getMessage().toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CartItemDeleteResponse> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();

            }

        });
//        context.notifyDataSetChanged();
        Intent refresh = new Intent(context,CartActivity.class);
            context.startActivity(refresh);
            Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show();
    }


}
