package com.example.crud_pegawai

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PegawaiAdapter(private val listPegawai: List<Pegawai>, private val mContext: Context?) : RecyclerView.Adapter<PegawaiAdapter.PegawaiHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PegawaiHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pegawai, parent, false)
        return PegawaiHolder(itemView)
    }

    override fun onBindViewHolder(holder: PegawaiHolder, position: Int) {
        holder.txvNamaPegawai.setText(listPegawai.get(position).nama)
        holder.txvTanggalLahir.setText(listPegawai.get(position).tanggalLahir)
        holder.txvDivisi.setText(listPegawai.get(position).divisi)
        holder.btnEdit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent: Intent = Intent(mContext, EditPegawaiActivity::class.java)
                intent.putExtra("id", listPegawai.get(position).id)
                intent.putExtra("nama", listPegawai.get(position).nama)
                intent.putExtra("tanggal_lahir", listPegawai.get(position).tanggalLahir)
                intent.putExtra("divisi", listPegawai.get(position).divisi)
                mContext!!.startActivity(intent)
            }
        })
        holder.btnHapus.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val builder = AlertDialog.Builder(mContext!!)
                builder.setTitle("Konfirmasi Hapus Pegawai")
                builder.setMessage("Apakah anda yakin akan menghapus data pegawai ini?")
                builder.setPositiveButton("OK") { dialogInterface, i ->
                    val idPegawaiYgAkanDihapus: Int = listPegawai.get(position).id
                    hapusPegawai(idPegawaiYgAkanDihapus)
                }
                builder.setNegativeButton("Cancel") { dialogInterface, i -> }
                val dialogKonfirmasiHapusPegawai = builder.create()
                dialogKonfirmasiHapusPegawai.show()
            }
        })
    }

    override fun getItemCount(): Int {
        return listPegawai.size
    }

    fun hapusPegawai(idPegawai: Int){
        if(ConnectivityUtil.isConnected(mContext!!)){
            RetrofitConfig().getService().hapusPegawai(idPegawai).enqueue(object: Callback<ModelStatusResponse> {
                override fun onResponse(call: Call<ModelStatusResponse>, response: Response<ModelStatusResponse>) {
                    if(response.isSuccessful()){
                        val responseBody: ModelStatusResponse? = response.body()
                        val status: String = responseBody!!.status
                        if(status.equals("success")){
                            Toast.makeText(mContext, "Berhasil menghapus", Toast.LENGTH_SHORT).show()
                            MainActivity.loadDataPegawai()
                        }
                        else if(status.equals("error")){
                            Toast.makeText(mContext, "Gagal menghapus", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Log.e("response_code", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<ModelStatusResponse>, t: Throwable) {
                    Toast.makeText(mContext, "Request error", Toast.LENGTH_SHORT).show()
                }
            })
        }
        else{
            Toast.makeText(mContext, "Butuh koneksi internet", Toast.LENGTH_SHORT).show()
        }
    }

    inner class PegawaiHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txvNamaPegawai: TextView
        val txvTanggalLahir: TextView
        val txvDivisi: TextView
        val btnEdit: Button
        val btnHapus: Button

        init {
            txvNamaPegawai = itemView.findViewById(R.id.txvNamaPegawai)
            txvTanggalLahir = itemView.findViewById(R.id.txvTanggalLahir)
            txvDivisi = itemView.findViewById(R.id.txvDivisi)
            btnEdit = itemView.findViewById(R.id.btnEdit)
            btnHapus = itemView.findViewById(R.id.btnHapus)
        }
    }
}