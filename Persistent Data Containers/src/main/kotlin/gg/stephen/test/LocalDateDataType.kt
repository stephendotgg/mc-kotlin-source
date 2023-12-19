package gg.stephen.test

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import java.time.LocalDate

class LocalDateDataType : PersistentDataType<String, LocalDate> {

    override fun getPrimitiveType(): Class<String> {
        return String::class.java
    }

    override fun getComplexType(): Class<LocalDate> {
        return LocalDate::class.java
    }

    override fun fromPrimitive(primitive: String, context: PersistentDataAdapterContext): LocalDate {
        return LocalDate.parse(primitive)
    }

    override fun toPrimitive(complex: LocalDate, context: PersistentDataAdapterContext): String {
        return complex.toString()
    }

}