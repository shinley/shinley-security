package com.shinley.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.shinley.dto.User;
import com.shinley.dto.UserQueryCondition;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
//    public Object getCurrentUser(Authentication authentication) {
//        return SecurityContextHolder.getContext().getAuthentication();
//        return authentication;
        return userDetails;
    }

    /**
     * JsonView 定义返回的视图
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @JsonView(User.UserSimpleView.class)
    public List<User> query(@RequestParam String username) {
        System.out.println("username:" + username);

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @RequestMapping(value = "/user2", method = RequestMethod.GET)
    public List<User> query2(UserQueryCondition condition) {
        ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE);
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @RequestMapping(value = "/user3", method = RequestMethod.GET)
    public List<User> query3(UserQueryCondition condition, @PageableDefault(page = 2, size = 17, sort = "username.asc") Pageable pageable) {
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));;
        System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));;
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    /**
     * 参数用正则校验
     * JsonView 定义返回的视图
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.GET)
    @JsonView(User.UserDetailView.class)
    public User getUserInfo(@PathVariable String id) {
        User user = new User();
        user.setUsername("tom");
        user.setPassword("xxx");
        return user;
    }

    /**
     * 添加用户
     */
    @PostMapping("/user")
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getId());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    /**
     * 修改用户
     */
    @PutMapping("/user/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError)error;
                String message = fieldError.getField() + " " + error.getDefaultMessage();
                System.out.println(message);
            });
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getId());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    /**
     * 修改用户
     */
    @DeleteMapping("/user/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }

}
