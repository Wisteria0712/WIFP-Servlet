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
public class Note implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer noteId;
    private String author;
    private String noteTitle;
    private String noteContent;
    private Integer visit;
    private String categoryName;
    private String createTime;
    private String updateTime;
}
