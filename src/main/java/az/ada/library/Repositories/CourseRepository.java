package az.ada.library.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import az.ada.library.Models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // find courses which are enrolled by UserId
    public List<Course> findByEnrolledUsers_Id(Long id);
}
