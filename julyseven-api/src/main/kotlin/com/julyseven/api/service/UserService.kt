package com.julyseven.api.service

import com.julyseven.common.entity.User

import java.util.*

interface UserService {

    fun getUser(userId: String): Optional<User>

    fun createUser(user: User): User

    fun updateUser(user: User): User

    fun deleteUser(userId: String)
}