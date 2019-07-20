package com.example.myoutfit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TampilProcdukActivity extends AppCompatActivity {
    RecyclerView rvProduk;
    Button btnRefresh;
    String URL_PRODUK = "https://projekpmo.000webhostapp.com/tampilProdukDetail.php";
    List<ProdukModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_procduk);

        rvProduk = findViewById(R.id.rvProduk);
        btnRefresh = findViewById(R.id.btnRefresh);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getProduk();
            }
        });

        getProduk();
    }

    private void getProduk() {
        RequestQueue requestQueue = Volley.newRequestQueue(TampilProcdukActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_PRODUK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            list.clear();
                            JSONObject object = new JSONObject(response);
                            if (object.getInt("status") == 1) {
                                JSONArray jsonArray = object.getJSONArray("result");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    ProdukModel produkModel = new ProdukModel();
                                    produkModel.setId_produk(jsonObject.getString("id_produk"));
                                    produkModel.setType(jsonObject.getString("type"));
                                    produkModel.setKode_produk(jsonObject.getString("kode_produk"));
                                    produkModel.setMerk(jsonObject.getString("merk"));
                                    produkModel.setWarna(jsonObject.getString("warna"));
                                    produkModel.setBahan(jsonObject.getString("bahan"));
                                    produkModel.setUkuran(jsonObject.getString("ukuran"));
                                    produkModel.setDeskripsi(jsonObject.getString("deskripsi"));
                                    produkModel.setKategori(jsonObject.getString("kategori"));
                                    produkModel.setHarga(jsonObject.getString("harga"));
                                    produkModel.setGambar(jsonObject.getString("gambar"));
                                    list.add(produkModel);
                                }
                                ProdukAdapter adapter = new ProdukAdapter(TampilProcdukActivity.this, list);
                                rvProduk.setLayoutManager(new GridLayoutManager(TampilProcdukActivity.this, 3));
                                rvProduk.setAdapter(adapter);
                            } else {
                                Toast.makeText(TampilProcdukActivity.this, "Belum ada produk", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TampilProcdukActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}