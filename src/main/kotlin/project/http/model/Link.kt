package project.http.model

data class Link(
    val rel: String,
    val href: String,
    val type: String? = null,
    val title: String? = null
)
