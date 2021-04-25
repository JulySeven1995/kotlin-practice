package com.julyseven.api.service

import com.julyseven.common.entity.User
import com.julyseven.common.repository.UserRepository
import org.hibernate.HibernateException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(private val repository: UserRepository) : UserService {

    override fun getUser(userId: String): Optional<User> {

        return repository.findByUserId(userId)
    }

    override fun createUser(user: User): User {

        if (repository.existsById(user.userId)) {

            throw HibernateException(String.format("User Already Exists! userId : [{}]", user.userId))
        }

        return repository.saveAndFlush(user)
    }

    override fun updateUser(user: User): User {

        if (!repository.existsById(user.userId)) {

            throw HibernateException(String.format("User Does Not Exists! userId : [{}]", user.userId))
        }

        return repository.saveAndFlush(user)
    }

    override fun deleteUser(userId: String) {

        if (!repository.existsById(userId)) {

            throw HibernateException(String.format("User Does Not Exists! userId : [{}]", userId))
        }

        repository.deleteById(userId)
    }
}