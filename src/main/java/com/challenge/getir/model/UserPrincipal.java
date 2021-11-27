package com.challenge.getir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal {
    private Integer id;
    private String username;
    private boolean isAdmin;
}