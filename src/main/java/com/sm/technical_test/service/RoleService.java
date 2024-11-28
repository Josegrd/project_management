package com.sm.technical_test.service;

import com.sm.technical_test.constant.ERole;
import com.sm.technical_test.entity.Role;


public interface RoleService {
    Role getOrSave(ERole role);
}
