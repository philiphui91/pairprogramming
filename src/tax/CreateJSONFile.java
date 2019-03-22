package tax;

//import org.json.JSONArray;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

public class CreateJSONFile {

	public CreateJSONFile (List<Tax> _taxMasterList) {

		JSONObject obj = new JSONObject();
		
		String fileName = new SimpleDateFormat("'myJSON_'yyyyMMddHHmmss'.json'").format(new Date());
		try(FileWriter file = new FileWriter(ValueSetting.outFolderPath + fileName))
		{
			boolean firstLine = true;
			file.write("[");
			for (Tax _taxMaster: _taxMasterList) {
				if (firstLine)
					firstLine = false;
				else 
					file.write(",");
				
				obj.put("1_id", _taxMaster.getId());
				obj.put("2_joint_mpf_deduction", _taxMaster.RoundDouble(_taxMaster.getTaxJoint().getMpfDeduction(), 0));
				obj.put("3_separate_husband_mpf_deduction", _taxMaster.RoundDouble(_taxMaster.getTaxSeparateHusband().getMpfDeduction(), 0));
				obj.put("4_separate_wife_mpf_deduction", _taxMaster.RoundDouble(_taxMaster.getTaxSeparateWife().getMpfDeduction(), 0));
				obj.put("5_joint_tax_payable", _taxMaster.RoundDouble(_taxMaster.getTaxJoint().calTaxPayable(), 0));
				obj.put("6_separate_husband_tax_payable", _taxMaster.RoundDouble(_taxMaster.getTaxSeparateHusband().calTaxPayable(), 0));
				obj.put("7_separate_wife_tax_payable", _taxMaster.RoundDouble(_taxMaster.getTaxSeparateWife().calTaxPayable(), 0));
				obj.put("8_sep_husband_75%_tax_payable", _taxMaster.RoundDouble(Deduction75percent(_taxMaster.getTaxSeparateHusband().calTaxPayable()), 0));
				obj.put("9_sep_wife_75%_tax_payable", _taxMaster.RoundDouble(Deduction75percent(_taxMaster.getTaxSeparateWife().calTaxPayable()), 0));
				
				obj.put("10_recommendation", _taxMaster.getRecommendation());
				
				file.write(obj.toString());
			}
			file.write("]");
		
		
			
//			file.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("file generated. file name: " + fileName);
	}
	
	public Double Deduction75percent(Double _tax) {
		Double _75tax = _tax * 0.75;
		if (_75tax > 20000.0) {
			_75tax = 20000.0;
		}
		return _tax - _75tax;
	}
	
}
