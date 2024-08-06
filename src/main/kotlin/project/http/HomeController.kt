package project.http

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import project.http.model.HomeOutputModel
import project.http.model.Link

@RestController
class HomeController {
    @GetMapping(PathTemplate.HOME)
    fun home() : ResponseEntity<*> {
        val createUserLink = Link(
            rel = "create-user",
            href = PathTemplate.Users.CREATE,
            type = "POST",
            title = "Create a new user"
        )
        val loginUserLink = Link(
            rel = "login",
            href = PathTemplate.Users.LOGIN,
            type = "POST",
            title = "Login"
        )
        val systemInfoLink = Link(
            rel = "system-info",
            href = PathTemplate.SYSTEM,
            type = "GET",
            title = "System information"
        )
        val links = listOf(createUserLink, loginUserLink, systemInfoLink)
        val homeModel = HomeOutputModel(
            title = "Home page",
            links = links
        )
        return ResponseEntity.ok(homeModel)
    }
}