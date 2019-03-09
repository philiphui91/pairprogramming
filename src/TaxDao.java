import java.util.Map;


public class TaxDao {
	
	private Double income;
	
	private Double deduction;
	
	private Double allowances;
	
	public Double getIncome() {
		
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getDeduction() {
		return deduction;
	}

	public void setDeduction(Double deduction) {
		this.deduction = deduction;
	}

	public Double getAllowances() {
		return allowances;
	}

	public void setAllowances(Double allowances) {
		this.allowances = allowances;
	}

	public Double calNetChargeableIncome() {
		Double netChargeableIncome;
		Double _totalIncome = CheckNull(getIncome());
		Double _deductions = CheckNull(getDeduction());
		Double _allowances = CheckNull(getAllowances());
		
		netChargeableIncome = _totalIncome - _deductions - _allowances;
		if (netChargeableIncome < 0.0) {
			netChargeableIncome = 0.0;
		}
		return netChargeableIncome;
	}
	
	public Double calNetIncome() {
		Double netIncome;
		Double _totalIncome = CheckNull(getIncome());
		Double _deductions = CheckNull(getDeduction());
		
		netIncome = _totalIncome - _deductions;
		return netIncome;
	}
	
	public Double calProgessRateTax(Double taxGap, Double[] taxRate) {
		Double n = calNetChargeableIncome(); //netChargeableIncome
		Double tax = 0.0;

		int period = 0;
		while (n.doubleValue() > 0 && period < taxRate.length) {
			if (n - taxGap > 0 && period != taxRate.length - 1) {
				tax += taxGap * taxRate[period];
			} else {
				tax += n * taxRate[period];	
			}
			n -= taxGap;
			period ++;
		}
		 
		return tax;
	}
	
	public Double calStandardRateTax(Double standardRate) {
		Double n = calNetChargeableIncome(); //netChargeableIncome
		return n * standardRate;
	}
	
	public Double CheckNull(Double d) {
		if (d == null) {
			d = 0.0;
		}
		
		if (d < 0) {
			d = 0.0;
		}
		
		return d;
	}
	
	public void calMPFDeduction(Map<YearOfAssessment, Double> maxDeductionMap, YearOfAssessment year){
		Double _income = CheckNull(getIncome());
		Double mpfDeduction = _income * 0.05;
		
		//TODO: to be removed - Testing Use
//		System.out.println("MPF Deduction Start");
		Double mpfMaxDeduction = maxDeductionMap.get(year);
		if (mpfMaxDeduction == null) {
			try {
				throw new Exception("Year of Assessment out of range");
			} catch (Exception e) {
				e.printStackTrace(); //TODO: Logger
			}
		}
		if (mpfMaxDeduction <= mpfDeduction) {
			mpfDeduction = mpfMaxDeduction;
		}
		
		setDeduction(mpfDeduction);
		
		//TODO: to be removed - Testing Use
//		System.out.println("MPF Deduction: " + mpfDeduction);
	}

}
