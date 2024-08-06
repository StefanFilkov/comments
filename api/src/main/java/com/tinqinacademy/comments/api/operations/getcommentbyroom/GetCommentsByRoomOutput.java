package com.tinqinacademy.comments.api.operations.getcommentbyroom;

import com.tinqinacademy.comments.api.base.OperationOutput;
import com.tinqinacademy.comments.api.models.output.CommentOutput;
import lombok.*;

import java.util.List;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class GetCommentsByRoomOutput implements OperationOutput {
    List<CommentOutput> commentOutputs;

}
