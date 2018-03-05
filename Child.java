/**
   Name: Sanket Bogati
   Date: 11/06/2016
   Course/Section: IT 206.202
   Assignment: Programming Assignment 6

   Description:

   This program supports Gifts and Knickknacks to assist Monica to track
   children in company's new daycare program. Currently, the program
   supports 8 children.   

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
public class Child {
	public static double CHARGE_PER_DAY = 106.26;
	public static double EXTENDED_CHARGE = 16.96;
	public static int MIN_ELIGIBLE_AGE = 3;
	public static int MAX_ELIGIBLE_AGE = 11;
	public static int MIN_NUM_FOOD_ALLERGIES = 0;
	public static int MAX_NUM_FOOD_ALLERGIES = 7;
	public static int MIN_NUM_DAYS_IN_PROGRAM = 1;
	public static int MAX_NUM_DAYS_IN_PROGRAM = 5;
	private String fName;
	private String lName;
	private int age;
	private String streetAddress;
	private String phoneNumber;
	private String emailAddress;
	private int numOfDays;
	private int numFoodAllergies;
	private String[] foodAllergies = new String[MAX_NUM_FOOD_ALLERGIES];
	private boolean requiresExtendedHours;
	private static double totalCost = 0.0;
	private static int numChild = 0;

	//Default constructor that increments everytime a child object is created
   public Child() {
		numChild++;
	}

	/**
   Constructor that sets fName, lName, age, numFoodAllergies and calls the
      default constructor
   @param fName The first name of the child
   @param lName The last name of the child
   @age The age of the child
   @numFoodAllergies The number of food allergies the child has    
   **/
   public Child(String fName, String lName, int age, int numFoodAllergies) {
		this();
		this.fName = fName;
		this.lName = lName;
		this.age = age;
		this.numFoodAllergies = numFoodAllergies;
	}
   
   /**
   The following methods returns the informations associated
   with the current instance of an object    
   **/
	public static int getNumChild() {return numChild;}
	public String getFName() {return this.fName;}
   public String getLName() {return this.lName;}
	public String getStreetAddress() {return this.streetAddress;}
	public String getPhoneNumber() {return this.phoneNumber;}
	public String getEmailAddress() {return this.emailAddress;}
	public int getNumOfDays() {return this.numOfDays;}
	public int getNumFoodAllergies() {return this.numFoodAllergies;}
   public static double getTotalCost() {return totalCost;}
   //Food array is copied into a new array and the new array is returned
   public String[] getFoodAllergies() {
		String[] tempArray = new String[this.foodAllergies.length];
		for (int x = 0; x < this.getNumFoodAllergies(); x++) {
			tempArray[x] = this.foodAllergies[x];
		}return tempArray;}
	public boolean getRequiresExtendedHours() {return this.requiresExtendedHours;}
   
   /**
      Set the first name of the child if it is valid
      @param fName the first name to be set
      @return Whether or not the first name has been set
	  **/
	public boolean setFName(String fName) {
		if (validateName(fName)) {
			this.fName = fName;
			return true;
		} else {
			return false;
		}
	}

	/**
      Set the last name of the child if it is valid
      @param lName the last name to be set
      @return Whether or not the last name has been set
	  **/
   public boolean setLName(String lName) {
		if (validateName(lName)) {
			this.lName = lName;
			return true;
		} else {
			return false;
		}
	}

	/**
      Validate the name so that it contains all letters
      @param input The input to be validate
      @return Whether or not the input is valid
	  **/
   public static boolean validateName(String input) {
		boolean valid = true;
		if (input.equals("")) {
			valid = false;
		} else {
			for (int x = 0; x < input.length(); x++) {
				if (!Character.isLetter(input.charAt(x))) {
					valid = false;
				}
			}
		}
		return valid;
	}

	/**
      Set the street address of the child if it is valid
      @param streetAddress The street address to be set
      @return Whether or not the street address has been set
	  **/
   public boolean setStreetAddress(String streetAddress) {
		if (validateString(streetAddress)) {
			this.streetAddress = streetAddress;
			return true;
		} else {
			return false;
		}
	}

	/**
      Set the phone number of a child if it is valid
      @param phoneNumber The phone number to be validated and set
      @return Whether or not phone number has been set
	  **/
   public boolean setPhoneNumber(String phoneNumber) {
		boolean valid = false;
		if (phoneNumber.length() == 14) {
			if (phoneNumber.charAt(0) == '(' && phoneNumber.charAt(4) == ')'
					&& phoneNumber.charAt(5) == ' '
					&& phoneNumber.charAt(9) == '-') {
				valid = true;
				if (valid) {
					for (int x = 0; x < phoneNumber.length(); x++) {
						if (Character.isLetter(phoneNumber.charAt(x))) {
							valid = false;
						} else if (x != 0 && x != 4 && x != 5 && x != 9) {
							if (!Character.isDigit(phoneNumber.charAt(x))) {
								valid = false;
							}
						}
					}
				}
			}
		}
		if (valid) {
			this.phoneNumber = phoneNumber;
			return true;
		} else {
			return false;
		}
	}

	/**
      Set the email addressof a child if it is valid
      @param emailAddress The email address to be validated and set
      @return Whether or not email address has been set
	  **/
   public boolean setEmailAddress(String emailAddress) {
		boolean valid = false;
		final int MIN_NUMER_OF_LETTERS = 2;
		final int MAX_NUM_OF_LETTERS = 4;
		int position1 = emailAddress.indexOf('@');
		int position2 = emailAddress.indexOf('.', position1);
		int numOfLetters = emailAddress.substring(position2 + 1).length();
		if (position1 > 0
				&& emailAddress.substring(position1 + 1, position2).length() > 0) {
			if (numOfLetters >= MIN_NUMER_OF_LETTERS
					&& numOfLetters <= MAX_NUM_OF_LETTERS) {
				valid = true;
			}
		}
		if (valid) {
			this.emailAddress = emailAddress;
			return true;
		} else {
			return false;
		}
	}

	/**
      Validate the string so that it is not empty
      @param input The string input to be validated
      @return Whether or not input string is valid
	  **/
   private static boolean validateString(String input) {
		if (input.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	/**
      Set the number of days child requires service if it is valid
      @param numOfDays The number of days child requires service
      @return Whether or not number of days has been set
	  **/
   public boolean setNumOfDays(int numOfDays) {
		if (numOfDays >= MIN_NUM_DAYS_IN_PROGRAM
				&& numOfDays <= MAX_NUM_DAYS_IN_PROGRAM) {
			this.numOfDays = numOfDays;
			return true;
		} else {
			return false;
		}
	}

	/**
      Set the number of food allergies child has if it is valid
      @param numOfDays The number of food allergies child has
      @return Whether or not number of allergies has been set
	  **/
   public boolean setNumFoodAllergies(int numFoodAllergies) {
		if (numFoodAllergies >= MIN_NUM_FOOD_ALLERGIES
				&& numFoodAllergies <= MAX_NUM_FOOD_ALLERGIES) {
			this.numFoodAllergies = numFoodAllergies;
			;
			return true;
		} else {
			return false;
		}
	}

	/**
      Set the allergies child has 
      @param foodAllergies The list of allergies child has
      @return void
	  **/
   public void setFoodAllergies(String[] foodAllergies) {
		for (int x = 0; x < getNumFoodAllergies(); x++) {
			this.foodAllergies[x] = foodAllergies[x];
		}
	}

	/**
      Set if the child requires extended hours
      @param requiresExtendedHours if the child requires extended hours
      @return void
	  **/
   public void setRequiresExtendedHours(boolean requiresExtendedHours) {
		this.requiresExtendedHours = requiresExtendedHours;
	}

	/**
      Calculate the tota cost of current child
      @return the cost that has been calculated
	  **/
   public double calculateCost() {
		double cost = 0.0;
		cost += CHARGE_PER_DAY * this.numOfDays;
		if (this.getRequiresExtendedHours() == true) {
			cost += EXTENDED_CHARGE * this.numOfDays;
		}
		return cost;
	}

	/**
      Accumulate the total cost of all non tutored children
      @return void
	  **/
   public void accumulateTotal() {
		totalCost += this.calculateCost();
	}

	/**
      Concatinate the list of food allergies child has
      @return String representation of the food allergies
	  **/
   public String foodAllergiesString() {
		String output = "";
		for (int x = 0; x < this.getNumFoodAllergies(); x++) {
			output += this.foodAllergies[x];
			if (x < (this.getNumFoodAllergies() - 1)) {
				output += ", ";
			}
		}
		return output;
	}

	/**
      Concatinate the information of current child object
      @return String representation of the child object
	  **/
   public String toStringWithoutCalculation() {
		String output = "";
		output += "   Name: " + this.getFName() + " " + this.getLName()
				+ "\n   Street Address: " + this.getStreetAddress()
				+ "\n   Phone Number: " + this.getPhoneNumber()
				+ "\n   Email Address: " + this.getEmailAddress()
				+ "\n   # of Service Days: " + this.getNumOfDays()
				+ "\n   # of Food Allergies: " + this.getNumFoodAllergies();
		if (this.getNumFoodAllergies() > 0) {
			output += "\n          Allergies List: " + foodAllergiesString();
		}
		output += "\n   Does the child require extended hours? ";
		if (this.getRequiresExtendedHours() == true) {
			output += "Yes!";
		} else {
			output += "No!";
		}
		return output;
	}

	/**
      Establish all of the information required to form String representaton of child object
      @return String representation of child object
	  **/
   public String toString() {
		return toStringWithoutCalculation() + "\n   Is the Child tutored? No!"
				+ "\n   Service Cost: "
				+ String.format("$%.2f", this.calculateCost());
	}
}