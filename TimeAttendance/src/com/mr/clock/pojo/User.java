package com.mr.clock.pojo;
/**
 * @author orane
 * @create 2024/3/31 20:26
 * @Description 管理员类，与MySQL中的t_user对应
 **/

public class User {
    private String username;            //用户名
    private String password;            //密码

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
