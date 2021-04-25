package com.julyseven.api.controller

import com.julyseven.api.service.UserService
import com.julyseven.common.entity.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner::class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class JpaControllerTest {

    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var userService: UserService

    @Before
    fun setUp() {

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @Test
    fun createUserTest() {

        val result = mvc.perform(MockMvcRequestBuilders.post("/createUser")
            .param("userId", "Jaeho")
            .param("userName", "Julius")
            .param("password", "1234")
            .accept(MediaType.APPLICATION_JSON))
            .andReturn()

        val status = result.response.status
        Assert.assertEquals(HttpStatus.OK.value(), status)
    }

    @Test
    fun searchUserTest() {

        userService.createUser(User("JhChoi", "Julius", "1234"))

        val result = mvc.perform(MockMvcRequestBuilders.get("/getUserInfo")
            .param("userId", "JhChoi")
            .accept(MediaType.APPLICATION_JSON))
            .andReturn();

        val status = result.response.status
        Assert.assertEquals(HttpStatus.OK.value(), status)
    }

    @Test
    fun updateUserTest() {

        userService.createUser(User("최재호", "Julius", "1234"));

        val result = mvc.perform(MockMvcRequestBuilders.put("/updateUser")
            .param("userId", "최재호")
            .param("userName", "멋쟁이")
            .param("password", "3145")
            .accept(MediaType.APPLICATION_JSON))
            .andReturn();

        val status = result.response.status;
        Assert.assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    fun deleteUserTest() {

        userService.createUser(User("SexyGuy", "Jaeho", "1234"));
        val result = mvc.perform(MockMvcRequestBuilders.delete("/deleteUser")
            .param("userId", "SexyGuy")
            .accept(MediaType.APPLICATION_JSON))
            .andReturn()

        val status = result.response.status
        Assert.assertEquals(HttpStatus.OK.value(), status)
    }


}