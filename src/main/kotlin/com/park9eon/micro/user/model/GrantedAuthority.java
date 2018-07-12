package com.park9eon.micro.user.model;

import java.io.Serializable;

public interface GrantedAuthority extends Serializable {
    String getAuthority();
}
