package tax;
import java.util.HashMap;
import java.util.Map;


public class Tax extends TaxMaster {

	private TaxDao taxSeparateHusband;
	private TaxDao taxSeparateWife;
	private TaxDao taxJoint;
	
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

	public TaxDao getTaxSeparateHusband() {
		return taxSeparateHusband;
	}

	public void setTaxSeparateHusband(TaxDao taxSeparateHusband) {
		this.taxSeparateHusband = taxSeparateHusband;
	}

	public TaxDao getTaxSeparateWife() {
		return taxSeparateWife;
	}

	public void setTaxSeparateWife(TaxDao taxSeparateWife) {
		this.taxSeparateWife = taxSeparateWife;
	}

	public TaxDao getTaxJoint() {
		return taxJoint;
	}

	public void setTaxJoint(TaxDao taxJoint) {
		this.taxJoint = taxJoint;
	}

	
}
