package com.onchess.client;

import com.onchess.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.cloud.openfeign.FeignClient;

//@FeignClient(name = "user-service")
public interface UserFeignClient {

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id);
}
