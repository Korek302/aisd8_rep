package aisd8_rep;

public class Vehicle implements Comparable<Vehicle>
{
	int age;
	String model;
	
	public Vehicle(int age, String model)
	{
		this.age = age;
		this.model = model;
	}

	public int compareTo(Vehicle compareVehicle)
	{
		int compareAge = compareVehicle.age;
		return this.age - compareAge;
	}
	
	public int reverseCompareTo(Vehicle compareVehicle)
	{
		int compareAge = compareVehicle.age;
		return compareAge - this.age;
	}
	
	public String toString()
	{
		return (age+/*" "+model+*/" ");
	}
}
