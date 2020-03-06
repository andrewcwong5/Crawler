import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
	public static DB db = new DB();
	
	public static void main(String[] args) throws SQLException, IOException {
		db.runSql2("Truncate Record;");
		processPage("https://www.cochranelibrary.com/cdsr/reviews/topics");
		System.out.println("processing page");
		
	}

	private static void processPage(String URL) throws SQLException, IOException {
		
		//checks if URL is already in db
		String sql = "select * from Record where URL = '"+URL+"'";
		ResultSet rs = db.runSql(sql);
		if(rs.next()) {
			
		}else {
			//store the URL to db to avoid parsing again
			sql = "INSERT INTO Record " + " (URL) VALUES " + "(?);";
			PreparedStatement stmt = db.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, URL);
			stmt.execute();
			
			System.out.println(URL);
			
			//Print to txt file cochrane_reviews.txt
//			try {
//				FileWriter fw = new FileWriter("cochrane_reviews.txt");
//				PrintWriter pw = new PrintWriter(fw);
//			
//					for (String string : URL) {
//						pw.println(string);
//					}
//			pw.close();
//			}
//			catch (IOException e) {
//				e.printStackTrace();
//			}
		
			
			
			//use Jsoup
			Document doc = Jsoup.connect("https://www.cochranelibrary.com/cdsr/reviews/topics").get();
//			
//			if(doc.text().contains("research")){
//				System.out.println(URL);
//			}
//			
			//get all links and recursively call the processPage method
			Elements questions = doc.select("a[href]");
			for(Element link: questions) {
				if(link.attr("href").contains("cochranelibrary.com"))
					processPage(link.attr("abs:href"));
			}
		}
		
	}

}
