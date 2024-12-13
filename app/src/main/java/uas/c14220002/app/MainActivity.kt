package uas.c14220002.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import uas.c14220002.app.database.historyKesehatan
import uas.c14220002.app.database.historyKesehatanDB

class MainActivity : AppCompatActivity() {
    private lateinit var DB : historyKesehatanDB
    private lateinit var adapterHistory: adapterHistory
    private var arHistory : MutableList<historyKesehatan> = mutableListOf()
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapterHistory = adapterHistory(arHistory)

        DB = historyKesehatanDB.getDatabase(this)
        var _rvNotes = findViewById<RecyclerView>(R.id.rvNotes)
        var _fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        val _btnDownload = findViewById<FloatingActionButton>(R.id.fabDownload)
        val _btnUpload = findViewById<FloatingActionButton>(R.id.fabUpload)

        _rvNotes.layoutManager = LinearLayoutManager(this)
        _rvNotes.adapter = adapterHistory

        _fabAdd.setOnClickListener {
            startActivity(Intent(this, TambahDaftar::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.Main).async {
            val historyKesehatan = DB.funhistoryKesehatanDAO().selectAll()
            adapterHistory.isiData(historyKesehatan)
            Log.d("data Room", historyKesehatan.toString())
        }
    }
}