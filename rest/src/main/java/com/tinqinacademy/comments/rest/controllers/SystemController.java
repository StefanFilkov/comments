package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.api.URLMappings;
import com.tinqinacademy.comments.api.errors.Errors;
import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentOperation;
import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.putcomment.PutCommentInput;
import com.tinqinacademy.comments.api.operations.putcomment.PutCommentOutput;
import com.tinqinacademy.comments.core.processors.comment.PutCommentOperationProcessor;
import io.vavr.control.Either;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SystemController extends BaseController {
    private final DeleteCommentOperation deleteCommentOperation;
    private final PutCommentOperationProcessor putCommentOperationProcessor;

    public SystemController(DeleteCommentOperation deleteCommentOperation, PutCommentOperationProcessor putCommentOperationProcessor) {
        this.deleteCommentOperation = deleteCommentOperation;
        this.putCommentOperationProcessor = putCommentOperationProcessor;
    }


    @PutMapping(URLMappings.PUT_COMMENT)
    public ResponseEntity<?> putComment(@RequestBody @Valid PutCommentInput input, @PathVariable String commentId){
        input.setId(commentId);

        Either<Errors, PutCommentOutput> result= putCommentOperationProcessor.process(input);
        return handleResult(result);

    }

    @DeleteMapping(URLMappings.DELETE_COMMENT)
    public ResponseEntity<?> deleteComment(@PathVariable String commentId){
        DeleteCommentInput input = DeleteCommentInput
                .builder()
                .commentId(commentId)
                .build();

        Either<Errors, DeleteCommentOutput> result = deleteCommentOperation.process(input);
        return handleResult(result);
    }
}
