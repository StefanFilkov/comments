package com.tinqinacademy.comments.api.operations.deletecomment;

import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class DeleteCommentInput implements OperationInput {

    @NotBlank
    @UUID
    private String commentId;
}
