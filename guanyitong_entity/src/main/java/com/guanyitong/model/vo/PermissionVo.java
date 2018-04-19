package com.guanyitong.model.vo;

import com.guanyitong.model.Permission;

public class PermissionVo extends Permission {
    private String parentName;//父级菜单名称

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
