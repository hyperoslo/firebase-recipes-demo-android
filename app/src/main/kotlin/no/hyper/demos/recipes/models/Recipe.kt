package no.hyper.demos.recipes.models

data class Recipe(
        val name: String,
        var firebaseKey: String?
) {

    val payload: Map<String, Any>
        get() = mapOf("name" to this.name)

}
