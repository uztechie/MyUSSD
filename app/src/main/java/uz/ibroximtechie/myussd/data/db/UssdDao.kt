package uz.ibroximtechie.myussd.data.db

import androidx.room.Dao
import androidx.room.Query
import com.example.ussd.domain.model.Category
import com.example.ussd.domain.model.Tarif
import kotlinx.coroutines.flow.Flow
import uz.ibroximtechie.myussd.domain.model.Company
import uz.ibroximtechie.myussd.domain.model.USSDCode

@Dao
interface UssdDao {
    @Query("select * from company where itemId=:id")
    fun getCompany(id:Int):Flow<Company?>

    @Query("select * from company")
    fun getCompanies():List<Company>

    @Query("select * from code where company_id=:companyId order by `order`")
    fun getCodes(companyId:Int):Flow<List<USSDCode>>

    @Query("select * from category where company_id=:companyId and type_id=:typeId")
    fun getCategories(companyId: Int, typeId:Int):Flow<List<Category>>

    @Query("select * from tarif where catid=:catId")
    fun getTarif(catId:Int):Flow<List<Tarif>>



}