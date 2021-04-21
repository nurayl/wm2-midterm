package az.ada.library.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import az.ada.library.Models.Reply;

public interface ReplyRepository extends MongoRepository<Reply, String> {
   // List<Reply> findReplyByComment(Comment comment1);
   List<Reply> findByCommentId(String commentID);
}
