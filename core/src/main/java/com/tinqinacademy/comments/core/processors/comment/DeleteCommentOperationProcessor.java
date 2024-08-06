package com.tinqinacademy.comments.core.processors.comment;


import com.tinqinacademy.comments.api.errors.Errors;
import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentOperation;
import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentOutput;
import com.tinqinacademy.comments.core.errormapper.ErrorMapper;
import com.tinqinacademy.comments.core.exceptions.CommentNotFoundException;
import com.tinqinacademy.comments.core.exceptions.NoCommentsFoundException;
import com.tinqinacademy.comments.core.processors.BaseOperationProcessor;
import com.tinqinacademy.comments.persistence.entities.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import io.vavr.Predicates;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;


import java.util.UUID;

import static io.vavr.API.*;


@Service
@Slf4j
public class DeleteCommentOperationProcessor extends BaseOperationProcessor implements DeleteCommentOperation {
    private final CommentRepository commentRepository;

    public DeleteCommentOperationProcessor(CommentRepository commentRepository, ConversionService conversionService, Validator validator, ErrorMapper errorMapper) {
        super(conversionService, validator, errorMapper);
        this.commentRepository = commentRepository;
    }


    @Override
    public Either<Errors, DeleteCommentOutput> process(DeleteCommentInput input) {
        log.info("Start updateComment id: {}", input);

        return Try.of(() -> {

                    Comment comment = commentRepository
                            .findById(UUID.fromString(input.getCommentId()))
                            .orElseThrow(() -> new CommentNotFoundException(input.getCommentId()));

                    commentRepository.delete(comment);

                    DeleteCommentOutput result = DeleteCommentOutput.builder().build();

                    log.info("End updateComment result: {}", result);
                    return result;

                })
                .toEither()
                .mapLeft(throwable -> Match(throwable).of(
                        Case($(Predicates.instanceOf(CommentNotFoundException.class)), errorMapper::mapErrors),
                        Case($(Predicates.instanceOf(NoCommentsFoundException.class)), errorMapper::mapErrors),
                        Case($(), errorMapper::mapErrors)
                ));


    }


}
