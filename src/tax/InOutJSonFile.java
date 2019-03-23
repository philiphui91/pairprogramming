package tax;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import tax.setting.ValueSetting;
import tax.setting.YearOfAssessment;

public class InOutJSonFile {

	public InOutJSonFile(String inputFileName) {
		JSONArray arr;
		List<Tax> taxList = new ArrayList<Tax>();
		try {
			arr = (JSONArray) readJsonSimpleDemo(ValueSetting.inFolderPath + inputFileName);

			System.out.println(arr);
			for (int i = 0; i < arr.size(); i++) {
				String line = arr.get(i).toString();
				JSONObject json = (JSONObject) org.skyscreamer.jsonassert.JSONParser.parseJSON(line);

				// Error Message will show if wife_income not found
				// Error Message: Exception in thread "main" org.json.JSONException:
				// JSONObject["wife_income"] not found.
				String wife_income_str = (json.get("wife_income")).toString();

				// Error Message will show if husband_income not find
				// Error Message: Exception in thread "main" org.json.JSONException:
				// JSONObject["husband_income"] not found.
				String husband_income_str = (json.get("husband_income")).toString();

				// Error Message will show if id not found
				// Error Message: Exception in thread "main" org.json.JSONException:
				// JSONObject["id"] not found.
				String id = (json.get("id")).toString();
				
				String year = (json.get("assessment_year")).toString();
				YearOfAssessment _year = YearOfAssessment.valueOf(year);
				

				TaxUtil taxUtil = new TaxUtil(stringConvertToDouble(husband_income_str),
						stringConvertToDouble(wife_income_str), _year, id);
				taxList.add(taxUtil.returnObj());
			}
			
			new CreateJSONFile(taxList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

//	    JSONObject jsonObject = (JSONObject) readJsonSimpleDemo(ValueSetting.inFolderPath + "test.json");
//	    System.out.println(jsonObject);
//	    System.out.println(jsonObject.get("wife_income"));
	}

	public static Object readJsonSimpleDemo(String filename) throws Exception {
		FileReader reader = new FileReader(filename);
		JSONParser jsonParser = new JSONParser();
		return jsonParser.parse(reader);
	}

	public static Double stringConvertToDouble(String inputStr) {
		// Error will show if input String cannot convert to Double
		// Error message: Exception in thread "main" java.lang.NumberFormatException:
		// For input string: {inputStr}
		Double d = Double.parseDouble(inputStr);
		;
		return d;
	}
}
