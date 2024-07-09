package project.domain

data class Season(
    val id: Int,
    val number: Int,
    val rating: Int,
    val episodes: List<Episode> = emptyList()
)