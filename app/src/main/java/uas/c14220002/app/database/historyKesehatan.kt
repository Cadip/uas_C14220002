package uas.c14220002.app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class historyKesehatan(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id: Int = 0,

    @ColumnInfo(name = "Tanggal")
    var tanggal: String,

    @ColumnInfo(name = "BeratBadan")
    var beratBadan: String,

    @ColumnInfo(name = "TekananDarah")
    var tekananDarah: String,

    @ColumnInfo(name = "Catatan")
    var catatan: String
)
