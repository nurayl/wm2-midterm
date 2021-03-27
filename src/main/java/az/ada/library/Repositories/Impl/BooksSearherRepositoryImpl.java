package az.ada.library.Repositories.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import az.ada.library.Models.Book;
import az.ada.library.Repositories.BooksSearherRepository;

@Repository
public class BooksSearherRepositoryImpl implements BooksSearherRepository {

    @PersistenceContext
    private EntityManager em;

    // combine strings to run the correct sql query
    public List<Book> findByBookNameAndAuthorAndCategoryId(String name, String authorName, Long categoryId) {
        String queryString = "select distinct b.id, b.author, b.name, b.publish_date, b.picked_up_by_id from books b join books_categories bc on b.id=bc.book_id where 1=1 ";

        // use LOWER sql function for case-insensitive
        if (name != null) {
            queryString += "AND LOWER(b.name) LIKE LOWER(:name) ";
        }
        if (authorName != null) {
            queryString += "AND LOWER(b.author) LIKE LOWER(:authorName) ";
        }
        if (categoryId != null) {
            queryString += "AND bc.category_id = :categoryId ";
        }
        Query query = em.createNativeQuery(queryString, Book.class);

        if (name != null) {
            query.setParameter("name", "%" + name + "%");
        }
        if (authorName != null) {
            query.setParameter("authorName", "%" + authorName + "%");
        }
        if (categoryId != null) {
            query.setParameter("categoryId", categoryId);
        }

        // run query and get result
        List<Book> results = query.getResultList();

        return results;
    }

}