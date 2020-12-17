package com.shinley.dto;

import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class User {

    /**
     * 使用接口定义视图
     */
    public interface UserSimpleView {};
    public interface UserDetailView extends UserSimpleView {};

    private String id;

    private String username;

    @NotBlank
    private String password;

    private Date birthday;

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
