package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Random;

@SpringBootApplication
public class DemoApplication {

	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://125.212.227.42:3336/preSchool_app";
		String user = "root";
		String pass = "toor";
		return DriverManager.getConnection(url, user, pass);
	}

	public static void main(String[] args) throws Exception {
		Connection conn = getConnection();
		Faker faker = new Faker();
		String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In auctor massa ut dolor volutpat aliquet eu a tellus. Donec nisl purus, laoreet vel laoreet ac, dignissim sed neque. Pellentesque erat sapien, laoreet id scelerisque in, ultricies ac nisi. Donec ornare, dolor eget commodo sagittis, arcu metus molestie ipsum, a semper eros dolor et quam. Duis placerat dictum libero in accumsan. Duis feugiat, urna porttitor convallis maximus, dui erat ultricies mauris, a efficitur augue est ac ipsum. Pellentesque nec posuere eros, non pretium erat. In in justo eget nibh maximus dapibus pharetra eget metus. Suspendisse molestie et ante sit amet hendrerit. Aenean mauris massa, aliquam nec molestie sed, suscipit a arcu. Curabitur ultrices nisi in nisl finibus sollicitudin. Aliquam nec consequat tellus. Sed eu tortor pharetra, pharetra elit id, luctus erat. In quis ex at purus congue convallis. Suspendisse luctus nisi magna, convallis eleifend ipsum lacinia eget. Aliquam lobortis maximus vestibulum.";

		Random rand = new Random();
		for (int i = 0; i < 10; i++){
			int randomNum = rand.nextInt(100);
//			moment
			String query = "insert into moments(content, img, status_accept,likes,  author_id, createdAt) values (?, ?, ?,?, ?, current_timestamp)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, lorem.substring(randomNum, randomNum+200));
			preparedStmt.setInt(2, 1);
			preparedStmt.setInt(3, 1);
			preparedStmt.setInt(4, 1);
			preparedStmt.setInt(5, i);
			preparedStmt.execute();

//			activity
			Date date1 = new Date(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2019/10/09 00:00:00").getTime());
			Date date2 = new Date(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2019/10/10 00:00:00").getTime());
			Date date3 = new Date(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2019/10/11 00:00:00").getTime());
			Date date4 = new Date(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2019/10/12 00:00:00").getTime());
			String query1 = "insert into activities_management(title, acti_time_from, acti_time_to, regis_time_from, regis_time_to, notice_class,content, regis_status, img, createdAt) " +
					" values(?,?,?,?,?,?,?,?,?,current_timestamp)";
			PreparedStatement preparedStmt1 = conn.prepareStatement(query1);
			preparedStmt1.setString(1, faker.gameOfThrones().character());
			preparedStmt1.setDate(2, date1);
			preparedStmt1.setDate(3, date2);
			preparedStmt1.setDate(4, date3);
			preparedStmt1.setDate(5, date4);
			preparedStmt1.setString(6, "[1,2,3]");
			preparedStmt1.setString (7, lorem.substring(randomNum, randomNum+500));
			preparedStmt1.setInt(8, 1);
			preparedStmt1.setInt(9, 1);

			preparedStmt1.execute();
//
////			meal
			String query2 = "insert into meal_types(breakfast, breakfast_sub, lunch, lunch_sub, date_meal, createdAt)" +
					" values(?,?,?,?,?,current_timestamp )";
			PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
			preparedStmt2.setString(1, faker.chuckNorris().fact());
			preparedStmt2.setString(2, faker.address().fullAddress());
			preparedStmt2.setString(3, faker.esports().team());
			preparedStmt2.setString(4, faker.leagueOfLegends().champion());
			preparedStmt2.setDate(5, date1);

			preparedStmt2.execute();

//			absense_ticket
			String query3 = "insert into absense_tickets(std_id, date, reason, status_accept, createdAt)" +
					"values(?,?,?,?,current_timestamp )";
			PreparedStatement preparedStmt3 = conn.prepareStatement(query3);
			preparedStmt3.setInt(1,1);
			preparedStmt3.setDate(2, date1);
			preparedStmt3.setString(3, faker.starTrek().location());
			preparedStmt3.setInt(4, 1);
			preparedStmt3.execute();

//			class
			String query4 = "insert into class(class_name, homeroom_teacher, number_student, createdAt)" +
					"values(?,?,?,current_timestamp )";
			PreparedStatement preparedStmt4 = conn.prepareStatement(query4);
			preparedStmt4.setString(1,faker.dragonBall().character());
			preparedStmt4.setInt(2, 1);
			preparedStmt4.setInt(3, randomNum);
			preparedStmt4.execute();

//			teacher
			int isDelete = rand.nextInt(2);
			String query5 = "insert into teachers( last_name, first_name, address, phone, email, avatar, isDelete, createdAt)" +
					"values(?,?,?,?,?,?,?,current_timestamp)";
			PreparedStatement preparedStmt5 = conn.prepareStatement(query5);
			preparedStmt5.setString(1, faker.name().lastName());
			preparedStmt5.setString(2, faker.name().firstName());
			preparedStmt5.setString(3, faker.address().fullAddress());
			preparedStmt5.setString(4, faker.phoneNumber().phoneNumber());
			preparedStmt5.setString(5, faker.name().username() + "@gmail.com");
			preparedStmt5.setString(6, faker.avatar().image());
			preparedStmt5.setInt(7, isDelete);
			preparedStmt5.execute();

//			student
			String query6 = "insert into students(last_name, first_name, gender, birthday, address, birthday, address, parent_id, grade, class_id, school_id, avatar, is_delete, createdAt)" +
					"values(?,?,?,?,?,?,?,?,?,?,?,?,current_timestamp )";
			PreparedStatement preparedStmt6 = conn.prepareStatement(query6);
			preparedStmt6.setString(1, faker.name().lastName());
			preparedStmt6.setString(2, faker.name().firstName());
			preparedStmt6.setInt(3, isDelete);
			preparedStmt6.setDate(4, new Date(faker.date().birthday(3,5).getTime()));
			preparedStmt6.setString(5, faker.address().fullAddress());
			preparedStmt6.setInt(6, faker.number().numberBetween(1, 20));
			preparedStmt6.setInt(7, faker.number().numberBetween(3,5));
			preparedStmt6.setInt(8, faker.number().numberBetween(1,10));
			preparedStmt6.setInt(9, faker.number().numberBetween(1,10));
			preparedStmt6.setString(10, faker.avatar().image());
			preparedStmt6.setInt(11, faker.number().numberBetween(0,1));
			preparedStmt6.execute();

//			user
			String query7 = "insert into users(username, password, role, createdAt)" +
					"values(?,?,?,current_timestamp )";
			PreparedStatement preparedStmt7 = conn.prepareStatement(query7);
			preparedStmt7.setString(1, faker.name().username());
			preparedStmt7.setString(2, "123");
			preparedStmt7.setInt(3, faker.number().numberBetween(10, 13));
			preparedStmt7.execute();

//			school
			String query8 = "insert into schools(school_name, school_year, phone, email, website, address, description, createdAt)" +
					"values(?,?,?,?,?,?,?,current_timestamp)";
			PreparedStatement preparedStmt8 = conn.prepareStatement(query8);
			preparedStmt8.setString(1, faker.name().fullName());
			preparedStmt8.setInt(2, faker.number().numberBetween(1950,2000));
			preparedStmt8.setString(3, faker.phoneNumber().phoneNumber());
			preparedStmt8.setString(4, faker.name().username()+"@edu.com.vn");
			preparedStmt8.setString(5, faker.name().username()+".edu.vn");
			preparedStmt8.setString(6, faker.address().fullAddress());
			preparedStmt8.setString(7, lorem.substring(randomNum, randomNum+100));
			preparedStmt8.execute();

//			parent
			String query9 = "insert into parents(last_name, first_name, phone, relationship, avatar, emergency_contact, is_delete, createdAt)" +
					"values(?,?,?,?,?,?,?,current_timestamp)";
			PreparedStatement preparedStmt9 = conn.prepareStatement(query9);
			preparedStmt9.setString(1, faker.name().lastName());
			preparedStmt9.setString(2, faker.name().firstName());
			preparedStmt9.setString(3, faker.phoneNumber().phoneNumber());
			preparedStmt9.setString(4, faker.relationships().parent());
			preparedStmt9.setString(5, faker.avatar().image());
			preparedStmt9.setString(6, faker.phoneNumber().cellPhone());
			preparedStmt9.setInt(7, faker.number().numberBetween(0,1));
			preparedStmt9.execute();

//			notification
			String query10 = "insert into notifications(title, author,content, img, notice_class, createdAt)" +
					"values(?,?,?,?,?,current_timestamp)";
			PreparedStatement preparedStmt10 = conn.prepareStatement(query10);
			preparedStmt10.setString(1, faker.name().title());
			preparedStmt10.setInt(2, faker.number().numberBetween(1,10));
			preparedStmt10.setString(3, lorem.substring(randomNum, randomNum+300));
			preparedStmt10.setInt(4, faker.number().numberBetween(1,20));
			preparedStmt10.setString(5, "[1,2,3]");
			preparedStmt10.execute();
		}

		conn.close();
	}

}
