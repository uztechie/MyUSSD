package uz.ibroximtechie.myussd.data.db

import com.example.ussd.domain.model.Category
import com.example.ussd.domain.model.Internet
import com.example.ussd.domain.model.Minute
import com.example.ussd.domain.model.Tarif
import kotlinx.coroutines.flow.Flow
import uz.ibroximtechie.myussd.domain.model.Company
import uz.ibroximtechie.myussd.domain.model.USSDCode

interface UssdRepository {
    fun getCompany(id:Int):Flow<Company?>
    fun getCodes(companyId:Int):Flow<List<USSDCode>>
    fun getCompanies():List<Company>
    fun getCategories(companyId: Int, typeId:Int):Flow<List<Category>>
    fun getTarif(catId:Int):Flow<List<Tarif>>
    fun getInternet(catId:Int):Flow<List<Internet>>
    fun getMinutes(catId:Int):Flow<List<Minute>>
}


class UssdRepositoryImpl(val dao: UssdDao):UssdRepository{
    override fun getCompany(id: Int): Flow<Company?> {
        return dao.getCompany(id)
    }

    override fun getCodes(companyId: Int): Flow<List<USSDCode>> {
        return dao.getCodes(companyId)
    }

    override fun getCompanies(): List<Company> {
        return dao.getCompanies()
    }

    override fun getCategories(companyId: Int, typeId: Int): Flow<List<Category>> {
        return dao.getCategories(companyId, typeId)
    }

    override fun getTarif(catId: Int): Flow<List<Tarif>> {
        return dao.getTarif(catId)
    }

    override fun getInternet(catId: Int): Flow<List<Internet>> {
        return dao.getInternet(catId)
    }

    override fun getMinutes(catId: Int): Flow<List<Minute>> {
        return dao.getMinutes(catId)
    }

}


