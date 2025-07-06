package com.linklio.linklio.application.ports.out.userPorts;
import com.linklio.linklio.domain.model.User;


public interface SaveUserPort {
    User save(User user);
}
