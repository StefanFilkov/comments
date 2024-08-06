package com.tinqinacademy.comments.api.operations.putcomment;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;


@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class PutCommentOutput implements OperationOutput {
    private String id;
}
