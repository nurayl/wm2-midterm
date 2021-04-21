package az.ada.library.Services;

import az.ada.library.Controllers.dtos.CommentCreateRequest;
import az.ada.library.Models.Comment;
import az.ada.library.Models.Reply;


public interface CommentService {
    Comment addComment(CommentCreateRequest commentRequest, Long bookId);

    public Reply addReply(CommentCreateRequest replyRequest, String commentId);
}
