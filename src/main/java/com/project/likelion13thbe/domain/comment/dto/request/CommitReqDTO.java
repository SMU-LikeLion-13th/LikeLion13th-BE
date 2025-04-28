package com.project.likelion13thbe.domain.comment.dto.request;

import lombok.Builder;

public class CommitReqDTO {
    @Builder
    public record CreateCommitDTO(
            String content
    ) {
    }
}
