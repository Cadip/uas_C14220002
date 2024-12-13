package uas.c14220002.app.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface historyKesehatanDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(historyKesehatan: historyKesehatan)

    @Query("UPDATE historyKesehatan SET tanggal=:isi_tanggal, beratBadan=:isi_beratBadan, tekananDarah=:isi_tekananDarah, catatan=:isi_catatan WHERE id=:id")
    fun update(id: Int, isi_tanggal: String, isi_beratBadan: String, isi_tekananDarah: String, isi_catatan: String)

    @Delete
    fun delete(history: historyKesehatan)

    @Query("SELECT * FROM historyKesehatan ORDER BY id asc")
    fun selectAll() : MutableList<historyKesehatan>
}