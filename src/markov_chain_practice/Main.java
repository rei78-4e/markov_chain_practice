package markov_chain_practice;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try {
			// System.out.printf("Please input leaning data file path>");
			// var sc = new Scanner(System.in);
			// var input_path = sc.next();
			// sc.close();
			var input_path = getTestDataSetPath();
			var parsed_data = new Preparation(input_path);
			var leaned_data = new Leaning(parsed_data.get_input());
			var caled = new Generate(leaned_data);
			caled.show();
		} catch (Exception e) {
			// TODO
		}
	}

	// for test
	static String getTestDataSetPath() {
		var p = Path.of(System.getProperty("user.home"), "Develop","Java","markov_chain_practice","dataset.txt");
		return p.toString();
	}

}
