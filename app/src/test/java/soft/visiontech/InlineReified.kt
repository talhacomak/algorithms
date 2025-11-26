package soft.visiontech

import org.junit.Test

class InlineReified {

    @Test
    fun test_inline_reified() {
        SerializerRegistry.register(Int::class.java, IntSerializer)
        SerializerRegistry.register(String::class.java, StringSerializer)
        SerializerRegistry.register(Boolean::class.java, BooleanSerializer)
    }

    inline fun <reified T> serializeValue(value: T): String? {
        val serializer = SerializerRegistry.get(T::class.java)
        return serializer?.serialize(value)
    }

    fun runBlock(block: () -> Unit) {
        block()
    }

    inline fun runInline(block: () -> Unit) {
        block()
    }

    fun test() {
        runBlock {
            println("Inside lambda")
            // return   // ❌ HATA
        }
        runInline {
            println("Inside inline lambda")
            return
        }
        println("After runBlock")
    }


    interface Serializer<T> {
        fun serialize(value: T): String
    }

    object IntSerializer : Serializer<Int> {
        override fun serialize(value: Int) = "Int:$value"
    }

    object StringSerializer : Serializer<String> {
        override fun serialize(value: String) = "Str:$value"
    }

    object BooleanSerializer : Serializer<Boolean> {
        override fun serialize(value: Boolean) = "Bool:$value"
    }

    object SerializerRegistry {
        private val serializers = mutableMapOf<Class<*>, Serializer<*>>()

        fun <T> register(type: Class<T>, serializer: Serializer<T>) {
            serializers[type] = serializer
        }

        @Suppress("UNCHECKED_CAST")
        fun <T> get(type: Class<T>): Serializer<T>? {
            return serializers[type] as? Serializer<T>
        }
    }

}