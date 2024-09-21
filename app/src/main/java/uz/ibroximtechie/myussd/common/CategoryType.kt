package uz.ibroximtechie.myussd.common

enum class CategoryType(val typeId:Int) {
    INTERNET(typeId = 1),
    MINUTE(typeId = 2),
    SMS(typeId = 3),
    SERVICE(typeId = 4),
    TARIF(typeId = 5),
}