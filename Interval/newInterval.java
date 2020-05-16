import java.util.Scanner;

class NewInterval {
	
	private double middle;
	private double length;
	
	public NewInterval () {
		this.setNewInterval(0, 0);
    	}
    
	public NewInterval (NewInterval newInterval) {
		this.setNewInterval(newInterval);
	}
	
	public NewInterval(double low, double high) {
		this.setNewInterval(low, high);
	}
	
	public NewInterval(double high) {
		this.setNewInterval(0, high);
	}
	
	public NewInterval clone() {
		return new NewInterval(this);
	}
	
	public void setLow(double low) {
		this.length = this.getHigh() - low;
		this.middle = low + this.length/2;
	}
	
	public void setHigh(double high) {
		this.length = high - this.getLow();
		middle = high - this.length/2;
	}
	
	
	public void setNewInterval(double low, double high) {
		this.setLow(low);
		this.setHigh(high);
	}
	
	public void shift(double magnitude) {
		this.setLow(this.getLow() + magnitude);
		this.setHigh(this.getHigh() + magnitude);
	}
	
	public NewInterval shifted(double magnitude) {
		NewInterval NewInterval = this.clone();
		NewInterval.shift(magnitude);
		return NewInterval;
	}
	
	public void doubleSize() {
		this.setHigh(this.getHigh() + this.length());
	}
	
	public void setNewInterval(NewInterval newInterval) {
		this.setNewInterval(newInterval.getLow(), newInterval.getHigh());
	}
		
	public double length() {
		return this.getHigh() - this.getLow();
	}
	
	public double getLow() {
		return this.middle - this.length/2;
	}
	
	public double getHigh() {
		return this.middle + this.length/2;
	}
	
	public boolean equal(NewInterval newInterval) {
		return this.getLow() == newInterval.getLow() && 
				this.getHigh() == newInterval.getHigh();
	}
	
	public boolean includes(double value) {
		return this.getLow() <= value && value <= this.getHigh();
	}
	
	public boolean includes(NewInterval newInterval) {
		assert newInterval != null;	
		return this.includes(newInterval.getLow()) && this.includes(newInterval.getHigh());
	}
	
	public boolean intersects(NewInterval newInterval) {
		assert newInterval !=  null;
		return this.includes(newInterval.getLow()) ||
				this.includes(newInterval.getHigh()) ||
				newInterval.includes(this);
	}
	
	public NewInterval intersection(NewInterval newInterval) {
		assert this.intersects(newInterval);
		if(this.includes(newInterval)) {
			return newInterval.clone();
		} else if(newInterval.includes(this)) {
			return this.clone();
		} else if(this.includes(newInterval.getLow())) {
			return new NewInterval(newInterval.getLow(), this.getHigh());
		}else {
			return new NewInterval(this.getLow(), newInterval.getHigh());
		}
	}
	
	public void readData() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Low: ");
		double low = scan.nextDouble();
		System.out.println("High: ");
		double high = scan.nextDouble();
		this.setNewInterval(low, high);
	}
	
	public void display() {
		System.out.println("[" + this.getLow() + ", " + this.getHigh() + "]");
    	}
    
	public static void main(String[] args) {
		NewInterval test =  new NewInterval();
		test.readData();
		test.display();
		System.out.println(test.length());
	}
}
