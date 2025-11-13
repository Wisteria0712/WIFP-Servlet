package com.wisteria.domain.vo;

import com.wisteria.domain.Comment;
import com.wisteria.domain.User;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CommentVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer commentId;
    private Integer noteId;
    private String userName;
    private String commentTitle;
    private String commentContent;
    private String remoteIP;
    private String createTime;
    private User user;
}
