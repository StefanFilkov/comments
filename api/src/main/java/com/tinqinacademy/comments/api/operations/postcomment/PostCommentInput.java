package com.tinqinacademy.comments.api.operations.postcomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import lombok.*;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class PostCommentInput implements OperationInput {
    //TODO Validate
    @JsonIgnore
    private String roomId;
    private String firstName;
    private String lastName;
    private String content;
}
