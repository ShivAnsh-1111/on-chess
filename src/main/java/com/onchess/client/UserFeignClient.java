package com.onchess.client;

import com.onchess.entity.User;

@FeignClient(name = "user-service")
public interface UserFeignClient {

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id);
}