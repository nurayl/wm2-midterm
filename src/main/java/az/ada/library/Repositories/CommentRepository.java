package az.ada.library.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import az.ada.library.Models.Comment;

public interface CommentRepository extends MongoRepository<Comment, String>
{

   List<Comment> findByBookId(Long bookId);
   // List<Reply> findByBookId
   // (String bookId);



   // List<Reply> getReplies(String bookId);
}
