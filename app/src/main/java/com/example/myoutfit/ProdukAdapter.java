package com.example.myoutfit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ViewHolder> {
    private Context context;
    private List<ProdukModel> list;

    public ProdukAdapter(Context context, List<ProdukModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_produk, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_nama.setText(list.get(position).getMerk());
        holder.tv_harga.setText(list.get(position).getHarga());
        Picasso.get().load(list.get(position).getGambar()).into(holder.iv_gambar);

        holder.iv_gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailProdukActivity.class);
                intent.putExtra("gambar", list.get(position).getGambar());
                intent.putExtra("type", list.get(position).getType());
                intent.putExtra("kode_produk", list.get(position).getKode_produk());
                intent.putExtra("merk", list.get(position).getMerk());
                intent.putExtra("warna", list.get(position).getWarna());
                intent.putExtra("bahan", list.get(position).getBahan());
                intent.putExtra("ukuran", list.get(position).getUkuran());
                intent.putExtra("deskripsi", list.get(position).getDeskripsi());
                intent.putExtra("kategori", list.get(position).getKategori());
                intent.putExtra("harga", list.get(position).getHarga());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_gambar;
        TextView tv_nama;
        TextView tv_harga;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_gambar = itemView.findViewById(R.id.iv_gambar);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_harga = itemView.findViewById(R.id.tv_harga);
        }
    }
}
