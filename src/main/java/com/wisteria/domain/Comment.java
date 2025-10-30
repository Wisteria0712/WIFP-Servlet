package com.wisteria.domain;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Comment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer commentId;
    private Integer noteId;
    private String userName;
    private String commentTitle;
    private String commentContent;
    private String remoteIP;
    private String createTime;
}
