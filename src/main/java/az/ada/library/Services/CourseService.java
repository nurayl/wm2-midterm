package az.ada.library.Services;

import java.util.List;

import az.ada.library.Models.Course;
import az.ada.library.Services.dtos.CustomException;

public interface CourseService {
    public void enrollToCourse(Long courseId) throws CustomException;

    public List<Course> getAll();

    public List<Course> getEnrolledCourses();
}
