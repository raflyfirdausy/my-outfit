package com.example.myoutfit;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailProdukActivity extends AppCompatActivity {

    ImageView ivGambar;
    TextView tvKodeProduk;
    TextView tvMerk;
    TextView tvType;
    TextView tvHarga;
    TextView tvWarna;
    TextView tvBahan;
    TextView tvUkuran;
    TextView tvKategori;
    TextView tvDeskripsi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
        ivGambar = findViewById(R.id.ivGambar);
        tvKodeProduk = findViewById(R.id.tvKodeProduk);
        tvMerk = findViewById(R.id.tvMerk);
        tvType = findViewById(R.id.tvType);
        tvHarga = findViewById(R.id.tvHarga);
        tvWarna = findViewById(R.id.tvWarna);
        tvBahan = findViewById(R.id.tvBahan);
        tvUkuran = findViewById(R.id.tvUkuran);
        tvKategori = findViewById(R.id.tvKategori);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);

        Picasso.get().load(getIntent().getStringExtra("gambar")).into(ivGambar);
        tvKodeProduk.setText("Kode Produk : " + getIntent().getStringExtra("kode_produk"));
        tvMerk.setText("Merk : " + getIntent().getStringExtra("merk"));
        tvType.setText("Type : " + getIntent().getStringExtra("type"));
        tvHarga.setText("Harga : Rp " + getIntent().getStringExtra("harga"));
        tvWarna.setText("Warna : " + getIntent().getStringExtra("warna"));
        tvBahan.setText("Bahan : " + getIntent().getStringExtra("bahan"));
        tvUkuran.setText("Ukuran : " + getIntent().getStringExtra("ukuran"));
        tvKategori.setText("Kategori : " + getIntent().getStringExtra("kategori"));
        tvDeskripsi.setText("Deskripsi : " + getIntent().getStringExtra("deskripsi"));
    }
}
