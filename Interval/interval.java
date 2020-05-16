import java.util.Scanner;

class Interval {
	
	private double low;
	private double high;
	
	public Interval() {
		this.setInterval(0, 0);
    }
    
	public Interval(Interval interval) {
		this.setInterval(interval);
	}
	
	public Interval(double low, double high) {
		this.setInterval(low, high);
	}
	
	public Interval(double high) {
		this.setInterval(0, high);
	}
	
	public Interval clone() {
		return new Interval(this);
    }
    
	public void setInterval(double low, double high) {
		this.low = low;
		this.high = high;
	}
	
	public void shift(double magnitude) {
		this.low += magnitude;
		this.high += magnitude;
	}
	
	public Interval shifted(double magnitude) {
		Interval interval = this.clone();
		interval.shift(magnitude);
		return interval;
	}
	
	public void doubleSize() {
		this.high = this.high + this.length();
	}
	
	public void setInterval(Interval interval) {
		this.setInterval(interval.getLow(), interval.getHigh());
	}
		
	public double length() {
		return this.high - this.low;
	}
	
	public double getLow() {
		return this.low;
	}
	
	public double getHigh() {
		return this.high;
	}
	
	public boolean equal(Interval interval) {
		return this.low == interval.getLow() && 
				this.high == interval.getHigh();
	}
	
	public boolean includes(double value) {
		return this.low <= value && value <= this.high;
	}
	
	public boolean includes(Interval interval) {
		assert interval != null;	
		return this.includes(interval.getLow()) && this.includes(interval.getHigh());
	}
	
	public boolean intersects(Interval interval) {
		assert interval !=  null;
		return this.includes(interval.getLow()) ||
				this.includes(interval.getHigh()) ||
				interval.includes(this);
	}
	
	public Interval intersection(Interval interval) {
		assert this.intersects(interval);
		if(this.includes(interval)) {
			return interval.clone();
		} else if(interval.includes(this)) {
			return this.clone();
		} else if(this.includes(interval.getLow())) {
			return new Interval(interval.getLow(), this.high);
		}else {
			return new Interval(this.low, interval.getHigh());
		}
	}
	
	public void readData() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Low: ");
		double low = scan.nextDouble();
		System.out.println("High: ");
		double high = scan.nextDouble();
		this.setInterval(low, high);
	}
	
	public void display() {
		System.out.println("[" + this.low + ", " + this.high + "]");
    }
    
	public static void main(String[] args) {
		Interval test =  new Interval();
		test.readData();
		test.display();
		System.out.println(test.length());
	}
}
