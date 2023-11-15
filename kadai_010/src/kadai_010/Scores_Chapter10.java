package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
        Connection con = null;
        Statement insertstatement = null;
        
        String[][] scoreList = {
				{"侍健太", "65", "90"},
				{"刀沢彩香", "85", "70"},
				{"戦国広志", "75", "85"},
				{"武士山美咲", "75", "95"},
				{"武者小路勇気", "0", "0"}
		};
        
       
        try {
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "Ukingdom2"
            );

            System.out.println("データベース接続成功");
            
            //SQL準備        
            String sql = "SELECT * FROM scores;";
            String insertsql = "INSERT INTO scores(id, name, score_math, score_english) VALUES(?, ?, ?, ?);";
            insertstatement = con.prepareStatement(sql);
        
        insertstatement = con.createStatement();
        //更新
         String scoresql = "UPDATE scores SET score_math = '95', score_english = '80', WHERE id = 5;";
         //実行
         int rowCnt = insertstatement.executeUpdate(scoresql);
         System.out.println(rowCnt + "件レコードが更新されました");
         
         //並び替え
         String odersql = "SELECT FROM scores ORDER BY score_math ASC;";
         
         ResultSet result = insertstatement.executeQuery(scoresql);

         while(result.next()) {
             int id = result.getInt("id");
             String name = result.getString("name");
             String score_math  = result.getString("score_math");
             String score_english = result.getString("score_english");
             System.out.println(result.getRow() + "件目：生徒id=" + id + "／氏名=" + name + "/数学=" + score_math + "/英語＝" + score_english);
         }
        } catch(SQLException e) {
                System.out.println("エラー発生" + e.getMessage());
        }finally { 
                if( insertstatement != null ) {
                    try { insertstatement.close(); } catch(SQLException ignore) {}
                }
                if( con != null ) {
                    try { con.close(); } catch(SQLException ignore) {}
                }
            }
	
	}   
}

