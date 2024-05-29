package com.sparta.calendar.service;

import com.sparta.calendar.dto.CommentRequestDto;
import com.sparta.calendar.entity.Calendar;
import com.sparta.calendar.entity.Comment;
import com.sparta.calendar.repository.CalendarRepository;
import com.sparta.calendar.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
    private final CalendarRepository calendarRepository;
    private final CommentRepository commentRepository;

    // comment 생성
    public Comment createComment(CommentRequestDto dto){
        var newComment = dto.toEntity();
        Calendar calendar = calendarRepository.findById(dto.getUserId()).orElseThrow(NullPointerException::new);

        calendar.getCommentList().add(newComment);

//        List<Comment> commentList = calendar.getCommentList();
//        for(Comment comment : commentList){
//            System.out.println(calendar.getUserName());
//            System.out.println(comment.getCommentContent());
//        }

        return commentRepository.save(newComment);
    }
}
