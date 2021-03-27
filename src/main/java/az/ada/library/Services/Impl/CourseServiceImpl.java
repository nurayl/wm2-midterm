package az.ada.library.Services.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.ada.library.Models.Course;
import az.ada.library.Models.User;
import az.ada.library.Repositories.CourseRepository;
import az.ada.library.Repositories.UserRepository;
import az.ada.library.Services.CourseService;
import az.ada.library.Services.dtos.CustomException;

@Service
public class CourseServiceImpl extends BaseService implements CourseService{
    @Autowired
    CourseRepository courseRepo;

    @Autowired
    UserRepository userRepo;

    Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);


    public void enrollToCourse(Long courseId) throws CustomException {
        Long userId = this.getCurrentUserId();

        // if course does not exist, return error
        Optional<Course> course = courseRepo.findById(courseId);
        if (!course.isPresent()) {
            throw new CustomException("There is no such course");
        }
        Course c = course.get();
        if (c.getEnrolledUsers() == null) {
            c.setEnrolledUsers(new HashSet<User>());
        }

        c.getEnrolledUsers().add(new User(userId));
        courseRepo.save(c);

        logger.info("User " + userId + " enrolled to the course " + c.getName());

    }

    // returns the courses that user is enrolled
    public List<Course> getEnrolledCourses() {
        Long userId = this.getCurrentUserId();

        // search courses that is enrolled by UserId
        List<Course> courses = courseRepo.findByEnrolledUsers_Id(userId);
        return courses;
    }

    @Override
    public List<Course> getAll() {
        return courseRepo.findAll();
    }

}
