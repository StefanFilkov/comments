package com.tinqinacademy.comments.core.processors.comment;

import com.tinqinacademy.comments.api.errors.*;
import com.tinqinacademy.comments.api.models.output.CommentOutput;
import com.tinqinacademy.comments.api.operations.getcommentbyroom.GetCommentsByRoomInput;
import com.tinqinacademy.comments.api.operations.getcommentbyroom.GetCommentsByRoomOperation;
import com.tinqinacademy.comments.api.operations.getcommentbyroom.GetCommentsByRoomOutput;
import com.tinqinacademy.comments.core.errormapper.ErrorMapper;
import com.tinqinacademy.comments.core.exceptions.CommentNotFoundException;
import com.tinqinacademy.comments.core.exceptions.NoCommentsFoundException;
import com.tinqinacademy.comments.persistence.entities.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

@Service
@Slf4j
public class GetCommentsByRoomOperationProcessor implements GetCommentsByRoomOperation {
    private final CommentRepository commentRepository;
    private final ErrorMapper errorMapper;
    private final ConversionService conversionService;

    public GetCommentsByRoomOperationProcessor(CommentRepository commentRepository, ErrorMapper errorMapper, ConversionService conversionService) {
        this.commentRepository = commentRepository;

        this.errorMapper = errorMapper;
        this.conversionService = conversionService;
    }

    @Override
    public Either<Errors, GetCommentsByRoomOutput> process(GetCommentsByRoomInput input) {
        log.info("Start getCommentByRoom input: {}", input);


        return Try.of(() -> {

                    log.info("Start getCommentByRoom input: {}", input);


                    List<Comment> comments = commentRepository.findAllByRoomId(UUID.fromString(input.getId()));
                    if (comments.isEmpty()) {
                        throw new NoCommentsFoundException(input.getId());
                    }

                    GetCommentsByRoomOutput result = createRoomOutput(comments);


                    log.info("End getCommentByRoom result: {}", result);
                    return result;
                })
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(instanceOf(CommentNotFoundException.class)), errorMapper::mapErrors),
                        Case($(instanceOf(NoCommentsFoundException.class)), errorMapper::mapErrors),
                        Case($(), errorMapper::mapErrors)
                        ));
    }

    private GetCommentsByRoomOutput createRoomOutput(List<Comment> comments) {
        return GetCommentsByRoomOutput
                .builder()
                .commentOutputs(comments
                        .stream()
                        .map(comment -> conversionService.convert(comment, CommentOutput.class))
                        .toList())
                .build();
    }


}


//    @Override
//    public Either<Errors, GetCommentsByRoomOutput> process(GetCommentsByRoomInput input) {
//        return Try.of(() -> {
//
//                    log.info("Start getCommentByRoom input: {}", input);
//
//
//                    List<Comment> comments = commentRepository.findAllByRoomId(UUID.fromString(input.getId()));
//                    //TODO converter
//                    GetCommentsByRoomOutput result = GetCommentsByRoomOutput.builder().build();
////                            comments.stream().map(comment -> GetCommentsByRoomOutput
////                            .builder()
////                            .authorFirstName(comment.getAuthorFirstName())
////                            .authorLastName(comment.getAuthorLastName())
////                            .content(comment.getContent())
////                            .createdOn(LocalDate.from(comment.getCreatedOn()))
////                            .lastEditedBy(comment.getLastEditedBy())
////                            .build());
//
//
//                    log.info("End getCommentByRoom result: {}", result);
//                    return result;
//                })
//                .toEither()
//                .mapLeft(throwable -> {
//                    log.error("", throwable);
//                    ErrorMappings.ExceptionToError.stream().map(extoer -> extoer.containsKey());
//                });
//
//    }
