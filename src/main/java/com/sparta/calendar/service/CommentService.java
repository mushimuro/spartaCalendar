package com.sparta.calendar.service;

import com.sparta.calendar.dto.CalendarRequestDto;
import com.sparta.calendar.dto.CommentRequestDto;
import com.sparta.calendar.entity.Calendar;
import com.sparta.calendar.entity.Comment;
import com.sparta.calendar.entity.User;
import com.sparta.calendar.repository.CalendarRepository;
import com.sparta.calendar.repository.CommentRepository;
import com.sparta.calendar.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
    private final CalendarRepository calendarRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

//    String commentContent, String username, Calendar calendar, User user
    // comment 생성
    public Comment createComment(CommentRequestDto dto){
        User user = userRepository.findByUsername(dto.getUsername()).orElseThrow(NullPointerException::new);
        Calendar calendar = calendarRepository.findByUsername(dto.getUsername()).orElseThrow(NullPointerException::new);
        Comment newComment = new Comment(dto.getCommentContent(), dto.getUsername(), calendar, user);

        calendar.getCommentList().add(newComment);
        user.getCommentList().add(newComment);

        return commentRepository.save(newComment);
    }

    // 댓글 조회
    public Comment getComment(Long id){
        return commentRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    // 댓글 수정
    public Comment updateComment(Long commentId, CommentRequestDto dto) {
        Comment comment = getComment(commentId);
        comment.setCommentContent(dto.getCommentContent());

        return commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId){
        Comment comment = getComment(commentId);

        commentRepository.deleteById(commentId);
    }
}
