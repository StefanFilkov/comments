package com.tinqinacademy.comments.api.operations.updatecomment;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UpdateCommentOutput implements OperationOutput {
    private String id;
}
