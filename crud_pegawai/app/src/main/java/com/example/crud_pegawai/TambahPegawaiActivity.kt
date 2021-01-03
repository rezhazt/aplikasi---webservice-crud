package com.example.crud_pegawai

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahPegawaiActivity : AppCompatActivity() {
    private lateinit var txtNamaPegawai: EditText
    private lateinit var txtTanggalLahir: EditText
    private lateinit var txtDivisi: EditText
    private lateinit var ivKalender: ImageView
    private lateinit var btnSimpan: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_pegawai)

        txtNamaPegawai = findViewById(R.id.txtNamaPegawai)
        txtTanggalLahir = findViewById(R.id.txtTanggalLahir)
        txtDivisi = findViewById(R.id.txtDivisi)
        ivKalender = findViewById(R.id.ivKalender)
        btnSimpan = findViewById(R.id.btnSimpan)

        ivKalender.setOnClickListener(object : View.OnClickListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onClick(v: View?) {
                val datePickerDialog = DatePickerDialog(this@TambahPegawaiActivity)
                datePickerDialog.setOnDateSetListener(object: DatePickerDialog.OnDateSetListener{
                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                        val paddedDayOfMonth = String.format("%2s", Integer.toString(dayOfMonth)).replace(' ', '0')
                        val paddedMonth = String.format("%2s", Integer.toString(month + 1)).replace(' ', '0')
                        txtTanggalLahir.setText(Integer.toString(year)+"-"+paddedMonth+"-"+paddedDayOfMonth)
                    }
                })
                datePickerDialog.datePicker.touchables[0].performClick()
                datePickerDialog.show()
            }
        })

        btnSimpan.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val namaPegawai: String = txtNamaPegawai.getText().toString()
                val tanggalLahir: String = txtTanggalLahir.getText().toString()
                val divisi: String = txtDivisi.getText().toString()
                if(!namaPegawai.isEmpty() && !tanggalLahir.isEmpty() && !divisi.isEmpty()){
                    if(ConnectivityUtil.isConnected(this@TambahPegawaiActivity)){
                        RetrofitConfig().getService().tambahPegawai(namaPegawai,tanggalLahir,divisi).enqueue(object: Callback<ModelStatusResponse>{
                            override fun onResponse(call: Call<ModelStatusResponse>, response: Response<ModelStatusResponse>) {
                                if(response.isSuccessful()){
                                    val responseBody: ModelStatusResponse? = response.body()
                                    val status: String = responseBody!!.status
                                    if(status.equals("success")){
                                        Toast.makeText(this@TambahPegawaiActivity, "Berhasil menyimpan", Toast.LENGTH_SHORT).show()
                                        MainActivity.loadDataPegawai()
                                        finish()
                                    }
                                    else if(status.equals("error")){
                                        Toast.makeText(this@TambahPegawaiActivity, "Gagal menyimpan", Toast.LENGTH_SHORT).show()
                                    }
                                }
                                else{
                                    Log.e("response_code", response.code().toString())
                                }
                            }

                            override fun onFailure(call: Call<ModelStatusResponse>, t: Throwable) {
                                Toast.makeText(this@TambahPegawaiActivity, "Request error", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
                    else{
                        Toast.makeText(this@TambahPegawaiActivity, "Butuh koneksi internet", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this@TambahPegawaiActivity, "Semua field harus terisi", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}