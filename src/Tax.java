import java.util.HashMap;
import java.util.Map;


public class Tax extends TaxMaster {

	TaxDao taxSeparateHusband = new TaxDao();
	TaxDao taxSeparateWife = new TaxDao();
	TaxDao taxJoint = new TaxDao();
	
	public Tax() {
		Map<YearOfAssessment, Double> _MPFMaxDeduction = new HashMap<YearOfAssessment, Double>();
		_MPFMaxDeduction.put(YearOfAssessment.Y2012_13, 14500.0);
		_MPFMaxDeduction.put(YearOfAssessment.Y2013_14, 15000.0);
		_MPFMaxDeduction.put(YearOfAssessment.Y2014_15, 17500.0);
		_MPFMaxDeduction.put(YearOfAssessment.Y2015_16, 18000.0);
		_MPFMaxDeduction.put(YearOfAssessment.Y2016_17, 18000.0);
		_MPFMaxDeduction.put(YearOfAssessment.Y2017_18, 18000.0);
		_MPFMaxDeduction.put(YearOfAssessment.Y2018_19, 18000.0);
		super.setMPFMaxDeduction(_MPFMaxDeduction);
		
		Map<YearOfAssessment, Double> _MPFMinIncome = new HashMap<YearOfAssessment, Double>();
		_MPFMinIncome.put(YearOfAssessment.Y2012_13, 6500.0);
		_MPFMinIncome.put(YearOfAssessment.Y2013_14, 6500.0);
		_MPFMinIncome.put(YearOfAssessment.Y2014_15, 6500.0);
		_MPFMinIncome.put(YearOfAssessment.Y2015_16, 7100.0);
		_MPFMinIncome.put(YearOfAssessment.Y2016_17, 7100.0);
		_MPFMinIncome.put(YearOfAssessment.Y2017_18, 7100.0);
		_MPFMinIncome.put(YearOfAssessment.Y2018_19, 7100.0);
		super.setMPFMinIncome(_MPFMinIncome);
	}
	
}
