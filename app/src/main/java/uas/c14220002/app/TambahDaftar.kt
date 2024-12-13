package uas.c14220002.app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.Dispatchers
import uas.c14220002.app.database.historyKesehatan
import uas.c14220002.app.database.historyKesehatanDB
import uas.c14220002.app.helper.DateHelper.getCurrentDate

class TambahDaftar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_daftar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var DB = historyKesehatanDB.getDatabase(this)
        var _etBerat = findViewById<EditText>(R.id.etBerat)
        var _etDarah = findViewById<EditText>(R.id.etDarah)
        var _etCatatan = findViewById<EditText>(R.id.etCatatan)
        var _btnTambah = findViewById<Button>(R.id.btnTambah)
        var tanggal = getCurrentDate()

        _btnTambah.setOnClickListener {
            CoroutineScope(Dispatchers.IO).async {
                DB.funhistoryKesehatanDAO().insert(
                    historyKesehatan(
                        tanggal = tanggal,
                        beratBadan = _etBerat.text.toString(),
                        tekananDarah = _etDarah.text.toString(),
                        catatan = _etCatatan.text.toString()
                    )
                )
            }
        }
    }
}