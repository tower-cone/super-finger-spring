package towercone.superfingerspring;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.jooq.DSLContext;
import org.jooq.JSONFormat;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import towercone.superfingerspring.tables.Sections;

import java.sql.Connection;
import java.sql.DriverManager;

@Controller
@SpringBootApplication
public class SuperFingerSpringApplication {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

	@RequestMapping("/sections")
	@ResponseBody
	String sections() {
		return create.select().from(Sections.SECTIONS).fetch().formatJSON(jsonFormat);
	}

	private static JSONFormat jsonFormat = new JSONFormat()
			.header(false)
			.recordFormat(JSONFormat.RecordFormat.OBJECT);

	private static Connection conn;
	private static DSLContext create;

	public static void main(String[] args) {
		SpringApplication.run(SuperFingerSpringApplication.class, args);

		String url = "jdbc:postgresql://ec2-54-235-68-3.compute-1.amazonaws.com:5432/d4vsm153jo7vn8?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		String userName = "juhwudhcgihmfg";
		String password = "7d9f99b82a7cf10dc88bccad8f4511b61df9a206e8ff72e2ebc6974c7d260f6b";

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, userName, password);
			create = DSL.using(conn, SQLDialect.POSTGRES);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

