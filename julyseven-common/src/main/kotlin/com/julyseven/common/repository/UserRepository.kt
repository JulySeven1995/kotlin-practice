package com.julyseven.common.repository

import com.julyseven.common.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, String> {

    fun findByUserId(userId: String): Optional<User>

    fun findAllByUserName(userName : String): List<User>

}