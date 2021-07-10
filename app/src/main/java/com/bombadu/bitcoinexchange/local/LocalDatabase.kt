package com.bombadu.bitcoinexchange.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExchangeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExchanges(vararg exchangeEntity: ExchangeEntity)

    @Query("SELECT * FROM exchange_table")
    fun getAllExchanges(): LiveData<List<ExchangeEntity>>
}


@Database(
    entities = [ExchangeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract val exchangeDao: ExchangeDao
}

private lateinit var INSTANCE: LocalDatabase

fun getDatabase(context: Context): LocalDatabase {
    synchronized(LocalDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                LocalDatabase::class.java,
                "database"
            ).build()
        }
    }
    return INSTANCE
}