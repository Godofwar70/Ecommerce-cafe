package com.example.aplicacin.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacin.Acftivity.Detalles;
import com.example.aplicacin.R;
import com.example.aplicacin.model.producto;
import com.example.aplicacin.Acftivity.tipoProducto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class productoAdapter extends RecyclerView.Adapter<productoAdapter.ViewHolder> {

    public Context context;
    public List<producto> productoList;


    public productoAdapter(Context context, List<producto> productoList ) {
        this.context = context;
        this.productoList = productoList;
    }


    @NonNull
    @Override
    public productoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tipospro,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull productoAdapter.ViewHolder holder, int position) {
        Picasso.get().load(productoList.get(position).getImage_Url()).into(holder.image_Url);

        holder.Producto.setText(productoList.get(position).getProducto());
        holder.Descripcion.setText(productoList.get(position).getDescripcion());
        holder.price.setText(String.valueOf(productoList.get(position).getPrice()));
        holder.Estrella.setRating(productoList.get(position).getEstrella());
        final int finalposition = position;
        producto currentProduct = productoList.get(position);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tipoProductoIntent = new Intent(context, tipoProducto.class);
                tipoProductoIntent.putExtra("type", productoList.get(finalposition).getType());
                context.startActivity(tipoProductoIntent);

                // Abrir Detalles
                Intent detallesIntent = new Intent(context, Detalles.class);
                detallesIntent.putExtra("image_Url", productoList.get(finalposition).getImage_Url());
                detallesIntent.putExtra("Price", productoList.get(finalposition).getPrice());
                detallesIntent.putExtra("Description", productoList.get(finalposition).getDescripcion());
                detallesIntent.putExtra("Estrella", productoList.get(finalposition).getEstrella());
                detallesIntent.putExtra("Producto",productoList.get(finalposition).getProducto());
                context.startActivity(detallesIntent);
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, Detalles.class);
//                intent.putExtra("image_Url", productoList.get(finalposition).getImage_Url());
//                intent.putExtra("Price", productoList.get(finalposition).getPrice());
//                intent.putExtra("Description", productoList.get(finalposition).getDescripcion());
//                intent.putExtra("Estrella", productoList.get(finalposition).getEstrella());
//                context.startActivity(intent);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_Url;
        TextView Producto,Descripcion,price;
        RatingBar Estrella;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_Url = itemView.findViewById(R.id.tipos);
            Producto = itemView.findViewById(R.id.tiposProductos);
            Descripcion = itemView.findViewById(R.id.tiposDescripcion);
            price = itemView.findViewById(R.id.tiposPrecios);
            Estrella = itemView.findViewById(R.id.tiposEstrella);

        }
    }


}
