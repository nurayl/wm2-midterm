package az.ada.library.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import az.ada.library.Controllers.dtos.CoursesGetResponse;
import az.ada.library.Models.Course;
import az.ada.library.Services.CourseService;
import az.ada.library.Services.dtos.CustomException;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    Logger logger = LoggerFactory.getLogger(CourseController.class);

    @PostMapping({ "/courses/{courseId}/enroll" })
    public ResponseEntity enroll(@PathVariable Long courseId) {
        try {
            courseService.enrollToCourse(courseId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (CustomException e) {
            String errMsg = "Server Error while trying to Enrolling to Course " + courseId + " : " + e.getMessage();
            logger.error(errMsg, e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping({ "/courses" })
    public CoursesGetResponse getAllCourses() {
        List<Course> courses = courseService.getAll();
        CoursesGetResponse response = new CoursesGetResponse(courses);
        return response;
    }
}
