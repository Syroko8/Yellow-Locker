package com.example.nicolaspuebla_proyecto_final.model.dto;

import com.example.nicolaspuebla_proyecto_final.model.dataModels.MobileUser;

public class MemberListElement {
    private String rolType;
    private MobileUser user;

    public MemberListElement() {}

    public MemberListElement(String rolType, MobileUser user) {
        this.rolType = rolType;
        this.user = user;
    }

    public String getRolType() {
        return rolType;
    }

    public void setRolType(String rolType) {
        this.rolType = rolType;
    }

    public MobileUser getUser() {
        return user;
    }

    public void setUser(MobileUser user) {
        this.user = user;
    }
}
