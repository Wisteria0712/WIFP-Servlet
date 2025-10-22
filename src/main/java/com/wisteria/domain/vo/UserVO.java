package com.wisteria.domain.vo;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String userName;
    private String nickname;
    private String password;
    private String confirmPassword;
    private String telephone;
    private String brief;
    private String checkCodeInput;
    private String generateCheckCode;

    public ArrayList<String> validateUserForm() {
        ArrayList<String> list = new ArrayList<>();
        if (password.isEmpty()) {
            list.add("密码不能为空!");
        } else if (!password.equals(confirmPassword)) {
            list.add("两次密码不一致!");
        }
        if (!checkCodeInput.equalsIgnoreCase(generateCheckCode)) {
            list.add("验证码错误!");
        }
        return list;
    }
}
