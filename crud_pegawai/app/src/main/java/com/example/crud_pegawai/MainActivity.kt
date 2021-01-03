package com.example.crud_pegawai

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var btnTambahPegawai: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDaftarPegawai = findViewById(R.id.rvDaftarPegawai)
        btnTambahPegawai = findViewById(R.id.btnTambahPegawai)

        MainActivity.setContext(this)
        loadDataPegawai()

        btnTambahPegawai.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val intent: Intent = Intent(this@MainActivity, TambahPegawaiActivity::class.java)
                startActivity(intent)
            }
        })
    }
    companion object{
        private lateinit var rvDaftarPegawai:RecyclerView
        private lateinit var pegawaiAdapter: PegawaiAdapter
        private lateinit var listPegawai: List<Pegawai>
        private lateinit var context: Context
        fun setContext(_context: Context) {
            context=_context
        }
        public fun loadDataPegawai(){
            if(ConnectivityUtil.isConnected(context)){
                RetrofitConfig().getService().getListPegawai().enqueue(object: Callback<ModelListPegawai>{
                    override fun onResponse(call: Call<ModelListPegawai>, response: Response<ModelListPegawai>) {
                        if (response.isSuccessful()) {
//                            Log.e("state","successful")
                            val dataResponse: ModelListPegawai? = response.body()
                            listPegawai=dataResponse!!.listPegawai
                            pegawaiAdapter = PegawaiAdapter(listPegawai, context)
                            rvDaftarPegawai.layoutManager = LinearLayoutManager(context)
                            rvDaftarPegawai.itemAnimator = DefaultItemAnimator()
                            rvDaftarPegawai.adapter = pegawaiAdapter
                            pegawaiAdapter.notifyDataSetChanged()
                        }
                        else {
                            Log.e("response_code", response.code().toString())
                        }
                    }

                    override fun onFailure(call: Call<ModelListPegawai>, t: Throwable) {
                        Toast.makeText(context, "Request error", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            else{
                Toast.makeText(context, "Butuh koneksi internet", Toast.LENGTH_SHORT).show()
            }
        }
    }

}