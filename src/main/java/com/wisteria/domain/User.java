package com.wisteria.domain;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 373762079208650474L;
    private String userName;
    private String nickName;
    private String password;
    private String telephone;
    private String photo;
    private String isAuthor;
    private String brief;
    private String createTime;
}
