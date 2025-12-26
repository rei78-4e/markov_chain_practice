package markov_chain_practice;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// Scannerと全体でのエラーハンドリングのためtry
		try {
			// 学習データのファイルパス指定。デフォルトを使用する場合と自分で用意するかを選択
			ArrayList<String> lst = new ArrayList<String>();
			System.out.printf("do you use default leaning source? (y/n)> ");
			var sc = new Scanner(System.in);
			// yesなら既存のソースファイルのパスをヘルパー関数から取得
			if (sc.next().equals("y")) {
				lst.add(getTestDataSetPath());
				lst.add(getTestDataSetPathSf());
			} else { // noならソースパスの入力を促す
				System.out.printf("please input souce path>");
				lst.add(sc.next());
			}
			// Preparationにてデータの取得と単語へのパースを行う
			var parsed_data = new Preparation(lst.toArray(new String[0]));

			System.out.printf("analyzing...\n");
			// Leaningにて各単語の出現回数をカウントしてベクトル的に格納
			var leaned_data = new Leaning(parsed_data.get_input());
			System.out.printf("analyzed\n");

			// Generateにて出現回数から次単語の出現確率を各ベクトルに変換
			System.out.printf("caluculating...\n");
			var caled = new Generate(leaned_data);
			System.out.printf("caluculated\n");

			// 何単語分生成するかを質問
			System.out.printf("\nhow long do you want to generate?>");
			var i = sc.nextInt();
			// 入力はこれ以降ないのでここでclose()
			sc.close();

			// 出力のセパレータ
			System.out.printf("\n");

			// スタートはTheで固定
			// iは生成する単語数
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
