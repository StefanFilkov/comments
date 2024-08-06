package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.api.URLMappings;
import com.tinqinacademy.comments.api.errors.Errors;
import com.tinqinacademy.comments.api.operations.getcommentbyroom.GetCommentsByRoomInput;
import com.tinqinacademy.comments.api.operations.getcommentbyroom.GetCommentsByRoomOutput;
import com.tinqinacademy.comments.api.operations.postcomment.PostCommentInput;
import com.tinqinacademy.comments.api.operations.postcomment.PostCommentOutput;
import com.tinqinacademy.comments.api.operations.updatecomment.UpdateCommentInput;
import com.tinqinacademy.comments.api.operations.updatecomment.UpdateCommentOutput;
import com.tinqinacademy.comments.core.processors.comment.GetCommentsByRoomOperationProcessor;
import com.tinqinacademy.comments.core.processors.comment.PostCommentOperationProcessor;
import com.tinqinacademy.comments.core.processors.comment.UpdateCommentOperationProcessor;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class HotelController extends BaseController {


    private final PostCommentOperationProcessor postCommentOperationProcessor;
    private final UpdateCommentOperationProcessor updateCommentOperationProcessor;
    private final GetCommentsByRoomOperationProcessor getCommentsByRoomOperationProcessor;

    public HotelController(PostCommentOperationProcessor postCommentOperationProcessor, UpdateCommentOperationProcessor updateCommentOperationProcessor, GetCommentsByRoomOperationProcessor getCommentsByRoomOperationProcessor) {
        super();
        this.postCommentOperationProcessor = postCommentOperationProcessor;
        this.updateCommentOperationProcessor = updateCommentOperationProcessor;
        this.getCommentsByRoomOperationProcessor = getCommentsByRoomOperationProcessor;
    }

    @GetMapping(URLMappings.GET_COMMENTS_BY_ROOM_ID)
    public ResponseEntity<?> getCommentsByRoom(@PathVariable String roomId) {
        GetCommentsByRoomInput input = GetCommentsByRoomInput.builder().id(roomId).build();
        Either<Errors, GetCommentsByRoomOutput> result = getCommentsByRoomOperationProcessor.process(input);
        return handleResult(result);
    }

    @PostMapping(URLMappings.POST_COMMENT)
    public ResponseEntity<?> postComment(@PathVariable String roomId, @RequestBody PostCommentInput input) {
        input.setRoomId(roomId);

        Either<Errors, PostCommentOutput> result = postCommentOperationProcessor.process(input);
        return handleResult(result);
    }

    @PatchMapping(URLMappings.PATCH_COMMENT)
    public ResponseEntity<?> updateComment(@PathVariable String commentId) {
        UpdateCommentInput input = UpdateCommentInput.builder().id(commentId).build();
        Either<Errors, UpdateCommentOutput> result = updateCommentOperationProcessor.process(input);
        return handleResult(result);
    }
}
