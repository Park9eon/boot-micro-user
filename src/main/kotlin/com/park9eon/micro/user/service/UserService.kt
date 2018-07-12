package com.park9eon.micro.user.service

import com.park9eon.micro.user.domain.Role
import com.park9eon.micro.user.domain.User
import com.park9eon.micro.user.exception.UsernameNotFoundException
import com.park9eon.micro.user.model.UserDto
import com.park9eon.micro.user.repository.RoleRepository
import com.park9eon.micro.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
open class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var roleRepository: RoleRepository

    @Bean
    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    fun findById(id: Long): User? {
        return this.userRepository.getOne(id)
    }

    fun findByUsername(username: String): User {
        return this.userRepository.findByUsername(username) ?: throw UsernameNotFoundException()
    }

    fun findAll(offset: Int = 0, size: Int = 10): Page<User> {
        return this.userRepository.findAll(PageRequest.of(offset, size))
    }

    fun create(userDto: UserDto): User {
        val user = User()
        user.username = userDto.username
        user.password = this.bCryptPasswordEncoder().encode(userDto.password)
        user.roles = setOf(this.roleRepository.findByAuthority(Role.ROLE_USER))
        return this.userRepository.save(user)
    }

    fun update(userDto: UserDto): User {
        val user = userDto.username?.let { this.userRepository.findByUsername(it) }
                ?: throw UsernameNotFoundException()
        if (userDto.password != null) {
            user.password = this.bCryptPasswordEncoder().encode(userDto.password)
        }
        return this.userRepository.save(user)
    }

    fun delete(id: Long) {
        this.userRepository.deleteById(id)
    }
}
