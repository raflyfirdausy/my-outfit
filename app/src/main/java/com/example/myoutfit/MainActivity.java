package com.example.myoutfit;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private EditText editTextName;
    private EditText editTextKode;
    private EditText editTextMerk;
    private EditText editTextWarna;
    private EditText editTextBahan;
    private EditText editTextUkuran;
    private EditText editTextDesk;
    private EditText editTextKategori;
    private EditText editTextHarga;

    private Button buttonAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextKode = findViewById(R.id.editTextKode);
        editTextMerk = findViewById(R.id.editTextMerk);
        editTextWarna = findViewById(R.id.editTextWarna);
        editTextBahan = findViewById(R.id.editTextBahan);
        editTextUkuran = findViewById(R.id.editTextUkuran);
        editTextDesk = findViewById(R.id.editTextDesk);
        editTextKategori = findViewById(R.id.editTextKategori);
        editTextHarga = findViewById(R.id.editTextHarga);


        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProduct();
            }
        });


    }

    private void AddProduct() {

        final String name = editTextName.getText().toString().trim();
        final String kode = editTextKode.getText().toString().trim();
        final String merk = editTextMerk.getText().toString().trim();
        final String Warna = editTextWarna.getText().toString().trim();
        final String bahan = editTextBahan.getText().toString().trim();
        final String desk = editTextDesk.getText().toString().trim();
        final String Kategori = editTextKategori.getText().toString().trim();
        final String Harga = editTextHarga.getText().toString().trim();

        class AddProduct extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Menambahkan...",
                        "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NAMA, name);
                params.put(konfigurasi.KEY_EMP_KODE_PRODUK, kode);
                params.put(konfigurasi.KEY_EMP_MERK, merk);
                params.put(konfigurasi.KEY_EMP_WARNA, Warna);
                params.put(konfigurasi.KEY_EMP_BAHAN, bahan);
                params.put(konfigurasi.KEY_EMP_DESKRIPSI, desk);
                params.put(konfigurasi.KEY_EMP_KATEGORI, Kategori);
                params.put(konfigurasi.KEY_EMP_HARGA, Harga);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_GET_ALL, params);
                return res;
            }
        }

        AddProduct ap = new AddProduct();
        ap.execute();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
