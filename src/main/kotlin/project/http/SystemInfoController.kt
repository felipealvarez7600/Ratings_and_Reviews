package project.http

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import project.http.model.Link
import project.http.model.System
import project.services.SystemInfoService

@RestController
class SystemInfoController (private val systemInfoService: SystemInfoService){
    @GetMapping(PathTemplate.SYSTEM)
    fun getSystemInfo() = System(
        systemInfo = systemInfoService.getSystemInfo(),
        links = Link(
            rel = "home",
            href = PathTemplate.HOME,
            type = "GET",
            title = "Home page"
        )
    )
}