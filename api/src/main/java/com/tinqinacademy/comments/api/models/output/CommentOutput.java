package com.tinqinacademy.comments.api.models.output;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CommentOutput {
    private UUID id;
    private String content;
    private String authorFirstName;
    private String authorLastName;
    private LocalDate createdOn;
    private LocalDate updatedOn;
    private String lastEditedBy;

}
