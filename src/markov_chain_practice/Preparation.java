// 解析前のデータ処理 入力ファイルの読み込みと単語分割を行う

package markov_chain_practice;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Preparation {
    // 分割された単語列
    String[] input;
    ArrayList<Path> loaded_path_lst;

    /**
     * @param p: ソースファイルのパスリスト
     * @throws Exception
     */
    public Preparation(Path[] p) throws Exception {
        try {
            this.loaded_path_lst = new ArrayList<Path>();
            this.input = this.Parse(this.get_some_input(p));
        } catch (Exception e) {
            throw e;
        }
    }

    public Integer LoadedWordsNum() {
        return this.input.length;
    }

    public ArrayList<Path> LoadedPathList() {
        return this.loaded_path_lst;
    }

    public String[] get_input() {
        return this.input;
    }

    /**
     * 複数ファイルからの入力を結合して返す
     */
    String get_some_input(Path[] pathes) throws Exception {
        String res = new String();
        for (var p : pathes) {
            res += get_input(p);
        }
        return res;
    }

    /**
     * this function is possibly return error if failed read input file
     * 
     * @param path: 入力ファイルパス
     * @return ファイル内容の文字列
     */
    String get_input(Path p) throws Exception {

        // ファイルの存在確認
        if (!Files.isRegularFile(p)) {
            throw new InvalidInput(String.format("invalid input: %s", p));
        }

        // Io操作に失敗する可能性があるため
        try {
            var s = Files.readString(p)
                    .replaceAll(" {2,}", " ")
                    .replaceAll("(?m)^[ \\t]*\\r?\\n", "");
            this.loaded_path_lst.add(p);
            return s;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param input: 入力値
     * @return: spaceで分割した値
     */
    String[] Parse(String input) {
        return input.split("\\s+");
    }
}
