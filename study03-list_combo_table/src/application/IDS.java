package application;

public class IDS {
	private String name;
	private double result;
	private boolean isActive;

	public IDS(String name, double result, boolean isActive) {
		this.name = name;
		this.result = result;
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}