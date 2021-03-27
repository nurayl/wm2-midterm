package az.ada.library.Controllers.dtos;

import java.util.LinkedList;
import java.util.List;

import az.ada.library.Models.Course;
import lombok.Data;

@Data
public class CoursesGetResponse {
    private List<CourseDto> courses;

    public CoursesGetResponse(List<Course> courses) {
        this.courses = new LinkedList<CourseDto>();
        for (Course course : courses) {
            this.courses.add(new CourseDto(course.getId(), course.getName()));
        }
    }
}

@Data
class CourseDto {
    private Long id;
    private String name;

    public CourseDto() {}
    public CourseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
