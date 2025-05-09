    package com.dellas0095.assesmobpro2.model

    import androidx.room.Entity
    import androidx.room.PrimaryKey

    @Entity(tableName = "pelanggan")
    data class Pelanggan(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0L,
        val nama: String,
        val jumlah: String,
        val variant: String,
        val isDeleted: Boolean = false
    )
