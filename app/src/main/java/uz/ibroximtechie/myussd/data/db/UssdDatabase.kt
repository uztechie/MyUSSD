package uz.ibroximtechie.myussd.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ussd.domain.model.Category
import com.example.ussd.domain.model.Dealer
import com.example.ussd.domain.model.Internet
import com.example.ussd.domain.model.Minute
import com.example.ussd.domain.model.Service
import com.example.ussd.domain.model.Sms
import com.example.ussd.domain.model.Tarif
import uz.ibroximtechie.myussd.domain.model.Company
import uz.ibroximtechie.myussd.domain.model.USSDCode

@Database(
    entities = [
        Company::class,
        USSDCode::class,
        Tarif::class,
        Category::class,
        Internet::class,
        Minute::class,
        Sms::class,
        Service::class,
        Dealer::class
    ],
    version = 4,
    exportSchema = true
)
abstract class UssdDatabase:RoomDatabase() {
    abstract fun dao():UssdDao

    companion object{
        @Volatile
        private var INSTANCE : UssdDatabase? = null
        fun getInstance(context: Context):UssdDatabase{
            var tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UssdDatabase::class.java,
                    "newussd.db"
                )
//                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .addMigrations(MIGRATION_2_3)
                    .addMigrations(MIGRATION_3_4)
                    .createFromAsset("database/ussd.db")
                    .build()
                INSTANCE = instance
                return instance
            }


        }
    }

}