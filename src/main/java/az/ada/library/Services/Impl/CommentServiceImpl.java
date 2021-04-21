package az.ada.library.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import az.ada.library.Controllers.dtos.CommentCreateRequest;
import az.ada.library.Models.Book;
import az.ada.library.Models.Comment;
import az.ada.library.Models.Reply;
import az.ada.library.Repositories.BookRepository;
import az.ada.library.Repositories.CommentRepository;
import az.ada.library.Repositories.ReplyRepository;
import az.ada.library.Services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    BookRepository bookRepository;


    @Override
    public Comment addComment(CommentCreateRequest commentRequest, Long bookId) {
        Book book=bookRepository.findById(bookId).orElseThrow(() -> new UsernameNotFoundException("not found"));;
        Comment comment=new Comment(commentRequest.getText());
        comment.setBookId(bookId);
        return commentRepository.save(comment);
        
    }
    
    @Override
    public Reply addReply(CommentCreateRequest replyRequest, String commentId) {
        
        Reply reply = new Reply();
        reply.setCommentId(commentId);
        reply.setText(replyRequest.getText());
        return replyRepository.save(reply);
    }


}
