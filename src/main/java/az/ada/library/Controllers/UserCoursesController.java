package az.ada.library.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import az.ada.library.Controllers.dtos.CoursesGetResponse;
import az.ada.library.Models.Course;
import az.ada.library.Services.CourseService;

@RestController
public class UserCoursesController {

    @Autowired
    CourseService courseService;

    @GetMapping({ "/user/courses" })
    public CoursesGetResponse getCoursesOfUser() {
        List<Course> courses = courseService.getEnrolledCourses();
        CoursesGetResponse response = new CoursesGetResponse(courses);
        return response;
    }
}
