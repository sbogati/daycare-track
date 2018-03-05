/**
   Name: Sanket Bogati
   Date: 11/06/2016
   Course/Section: IT 206.202
   Assignment: Programming Assignment 6

   Description:

   This program supports Gifts and Knickknacks to assist Monica to track
   children in company's new daycare program. Currently, the program
   supports 8 children. TutoredChild contains all of the features
   as Child, but also contains grade level, and # of courses.

   This program will allow Monica to enter child’s information one at a 
   time until she indicates otherwise or 8 Children has been entered. 
   Only a child with valid name, age between 3 and 11, and allergies 
   less than 7 are allowed in the daycare program. Once it is determined
   that the child is eligible to be in the daycare program, it will ask 
   for phone#, email, if the child requires extended hours, and if the
   child requires tutoring, it will ask for grade level and the # of
   courses to be tutored in until all inputs are valid. 
   
   After maximum children has been entered, or if Monica indicates she's
   done entering children, Monica will see a well formatted report associated
   with each child, number of non-tutored Children, number of tutored 
   Children, average cost of tutored Children, and average cost of 
   non-tutored Children.     
 **/
public class TutoredChild extends Child {
	public static double SURCHARGE = 12;
	public static int MINIMUM = 0;
	public static int MAXIMUM = 8;
	private int gradeLevel;
	private int numOfCourses;
	private static double totalTutoredCost = 0.0;
	private static int numTutoredChild = 0;

	/**
   Constructor that sets fName, lName, age, numFoodAllergies and calls the
      superclass constructor
   @param fName The first name of the child
   @param lName The last name of the child
   @age The age of the child
   @numFoodAllergies The number of food allergies the child has
   **/

   public TutoredChild(String fName, String lName, int age,
			int numFoodAllergies) {
		super(fName, lName, age, numFoodAllergies);
		numTutoredChild++;
	}

	/**
   The following methods returns the informations associated
   with the current instance of an object    
   **/
   public static int getNumTutoredChild() {return numTutoredChild;}
   public int getGradeLevel() {return this.gradeLevel;}
   public int getNumOfCourses() {return this.numOfCourses;}
	public static double getTotalTutoredCost() {return totalTutoredCost;}

	/**
      Set the grade level of the tutored child if it is valid
      @param gradeLevel the grade level to be set
      @return Whether or not the grade level has been set
	  **/
   public boolean setGradeLevel(int gradeLevel) {
		if (gradeLevel >= MINIMUM && gradeLevel <= MAXIMUM) {
			this.gradeLevel = gradeLevel;
			return true;
		} else {
			return false;
		}
	}

	/**
      Set the number of courses if valid
      @param numOfCourses the number of courses to be set
      @return Whether or not the number of courses has been set
	  **/
   public boolean setNumOfCourses(int numOfCourses) {
		if (numOfCourses >= (MINIMUM + 1) && numOfCourses <= MAXIMUM) {
			this.numOfCourses = numOfCourses;
			return true;
		} else {
			return false;
		}
	}

	/**
      Calculate the tota cost of current tutored child
      @return the cost that has been calculated
	  **/
   public double calculateCost() {
		double cost = 0.0;
		cost = +super.calculateCost() + (this.numOfCourses * SURCHARGE);
		return cost;
	}

	/**
      Accumulate the total cost of all tutored children
      @return void
	  **/
   public void accumulateTotal() {
		totalTutoredCost += this.calculateCost();
	}

	/**
      Establish all of the information required to form String representaton of 
         tutoredchild object
      @return String representation of tutored child object
	  **/
   public String toString() {
		String output = "";
		output += super.toStringWithoutCalculation()
				+ "\n   Is the Child tutored? Yes! " + "\n   Grade Level: ";
		if (this.getGradeLevel() == 0) {
			output += "K";
		} else {
			output += this.getGradeLevel();
		}
		output += "\n   # of Courses: " + this.getNumOfCourses()
				+ "\n   Service Cost: "
				+ String.format("$%.2f", this.calculateCost());
		return output;
	}
}