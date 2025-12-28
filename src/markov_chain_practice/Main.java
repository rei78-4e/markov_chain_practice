package markov_chain_practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		// Scannerと全体でのエラーハンドリングのためtry
		try {
			var sc = new Scanner(System.in);

			// detasetの選択。数字以外(parse to intに失敗)だった場合はパスとして取得
			System.out.println();
			System.out.println("[STEP 1] Dataset Selection");
			System.out.println("-----------------------------------------");
			System.out.println("Chose a dataset type:");

			System.out.println("  0: [ALL] \t - Combine all available data");
			System.out.println("  1: [NORMAL] \t - Starndard data set");
			System.out.println("  2: [SF] \t - SF dataset");
			System.out.println("  3: [SHIKA] \t - shikanoko special dataset");
			System.out.println("  4: [NORMAL2] \t - Nature and Environment");
			System.out.println("  [CUSTOM] \t - custom dataset path (numbers prohibited)");
			System.out.printf("\nSelect number (or input path)> ");

			var input = sc.nextLine();

			// 入力値がintに変換出来ればデフォルトのデータセットを選択
			// 失敗すれば入力をそのままパスとして取り込む
			var lst = parse_option(input)
					.map(n -> {
						try {
							return switch (n) {
								case 1 -> List.of(Path.of(default_pare().toString(), "normal.txt"));
								case 2 -> List.of(Path.of(default_pare().toString(), "sf.txt"));
								case 3 -> List.of(Path.of(default_pare().toString(), "shika.txt"));
								case 4 -> List.of(Path.of(default_pare().toString(), "normal2.txt"));
								default -> get_all_dataset_path();
							};
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}).orElse(List.of(Path.of(input)));

			System.out.printf("\n-----------------------------------------\n");
			System.out.printf("[INFO] Loading dataset...\n");
			// Preparationにてデータの取得と単語へのパースを行う
			var parsed_data = new Preparation(lst.toArray(Path[]::new));
			var loaded_num = parsed_data.LoadedWordsNum();
			var loaded_path_lst = parsed_data.LoadedPathList();
			loaded_path_lst.forEach(ip -> {
				System.out.printf("  - Path: %s\n", ip.toString());
			});
			System.out.println();
			System.out.printf("[OK] Loaded %d words successfully\n", loaded_num);

			System.out.println();
			System.out.printf("[PROCESS]\n");
			System.out.printf("  - Analyzing loaded text...");
			// Leaningにて各単語の出現回数をカウントしてベクトル的に格納
			var leaned_data = new Leaning(parsed_data.get_input());
			System.out.printf(" [DONE]\n");

			// Generateにて出現回数から次単語の出現確率を各ベクトルに変換
			System.out.printf("  - Calculating probablities...");
			var caled = new Generate(leaned_data);
			System.out.printf(" [DONE]\n");

			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println("Markov Chain Model is ready!");
			System.out.println("-----------------------------------------");

			var file_name_lst = IntStream.range(0, loaded_path_lst.size())
					.mapToObj(i -> {
						var spaces = (i == 0) ? "" : "             ";
						return String.format("%s%s\n", spaces, loaded_path_lst.get(i).getFileName().toString());
					})
					.collect(Collectors.joining());
			System.out.println();
			// 何単語分生成するかを質問
			System.out.printf("[CONFIG]\n  - Dataset: %s\nHow long to generate> ", file_name_lst);
			var i = sc.nextInt();
			sc.nextLine(); // 空文字を消費して後に残さない

			System.out.printf("Seed word (\"***\" or not found = random)> ");
			var in_seed = sc.nextLine();
			String seed;
			if ((in_seed.isEmpty()) || in_seed.equals("***") || (!caled.checkExistWord(in_seed))) {
				seed = caled.randomStart();
			} else {
				seed = in_seed;
			}

			System.out.printf("How many generations?>");
			var in_num = sc.nextLine();
			var nn = parse_option(in_num)
					.map(n -> {
						return n;
					})
					.orElse(1);
			sc.close();

			for (int j = 1; j < nn + 1; j++) {
				System.out.printf("\n[RESULT]\n-----------------------------------------\n\n");
				// iは生成する単語数
				var gened_words_count = caled.loopShow(seed, i, 0);
				System.out.printf("\n-----------------------------------------\n");

				System.out.printf(
						"\n[STATUS]\n  - Words generated: %d\n  - Count: %d\n  - Status: Success\n  - Seed word: \"%s\"\n",
						gened_words_count, j, seed);
			}

			System.exit(0);
		} catch (Exception e) {
			System.err.printf("\n\n[ERROR] \n%s\n", e);
			System.exit(1);
		}
	}

	static Optional<Integer> parse_option(String s) {
		try {
			return Optional.of(Integer.parseInt(s));
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	static List<Path> get_all_dataset_path() throws IOException {
		var p = default_pare();
		try (Stream<Path> stream = Files.list(p)) {
			List<Path> path_list = stream.filter(f -> !Files.isDirectory(f)).sorted()
					.collect(Collectors.toList());
			return path_list;
		}
	}

	static Path default_pare() {
		return Path.of(Paths.get("").toAbsolutePath().toString(), "dataset");
	}
}
