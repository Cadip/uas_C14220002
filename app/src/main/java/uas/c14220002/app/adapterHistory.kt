package uas.c14220002.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uas.c14220002.app.database.historyKesehatan

class adapterHistory (private val historyKesehatan: MutableList<historyKesehatan>): RecyclerView.Adapter<adapterHistory.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): adapterHistory.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_recycler,parent,false
        )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: adapterHistory.ListViewHolder, position: Int) {
        var history = historyKesehatan[position]

        holder._tvTanggal.setText(history.tanggal)
        holder._tvBerat.setText(history.beratBadan)
        holder._tvTekanan.setText(history.tekananDarah)
        holder._tvCatatan.setText(history.catatan)
    }

    override fun getItemCount(): Int {
        return historyKesehatan.size
    }

    class ListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var _tvTanggal = itemView.findViewById<TextView>(R.id.tvTanggal)
        var _tvBerat = itemView.findViewById<TextView>(R.id.tvBerat)
        var _tvTekanan = itemView.findViewById<TextView>(R.id.tvTekanan)
        var _tvCatatan = itemView.findViewById<TextView>(R.id.tvCatatan)
    }

    fun isiData(history: List<historyKesehatan>) {
        historyKesehatan.clear()
        historyKesehatan.addAll(history)
        notifyDataSetChanged()
    }
}