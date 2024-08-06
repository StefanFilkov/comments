package com.tinqinacademy.comments.api.operations.putcomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class PutCommentInput implements OperationInput {

    @JsonIgnore
    private String id;
    @Size(min=0,max = 12, message = "String invalid")
    private String roomId;
    private String firstName;
    private String lastName;
    private String content;
}
