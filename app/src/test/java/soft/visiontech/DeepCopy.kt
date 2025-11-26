package soft.visiontech

class DeepCopy {

    data class User(
        val name: String,
        val tags: MutableList<String>
    ) {
        fun deepCopy(): User {
            return User(
                name = this.name,
                tags = this.tags.toMutableList() // yeni liste
            )
        }
    }
}