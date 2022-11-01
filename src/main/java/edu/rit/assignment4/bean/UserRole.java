package edu.rit.assignment4.bean;

public class UserRole {
    private Long userId;
    private Long roleId;

    public UserRole(Long userId, Long roleId) {
        this.roleId = roleId;
        this.userId = userId;
    }
}
