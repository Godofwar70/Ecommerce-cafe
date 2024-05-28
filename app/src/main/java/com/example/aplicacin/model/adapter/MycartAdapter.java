package com.example.aplicacin.model.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacin.R;
import com.example.aplicacin.model.MycartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MycartAdapter extends RecyclerView.Adapter<MycartAdapter.ViewHolder> {

    public Context context;
    public List<MycartModel> mycartModelList;
    FirebaseFirestore firestore;

    public MycartAdapter(Context context, List<MycartModel> mycartModelList) {
        this.context = context;
        this.mycartModelList = mycartModelList;
        firestore = FirebaseFirestore.getInstance();
    }


    @NonNull
    @Override
    public MycartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MycartAdapter.ViewHolder holder, int position) {

        holder.ProductName.setText(mycartModelList.get(position).getProductName());
        holder.Precio.setText(mycartModelList.get(position).getProductPrice());
        holder.Hora.setText(mycartModelList.get(position).getCurrentTime());
        holder.fecha.setText(mycartModelList.get(position).getCurrentDate());
        holder.cantidades.setText(mycartModelList.get(position).getCantidades());
        Log.d("MycartAdapter", "Cantidad: " + mycartModelList.get(position).getCantidades());
        holder.totales.setText(mycartModelList.get(position).getTotalprice());
        final int finalposition = position;
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("AddToCart").document(mycartModelList.get(finalposition).getDocumentId()).delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    mycartModelList.remove(mycartModelList.get(finalposition));

                                    notifyDataSetChanged();
                                    Toast.makeText(context,"Se elimino",Toast.LENGTH_SHORT).show();

                                }else{
                                    Toast.makeText(context,"Error"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });

    }

    @Override
    public int getItemCount() {
        return mycartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ProductName,Precio,Hora,fecha,cantidades,totales;
        Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ProductName = itemView.findViewById(R.id.textProducto);
            Precio = itemView.findViewById(R.id.textPrice);
            Hora= itemView.findViewById(R.id.textTime);
            fecha = itemView.findViewById(R.id.textDATE);
            cantidades = itemView.findViewById(R.id.textTotal);
            totales = itemView.findViewById(R.id.textTotales);
            delete = itemView.findViewById(R.id.buttonItemDelete);
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (MycartModel item : mycartModelList) {
            total += Double.parseDouble(item.getTotalprice());
        }
        return total;
    }

}
