package project.services

import org.springframework.stereotype.Service
import project.domain.devInfo.SystemInfo

@Service
class SystemInfoService {
    fun getSystemInfo(): SystemInfo {
        return SystemInfo(
            appName = "Ratings and Reviews",
            appVersion = "0.0.1",
            author = "Felipe Muñoz Alvarez",
            email = "felix7600@gmail.com",
            documentation = "https://github.com/felipealvarez7600/Ratings_and_Reviews/tree/main/docs_and_schemas"
        )
    }
}