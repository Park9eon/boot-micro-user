package com.park9eon.micro.user.model

import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UserCommand(
        var id: Long? = null,
        @Email
        @NotNull
        @Size(min = 6, max = 30)
        var username: String? = null,
        @NotNull
        @Size(min = 6, max = 30)
        var password: String? = null
)