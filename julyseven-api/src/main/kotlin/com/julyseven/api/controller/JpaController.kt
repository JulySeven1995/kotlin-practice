package com.julyseven.api.controller

import com.julyseven.api.service.ObjectConvertService
import com.julyseven.api.service.UserService
import com.julyseven.common.entity.User
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
class JpaController(
    private val userService: UserService,
    private val objectConvertService: ObjectConvertService
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)


    @CrossOrigin("*")
    @RequestMapping(method = [RequestMethod.GET], path = ["/getUserInfo"], params = ["userId"])
    @ResponseBody
    fun getUserInfo(@RequestParam userId: String): Map<String, Any> {

        logger.info("[GET] User Information Request, UserId = [{}]", userId)

        return userService.getUser(userId)
            .map(objectConvertService::convertToMap)
            .orElseThrow {
                logger.error("[GET] User Information Request Failed, UserId = [{}]", userId)
                ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
            }
    }

    @CrossOrigin("*")
    @RequestMapping(method = [RequestMethod.POST], path = ["/createUser"], params = ["userId", "userName", "password"])
    @ResponseBody
    fun createUser(@RequestParam userId: String, @RequestParam userName: String, @RequestParam password: String): Map<String, Any> {

        logger.info("[POST] User Create Request, UserId = [{}]", userId);

        val user = User(userId, userName, password)

        try {
            userService.createUser(user)
        } catch(e : Exception) {
            logger.error("[POST] User Create Request Failed, UserId = [{}]", userId);
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Create User Failed, -> " + e.message);
        }

        return objectConvertService.convertToMap(user)
    }

    @CrossOrigin("*")
    @RequestMapping(method = [RequestMethod.PUT], path = ["/updateUser"], params = ["userId"])
    @ResponseBody
    fun updateUser(@RequestParam userId: String, @RequestParam userName: String, @RequestParam password: String): Map<String, Any> {

        logger.info("[PUT] User Update Request, UserId = [{}]", userId);

        val user = User(userId, userName, password)

        try {
            userService.updateUser(user)
        } catch(e : Exception) {
            logger.error("[PUT] User Update Request Failed, UserId = [{}]", userId);
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Update User Failed, -> " + e.message);
        }

        return objectConvertService.convertToMap(user)
    }

    @CrossOrigin("*")
    @RequestMapping(method = [RequestMethod.DELETE], path = ["/deleteUser"], params = ["userId"])
    fun deleteUser(@RequestParam userId: String): ResponseEntity<Any> {

        logger.info("[DELETE] Delete User Request, UserId = [{}]", userId);

        try {
            userService.deleteUser(userId)
            return ResponseEntity("Delete User Succeed", HttpStatus.OK)
        } catch (e: Exception) {
            logger.error("[DELETE] User Update Request Failed, UserId = [{}]", userId, e);
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Delete User Failed, -> " + e.message);
        }
    }
}