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
public class Tag implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String tagName;
    private String noteID;
}
