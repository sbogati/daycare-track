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
import javax.swing.JOptionPane;

public class Daycare {
	public static void main(String[] args) {
		final int MAX_CHILDREN = 8;
		int age;
		int numAllergies;
		boolean enterMore;
      //Create a array that will hold Child objects
		Child children[] = new Child[MAX_CHILDREN];
      
      /* Continue prompting Monica to enter in Child information 
         until she indicates she's done or if the maximum 
         children has been entered. If the child is ineligible,
         show error message. If the child is eligible, create
         the child or tutored child object.
      */
		do {
			String fName = getName("Enter the first Name ");
			String lName = getName("Enter the last Name ");
			age = getNumber("Enter the age of the child");
			if (age >= Child.MIN_ELIGIBLE_AGE && age <= Child.MAX_ELIGIBLE_AGE) {
				numAllergies = getNumber("Enter the number of allergies the child has");
				if (numAllergies >= Child.MIN_NUM_FOOD_ALLERGIES
						&& numAllergies <= Child.MAX_NUM_FOOD_ALLERGIES) {
					boolean isChildTutored = JOptionPane.showConfirmDialog(
							null, "Is " + fName + " going tutored?") == JOptionPane.YES_OPTION;
					if (isChildTutored) {
						children[Child.getNumChild()] = new TutoredChild(fName,
								lName, age, numAllergies);						
						getTutoredChildInformation(children[Child.getNumChild() - 1], numAllergies);
					} else {
						children[Child.getNumChild()] = new Child(fName, lName,
								age, numAllergies);
						getChildInformation(children[Child.getNumChild() - 1],
								numAllergies);
					}
				} else
					errorMessage(fName);
			} else
				errorMessage(fName);
			enterMore = JOptionPane.showConfirmDialog(null,
					"Is there more children") == JOptionPane.YES_OPTION;
		} while (Child.getNumChild() <= MAX_CHILDREN && enterMore);     
		printReport(children);
	}

	/**
      Prompt Monica to enter Child's information until each valid
      input has been entered
            
      @param child The child's information to be enterd in for
      @param numAllergies The number of allergies the child has
      return void
	  */
   private static void getChildInformation(Child child, int numAllergies) {
		if (numAllergies > Child.MIN_NUM_FOOD_ALLERGIES) {
			String[] foodAllergies = getFoodAllergies(child, numAllergies);
			child.setFoodAllergies(foodAllergies);
		}

		int numOfDays;
		do {
			numOfDays = getNumber("Enter a number of days service is required");
			if (!child.setNumOfDays(numOfDays)) {
				JOptionPane
						.showMessageDialog(null,
								"Only days between 1 and 5 inclusive per week is available. Enter again.");
			}
		} while (!child.setNumOfDays(numOfDays));

		child.setRequiresExtendedHours(JOptionPane.showConfirmDialog(null,
				"Does " + child.getFName() + " require extended hours?") == JOptionPane.YES_OPTION);

		String streetAddress;
		do {
			streetAddress = JOptionPane.showInputDialog("Enter street address");
			if (!child.setStreetAddress(streetAddress)) {
				JOptionPane.showMessageDialog(null,
						"Enter a valid street address");
			}
		} while (!child.setStreetAddress(streetAddress));

		String phoneNumber;
		do {
			phoneNumber = JOptionPane
					.showInputDialog("Enter phone # in the format of (xxx) xxx-xxxx");
			if (!child.setPhoneNumber(phoneNumber)) {
				JOptionPane
						.showMessageDialog(
								null,
								"Invalid phone #. Enter a valid phone number in the format of (xxx) xxx-xxxx. \nExample # is: (703) 893-5993 ");
			}
		} while (!child.setPhoneNumber(phoneNumber));

		String emailAddress;
		do {
			emailAddress = JOptionPane
					.showInputDialog("Enter email address in the format of  xxx@yyy.com");
			if (!child.setEmailAddress(emailAddress)) {
				JOptionPane
						.showMessageDialog(
								null,
								"Invalid email. Enter email address in the format of  xxx@yyy.com. \nExample email is: example@gmu.edu");
			}
		} while (!child.setEmailAddress(emailAddress));
	}
   
   /**
      Prompt Monica to enter Child's allergies until each valid
      input has been entered
            
      @param child The child's allergies to be enterd in for
      @param numAllergies The number of allergies the child has
      return value The list of allergies child has
	  */
	private static String[] getFoodAllergies(Child child, int numAllergies) {
		String value[] = new String[numAllergies];
		for (int x = 0; x < numAllergies; x++) {
			value[x] = getName("Enter the no." + (x + 1) + " name of the food "
					+ child.getFName() + " is allergic to");
		}
		return value;
	}
   
