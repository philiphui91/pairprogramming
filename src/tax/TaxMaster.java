package tax;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import tax.setting.YearOfAssessment;

public class TaxMaster {
	
	private String id;
	
	private YearOfAssessment year;
	
	private Double taxGap;
	
	private Double[] taxRate; 
	
	private Double standardRate;
	
	private Map<YearOfAssessment, Double> MPFMaxDeduction = new HashMap<YearOfAssessment, Double>();
	
	private Map<YearOfAssessment, Double> MPFMinIncome = new HashMap<YearOfAssessment, Double>();
	
	private Double basicAllowance;
	
	private Double marriedAllowance;
	
	private String recommendation;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	
	
	public Double getBasicAllowance() {
		return basicAllowance;
	}

	public void setBasicAllowance(Double basicAllowance) {
		this.basicAllowance = basicAllowance;
	}

	public Double getMarriedAllowance() {
		return marriedAllowance;
	}

	public void setMarriedAllowance(Double marriedAllowance) {
		this.marriedAllowance = marriedAllowance;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
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
