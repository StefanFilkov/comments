package com.tinqinacademy.comments.api.operations.updatecomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import lombok.*;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UpdateCommentInput implements OperationInput {
    @JsonIgnore
    private String id;
    private String content;
}