   /**
      Prompt Monica to enter Tutored Child's information until each valid
      input has been entered
            
      @param child The tutored child's information to be enterd in for
      @param numAllergies The number of allergies the tutored child has
      return void
	  */
	private static void getTutoredChildInformation(Child tutoredChild, int numAllergies) {
      getChildInformation(tutoredChild, numAllergies);
		TutoredChild child = (TutoredChild) tutoredChild;
		int gradeLevel;
		do {
			gradeLevel = getNumber("Enter a grade level between "
					+ TutoredChild.MINIMUM + " and " + TutoredChild.MAXIMUM
					+ ". Enter 0 for level K.");
			if (!child.setGradeLevel(gradeLevel)) {
				JOptionPane.showMessageDialog(null,
						"Invalid. Enter a grade level between "
								+ TutoredChild.MINIMUM + " and "
								+ TutoredChild.MAXIMUM
								+ ". Enter 0 for level K.");
			}
		} while (!child.setGradeLevel(gradeLevel));

		int numOfCourses;
		do {
			numOfCourses = getNumber("Enter a # of courses between "
					+ (TutoredChild.MINIMUM + 1) + " and "
					+ TutoredChild.MAXIMUM + " inclusive.");
			if (!child.setNumOfCourses(numOfCourses)) {
				JOptionPane.showMessageDialog(null,
						"Invalid. Enter a # of courses between "
								+ (TutoredChild.MINIMUM + 1) + " and "
								+ TutoredChild.MAXIMUM);
			}
		} while (!child.setNumOfCourses(numOfCourses));

	}
   
   /**
      Prompt for name until the input is not empty all letters
            
      @param message The message to be shown when prompting for input
      return The validated string input
	  */
	private static String getName(String message) {
		String value = "";
		do {
			value = JOptionPane.showInputDialog(message);
			if (!Child.validateName(value)) {
				JOptionPane.showMessageDialog(null,
						"Error. Enter a valid that are all Letters!");
			}
		} while (!Child.validateName(value));
		return value;
	}

	/**
      Prompt for number until the input is valid type
            
      @param message The message to be shown when prompting for input
      return The validated type number
	  */
   private static int getNumber(String message) {
		int value = 0;
		boolean valid = true;
		do {
			try {
				value = Integer.parseInt(JOptionPane.showInputDialog(message));
				valid = true;
			} catch (NumberFormatException e) {
				valid = false;
				JOptionPane.showMessageDialog(null, "Error. Enter again");
			}
		} while (!valid);
		return value;
	}

	/**
      Message to be shown if the child is ineligible
            
      @param name The name of the child that is ineligible
      return void
	  */
   private static void errorMessage(String name) {
		JOptionPane.showMessageDialog(null, name
				+ " is ineligible to be in the daycare");
	}

	/**
      Display well formatted report associated with each child,
      number of non-tutored Children, number of tutored Children,
      average cost of tutored Children, and average cost of 
      non-tutored Children
            
      @param children[] The array that contains the list of
         tutored and non tutored children
      return void
	  */
   private static void printReport(Child children[]) {
		String display = "";
		if (Child.getNumChild() > 0) {
			for (int x = 0; x < Child.getNumChild(); x++) {
				display += "CHILD " + (x + 1) + "\n" + children[x] + "\n\n";
				children[x].accumulateTotal();
			}
			display += "Total # of Children who are tutored: "
					+ TutoredChild.getNumTutoredChild()
					+ "\nTotal # of Children who are not tutored: "
					+ (Child.getNumChild() - TutoredChild.getNumTutoredChild())
					+ "\nAverage cost of children who are tutored ";
			if (TutoredChild.getTotalTutoredCost() > 0.0) {
				display += String.format(
						"$%.2f",
						(TutoredChild.getTotalTutoredCost())
								/ (TutoredChild.getNumTutoredChild()));
			} else {
				display += "$0.00";
			}
			display += "\nAverage cost of children who are not tutored: ";
			if ((Child.getTotalCost()) > 0.0) {
				display += String.format(
						"$%.2f",
						(Child.getTotalCost())
								/ (Child.getNumChild() - TutoredChild
										.getNumTutoredChild()));
			} else {
				display += "$0.00";
			}
			JOptionPane.showMessageDialog(null, display);
		} else {
			JOptionPane.showMessageDialog(null, "No Children has been entered");
		}
	}
}