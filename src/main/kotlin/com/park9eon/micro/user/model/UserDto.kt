package com.park9eon.micro.user.model

data class UserDto(var id: Long? = null,
                   val username: String? = null,
                   val password: String? = null) {
    constructor(userCommand: UserCommand) : this(userCommand.id, userCommand.username, userCommand.password)
}