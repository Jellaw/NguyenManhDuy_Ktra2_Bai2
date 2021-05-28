package com.example.nguyenmanhduy_ktra2_bai2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Lichthi> list;
    private float totalMoney = 0;
    MainActivity mainActivity;
    int dem;
    public Adapter(ArrayList<Lichthi> list, MainActivity mainActivity) {
        this.list = list;
        this.mainActivity=mainActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lichthi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lichthi lichthi = list.get(position);
        if (lichthi == null){
            return;
        } else {
            holder.tvID.setText("ID: " + lichthi.getId());
            holder.tvName.setText("ngay thi: " + lichthi.getTenmonhoc());
            holder.tvNgaythi.setText("gio Thi: " + lichthi.getNgaythi());
            holder.tvGiothi.setText("Ten mon Thi: " + lichthi.getGiothi());
            holder.tvKieuthi.setText("Kieu Thi: " + lichthi.getKieuthi());
            if (list.get(position).getKieuthi().equals("Thi viet")){
                dem++;
            }

        }
        mainActivity.tongsomon=dem;
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvID, tvName, tvNgaythi,tvGiothi, tvKieuthi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.id);
            tvName = itemView.findViewById(R.id.tenmon);
            tvNgaythi = itemView.findViewById(R.id.ngaythi);
            tvGiothi = itemView.findViewById(R.id.giothi);
            tvKieuthi = itemView.findViewById(R.id.kieuthi);
        }
    }
}
