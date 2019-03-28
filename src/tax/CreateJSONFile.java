package tax;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import tax.setting.ValueSetting;
import tax.setting.YearOfAssessment;

public class CreateJSONFile {

	public CreateJSONFile (List<Tax> _taxList, String _inputFileName) {

		JSONObject obj = new JSONObject();
		
		String fileName = new SimpleDateFormat("'myJSON_'yyyyMMddHHmmss_'"+ _inputFileName+ "'").format(new Date());
		try(FileWriter file = new FileWriter(ValueSetting.outFolderPath + fileName))
		{
			boolean firstLine = true;
			file.write("[");
			for (Tax _tax: _taxList) {
				if (firstLine)
					firstLine = false;
				else 
					file.write(",");
				
				obj.put("1_id", _tax.getId());
				obj.put("2_joint_mpf_deduction", _tax.RoundDouble(_tax.getTaxJoint().getMpfDeduction(), 0));
				obj.put("3_separate_husband_mpf_deduction", _tax.RoundDouble(_tax.getTaxSeparateHusband().getMpfDeduction(), 0));
				obj.put("4_separate_wife_mpf_deduction", _tax.RoundDouble(_tax.getTaxSeparateWife().getMpfDeduction(), 0));
				obj.put("5_joint_tax_payable", _tax.RoundDouble(_tax.getTaxJoint().calTaxPayable(), 0));
				obj.put("6_separate_husband_tax_payable", _tax.RoundDouble(_tax.getTaxSeparateHusband().calTaxPayable(), 0));
				obj.put("7_separate_wife_tax_payable", _tax.RoundDouble(_tax.getTaxSeparateWife().calTaxPayable(), 0));
				obj.put("8_recommendation", _tax.getRecommendation());
//				obj.put("9_sep_husband_75percent_tax_payable", _tax.RoundDouble(Deduction75percent(_tax.getTaxSeparateHusband().calTaxPayable(), _tax.getYear()), 0));
//				obj.put("10_sep_wife_75percent_tax_payable", _tax.RoundDouble(Deduction75percent(_tax.getTaxSeparateWife().calTaxPayable(), _tax.getYear()), 0));
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
	
	public Double Deduction75percent(Double _tax, YearOfAssessment _YOA) {
		Double _75tax = _tax * 0.75;
		
		//dirty code
		Double maxDeduction = 0.0;
		switch (_YOA) {
		case Y2012_13:
		case Y2013_14:
			maxDeduction = 10000.0;
		break;
		case Y2014_15:
		case Y2015_16:
		case Y2016_17:
			maxDeduction = 20000.0;
		break;
		case Y2017_18:
			maxDeduction = 30000.0;
		break;
		case Y2018_19:
			maxDeduction = 20000.0;
		break;
		default: 
			maxDeduction = 0.0;
		break;
	}
		if (_75tax > maxDeduction) {
			_75tax = maxDeduction;
		}
		return _tax - _75tax;
	}
	
}
