package project.http.model

import project.domain.devInfo.SystemInfo

data class System (
    val systemInfo : SystemInfo,
    val links : Link
)