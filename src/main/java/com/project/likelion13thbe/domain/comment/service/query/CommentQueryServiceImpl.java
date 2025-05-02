package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.converter.CommentConverter;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentQueryServiceImpl implements CommentQueryService {
    private final CommentRepository commentRepository;

    @Override
    public CommentResDTO.CommentListResDTO getCommentList(Long reviewId) {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentResDTO.CommentDetailResDTO> filteredCommentDetailResDTOList =
                commentList.stream()
                        .filter(comment -> comment.getReview().getReviewId().equals(reviewId))
                        .map(CommentConverter::toCommentDetailResDTO)
                        .toList();

        return CommentConverter.toCommentListResDTO(filteredCommentDetailResDTOList);
    }
}
