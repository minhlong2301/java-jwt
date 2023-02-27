package vn.learning.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.learning.jwt.service.TestService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/test")
public class TestController {

    private final TestService testService;

    @GetMapping("/token")
    public String testToken() {
        return testService.testToken();
    }


}
