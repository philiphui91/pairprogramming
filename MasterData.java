import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;


public class MasterData {
	
	private YearOfAssessment year;
	
	private Double taxGap;
	
	private Double[] taxRate; 
	
	private Double standardRate;
	
	private Map<YearOfAssessment, Double> MPFMaxDeduction = new HashMap<YearOfAssessment, Double>();
	
	private Map<YearOfAssessment, Double> MPFMinIncome = new HashMap<YearOfAssessment, Double>();
	
	public YearOfAssessment getYear() {
		return year;
	}

	public void setYear(YearOfAssessment year) {
		this.year = year;
	}
	

	public Double getTaxGap() {
		return taxGap;
	}

	public void setTaxGap(Double taxGap) {
		this.taxGap = taxGap;
	}

	public Double[] getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double[] taxRate) {
		this.taxRate = taxRate;
	}

	public Double getStandardRate() {
		return standardRate;
	}

	public void setStandardRate(Double standardRate) {
		this.standardRate = standardRate;
	}

	public Map<YearOfAssessment, Double> getMPFMaxDeduction() {
		return MPFMaxDeduction;
	}

	public void setMPFMaxDeduction(Map<YearOfAssessment, Double> _MPFMaxDeduction) {
		MPFMaxDeduction = _MPFMaxDeduction;
	}

	public Map<YearOfAssessment, Double> getMPFMinIncome() {
		return MPFMinIncome;
	}

	public void setMPFMinIncome(Map<YearOfAssessment, Double> _MPFMinIncome) {
		MPFMinIncome = _MPFMinIncome;
	}
	
	public String RoundDouble(Double d, int rounding) {
		if (d == null) 
			d = 0.0; 
		
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(rounding); 
		nf.setRoundingMode(RoundingMode.DOWN);
		return nf.format(d);
	}

}
