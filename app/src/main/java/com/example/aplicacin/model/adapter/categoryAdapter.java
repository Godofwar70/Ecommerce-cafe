package com.example.aplicacin.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aplicacin.R;
import com.example.aplicacin.model.category;
import com.squareup.picasso.Picasso;

import java.util.List;



public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.ViewHolder> {


    public Context context;
    public List<category> categoryList;
    public OnItemClickListener onItemClick;


    public categoryAdapter(Context context, List<category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;

    }
    public interface OnItemClickListener {
        void onItemClick(category item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClick = listener;
    }


    @NonNull
    @Override
    public categoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.categoryhome,parent,false) );
    }

    @Override
    public void onBindViewHolder(@NonNull categoryAdapter.ViewHolder holder, int position) {
        Picasso.get().load(categoryList.get(position).getImage_Url()).into(holder.image_Url);

        holder.Producto.setText(categoryList.get(position).getProducto());
        holder.Descripcion.setText(categoryList.get(position).getDescripcion());
        holder.Descuento.setText(categoryList.get(position).getDescuento());
        float calificacion = categoryList.get(position).getEstrella();
        holder.Estrella.setRating(categoryList.get(position).getEstrella());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClick.onItemClick(categoryList.get(position));
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_Url;
        TextView Descripcion,Descuento,Producto;
        RatingBar Estrella;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_Url = itemView.findViewById(R.id.imagenCategory);
            Producto = itemView.findViewById(R.id.categoryproducto);
            Descripcion = itemView.findViewById(R.id.categorydescrip);
            Descuento = itemView.findViewById(R.id.categorydesc);
            Estrella = itemView.findViewById(R.id.categoryEstrella);
        }
    }
}
