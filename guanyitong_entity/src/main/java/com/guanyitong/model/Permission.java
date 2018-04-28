package com.guanyitong.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Permission implements Serializable{
    private Long id;
    private String permissionName;
    private String permissionUrl;
    private Integer type;
    private Integer status;
    private Long parentId;
    private Role role;//一个权限对应一个角色
    private Map<String,List<Permission>> map;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Map<String, List<Permission>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<Permission>> map) {
        this.map = map;
    }
}
