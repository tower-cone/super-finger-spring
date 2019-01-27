package towercone.superfingerspring;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import towercone.superfingerspring.tables.Sections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@Controller
@SpringBootApplication
public class SuperFingerSpringApplication {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(SuperFingerSpringApplication.class, args);

		String url = "jdbc:postgresql://ec2-54-235-68-3.compute-1.amazonaws.com:5432/d4vsm153jo7vn8?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		String userName = "juhwudhcgihmfg";
		String password = "7d9f99b82a7cf10dc88bccad8f4511b61df9a206e8ff72e2ebc6974c7d260f6b";

		try {
			//Class.forName("com.example.jdbc.Driver");
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(url, userName, password)) {
			DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
			Result<Record> result = create.select().from(Sections.SECTIONS).fetch();
			for (Record record : result) {
				String title = record.getValue(Sections.SECTIONS.TITLE);
				System.out.println(title);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

