package com.park9eon.micro.user.repository

import com.park9eon.micro.user.domain.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role, Long> {
    fun findByAuthority(authority: String): Role?
}
