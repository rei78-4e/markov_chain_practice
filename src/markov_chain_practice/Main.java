package markov_chain_practice;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try {
			ArrayList<String> lst = new ArrayList<String>();
			System.out.printf("do you use default leaning source? (y/n)> ");
			var sc = new Scanner(System.in);
      		if (sc.next().equals("y")) {
					lst.add(getTestDataSetPath());
					lst.add(getTestDataSetPathSf());
	  		} else {
				System.out.printf("please input souce path>");	
				lst.add(sc.next());
			}
			var parsed_data = new Preparation(lst.toArray(new String[0]));
			System.out.printf("analyzing...\n");
			var leaned_data = new Leaning(parsed_data.get_input());
			System.out.printf("analyzed\n");
			System.out.printf("caluculating...\n");
			var caled = new Generate(leaned_data);	
			System.out.printf("caluculated\n");

			System.out.printf("\nhow long do you want to generate?>");
			var i = sc.nextInt();
			sc.close();

			System.out.printf("\n");
			caled.loopShow("The", i);

			System.exit(0);
		} catch (Exception e) {
			System.err.printf("Error: %s", e);
			System.exit(1);
		}
	}

	// for test
	static String getTestDataSetPath() {
		var p = Path.of(System.getProperty("user.home"), "Develop", "Java", "markov_chain_practice", "dataset.txt");
		return p.toString();
	}

	static String getTestDataSetPathSf() {
		var p = Path.of(System.getProperty("user.home"), "Develop", "Java", "markov_chain_practice", "dataset_sf.txt");
		return p.toString();
	}

}
