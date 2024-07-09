package project.domain

data class Show(
    val id: Int,
    val title: String,
    val description: String,
    val releaseDate: Int,
    val duration: Long,
    val rating: Int,
    val seasons: List<Season> = emptyList()
)