package az.ada.library;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import az.ada.library.Models.Book;
import az.ada.library.Models.Category;
import az.ada.library.Models.Course;
import az.ada.library.Repositories.BookRepository;
import az.ada.library.Repositories.CategoryRepository;
import az.ada.library.Repositories.CourseRepository;
import az.ada.library.Services.AuthService;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	BookRepository bookRepo;
	@Autowired
	AuthService authService;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);

	}

	// Run method runs after spring boot application starts
	// Here, we create some entities and save to the database if tablesa are empty
	@Override
	public void run(String... args) throws Exception {

		// register default user
		authService.register("nuray@gmail.com", "Nuray Leysanli", "123456", "123456");

		if (courseRepo.count() == 0) {
			courseRepo.saveAll(Arrays.asList(new Course("Math"), new Course("Pyhsic"), new Course("History"),
					new Course("English")));
		}

		if (categoryRepo.count() == 0 && bookRepo.count() == 0) {
			Category scifi = new Category("Sci-Fi");
			Category economy = new Category("Economy");
			Category history = new Category("History");
			Category stem = new Category("STEM");
			categoryRepo.saveAll(Arrays.asList(scifi, economy, history, stem));

			Book b1 = new Book("Nineteen Eighty-Four", "George Orwell", this.getDate(1949, 1, 1));
			Book b2 = new Book("The Time Machine", "H. G. Wells", this.getDate(1895, 1, 1));
			Book b11 = new Book("Animal Farm", "George Orwell", this.getDate(1945, 8, 7));
			Book b12 = new Book("Burmese Days", "George Orwell", this.getDate(1934, 10, 1));
			Book b13 = new Book("Animal Farm - Down and Out in Paris and London", "George Orwell", this.getDate(1933, 1, 8));
			b1.setCategories(Arrays.asList(scifi));
			b2.setCategories(Arrays.asList(scifi));
			b11.setCategories(Arrays.asList(scifi));
			b12.setCategories(Arrays.asList(scifi, history));
			b13.setCategories(Arrays.asList(scifi));

			Book b3 = new Book("Why Nations Fail", "James A. Robinson", this.getDate(2012, 1, 1));
			Book b4 = new Book("The Wealth of Nations", "Adam Smith", this.getDate(1776, 3, 9));
			Book b5 = new Book("Debt: The First 5000 Years", "David Graeber", this.getDate(2011, 7, 12));
			b3.setCategories(Arrays.asList(economy));
			b4.setCategories(Arrays.asList(economy));
			b5.setCategories(Arrays.asList(economy, history));

			Book b6 = new Book("Rosie Revere, Engineer", "Andrea Beaty", this.getDate(2013, 9, 3));
			Book b7 = new Book("From Here to There: The Story of How We Transport Ourselves", "HP Newquist",
					this.getDate(2017, 1, 1));
			Book b8 = new Book("Debt: The First 5000 Years", "David Graeber", this.getDate(2011, 7, 12));
			b6.setCategories(Arrays.asList(stem, history));
			b7.setCategories(Arrays.asList(stem, history, scifi));
			b8.setCategories(Arrays.asList(economy));

			bookRepo.saveAll(Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b11, b12, b13));
		}

	}

	// creates a date object and returns it
	public Date getDate(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, day);
		return c.getTime();
	}

}
