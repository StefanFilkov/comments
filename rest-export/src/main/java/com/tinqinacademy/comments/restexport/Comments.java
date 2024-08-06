package com.tinqinacademy.comments.restexport;


import com.tinqinacademy.comments.api.URLMappings;
import com.tinqinacademy.comments.api.operations.postcomment.PostCommentInput;
import com.tinqinacademy.comments.api.operations.putcomment.PutCommentInput;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@FeignClient(name = "comment", url = "http://localhost:8081/comments", configuration = CommentsConfiguration.class)
public interface Comments {

    @GetMapping(value = URLMappings.GET_COMMENTS_BY_ROOM_ID)//Moje da se naloji da dobawq consumes i produces
    ResponseEntity<?> getCommentsByRoom(@PathVariable String roomId);

    @PostMapping(URLMappings.POST_COMMENT)
    ResponseEntity<?> postComment(@PathVariable String roomId, @RequestBody PostCommentInput input);

    @PatchMapping(URLMappings.PATCH_COMMENT)
    ResponseEntity<?> updateComment(@PathVariable String commentId);

    @PutMapping(URLMappings.PUT_COMMENT)
    ResponseEntity<?> putComment(@RequestBody @Valid PutCommentInput input, @PathVariable String commentId);

    @DeleteMapping(URLMappings.DELETE_COMMENT)
    ResponseEntity<?> deleteComment(@PathVariable String commentId);
}
