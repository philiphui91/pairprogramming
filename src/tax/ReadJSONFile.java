package tax;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSONFile {

	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader(ValueSetting.outFolderPath + "myJSON.json"));
			JSONObject jsonObject = (JSONObject) obj;
			String wife_income = (String) jsonObject.get("wife_income");
			System.out.print(wife_income +"");
			
		} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		catch (ParseException e) {e.printStackTrace();}
		catch (Exception e) {e.printStackTrace();}
		
	}
		
}
