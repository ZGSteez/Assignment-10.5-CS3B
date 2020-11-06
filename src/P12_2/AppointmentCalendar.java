package P12_2;

import java.util.*;
import java.util.Scanner;

public class AppointmentCalendar {
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private int count = 0;

    public void addAppointment(Appointment anAppointment) {
        appointments.add(anAppointment);
    }

    /**
     * Adds initial appointments for the user to see or manipulate
     */
    public void startingAppointments() {
        addAppointment(new Appointment("Dentist", "12/10/2020", "07:30", "10:30"));
        addAppointment(new Appointment("Physical Exam", "12/01/2020", "14:30", "16:30"));
        addAppointment(new Appointment("Christmas Party", "12/25/2020", "07:00", "19:00"));
        addAppointment(new Appointment("Thanksgiving Party", "11/26/2020", "07:00", "19:00"));
        addAppointment(new Appointment("Dog Grooming", "12/01/2020", "15:15", "19:15"));

    }

    /**
     * Removes appointment at specified date and index
     * @param userPick - the order in which the appointment is displayed to the user
     * @param date - date the user has picked
     */
    public void removeAppointment(int userPick, String date) {
        int counter = userPick;
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).returnDate().equals(date)) {

                if (counter == 1) {
                    appointments.remove(i);
                    System.out.println("Appointment successfully deleted.");
                }
                counter--;

            }
        }
    }


    /**
     * Displays all appointments on a date
     * @param date - date the user entered
     */
    public void showAppointments(String date) {
        boolean status = false;
        int count = 1;
        for (Appointment a : appointments) {
            if (a.returnDate().equals(date)) {
                System.out.println(count + ") " + a.returnDescription() + " " + a.returnDate() + " " + a.returnBeginningTime() + " " + a.returnEndingTime());
                status = true;
                count++;
            }
        }

        if (!status) {
            System.out.println("No appointments exist on that date.");
        }
    }

    /**
     * Asks the user for a date
     * @return - date of type String
     */
    public String getDate() {

        System.out.print("Enter the date in this format (MM/DD/YY): ");
        String theDate = scanner.next();


        while (!verifyDate(theDate)) {
            System.out.println("Something was wrong with the date entered. Please re-enter the date.");
            theDate = scanner.next();
        }
        return theDate;
    }

    /**
     * Checks to see if date entered is valid
     * @param theDate  - date entered
     * @return - true if date is valid, false if invalid
     */
    public boolean verifyDate(String theDate) {
        boolean status = true;

        try {
            String numbers[] = theDate.split("/");
            if (numbers.length != 3) {
                status = false;
            }
            int count = 0;
            for (int i = 0; i < numbers.length; i++) {
                if (i == 0) {
                    if (Integer.parseInt(numbers[i]) < 1 || (Integer.parseInt(numbers[i]) > 12)) {
                        status = false;
                    }
                } else if (i == 1) {
                    if ((Integer.parseInt(numbers[i]) < 1 || (Integer.parseInt(numbers[i]) > 31))) {
                        status = false;
                    }
                } else if (i == 2) {
                    if ((Integer.parseInt(numbers[i]) <= 2019)) {
                        status = false;
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Something was wrong with the date entered. Please re-enter the date.");
        }
        return status;


    }


    /**
     * Retrieves the time from the user
     * @return - the time in the form of a String
     */
    public String getTime() {
        System.out.print("Enter the time in this format (HH:MM): ");
        String theTime = scanner.next();

        while (!verifyTime(theTime)) {
            System.out.print("Something was wrong with the time entered. Please re-enter the time.");
            theTime = scanner.next();
        }
        return theTime;
    }

    /**
     * Checks to see if the time entered is valid
     * @param theTime - time entered
     * @return - true if valid, false if invalid
     */
    public boolean verifyTime(String theTime) {
        boolean status = true;
        try {
            String timeComponents[] = theTime.split(":");
            if (timeComponents.length != 2) {
                status = false;
            }
            for (int i = 0; i < timeComponents.length; i++) {
                if (i == 0) {
                    if (Integer.parseInt(timeComponents[i]) > 23 || Integer.parseInt(timeComponents[i]) < 0)
                        status = false;
                } else if (i == 1) {
                    if (Integer.parseInt(timeComponents[i]) > 59 || Integer.parseInt(timeComponents[i]) < 0)
                        status = false;
                }
            }
        } catch (NumberFormatException e) {
            // System.out.println("Something was wrong with the time entered. Please re-enter the time.");
        }
        return status;
    }

    /**
     * Asks user for description to appointment
     * @return - description of appointment
     */
    public String getDescription() {
        Scanner read = new Scanner(System.in);
        System.out.print("Enter the description: ");
        String theDescription = read.nextLine();
        return theDescription;

    }

    /**
     * Checks if appointment exists in the list
     * @param date - the date
     * @return - false if no appointments on date exist, true if at least one appointment exists on that date
     */
    public boolean checkIfAppointmentExists(String date) {

        boolean status = false;

        for (Appointment a : appointments) {
            if (a.returnDate().equals(date)) {
                status = true;
            }
        }
        return status;
    }

    /**
     * Checks if appointment that the user enters is valid
     * @param anAppointment - the appointment the user made
     * @return - false if appointment is invalid, true if valid
     */
    public boolean checkIfValid(Appointment anAppointment) {
        boolean status = true;

        for (Appointment a : appointments) {
            if ((a.returnDescription().equals(anAppointment.returnDescription())
                    && a.returnDate().equals(anAppointment.returnDate())
                    && a.returnBeginningTime().equals(anAppointment.returnBeginningTime())
                    && a.returnEndingTime().equals(anAppointment.returnEndingTime())))
//                    ||
//            (!compareTimes(a.returnEndingTime(),anAppointment.returnBeginningTime())))
            {
                status = false;

            }


            else if (a.returnDate().equals(anAppointment.returnDate())){
                if (!compareTimes(a.returnEndingTime(), anAppointment.returnBeginningTime())){
                    status = false;
                }
            }
        }
        return status;
    }

    public boolean compareTimes(String firstA, String secondA) {
        boolean status = true;
        String[] firstAComponent = firstA.split(":");
        String[] secondAComponent = secondA.split(":");
        int firstAMinutes = Integer.parseInt(firstAComponent[0]) * 60 + Integer.parseInt(firstAComponent[1]);
        int secondAMinutes = Integer.parseInt(secondAComponent[0]) * 60 + Integer.parseInt(secondAComponent[1]);
//        System.out.println("First appointment beginning time: " + firstAMinutes);
//        System.out.println("Second appointment beginning time: " + secondAMinutes);
        if (firstAMinutes > secondAMinutes){
            status = false;
        }
        return status;
    }

    /**
     * Main menu
     */
    public void menu() {

        boolean runAgain = true;

        while (runAgain) {

            System.out.println("This program add appointments, remove appointments, or print all appointments on a date.");
            System.out.print("Enter 1 to add appointments, 2 to remove appointments, 3 to display appointments on a date: ");

            char choice = scanner.next().charAt(0);


            if (choice == '1' || choice == '2' || choice == '3') {

                if (choice == '1') {

                    Appointment newAppointment = new Appointment(getDescription(), getDate(), getTime(), getTime());

                    while (!checkIfValid(newAppointment)) {

                        System.out.println("Unable to make appointment.It " +
                                "already exists or overlaps with an existing appointment! Please re-enter the appointment");
                        newAppointment = new Appointment(getDescription(), getDate(), getTime(), getTime());
                    }
                    addAppointment(newAppointment);

                } else if (choice == '2') {
                    String date = getDate();

                    int userPick = 0;

                    while (!checkIfAppointmentExists(date)) {
                        System.out.println("No appointments exist on that date. Please re-enter a different date.");
                        date = getDate();
                    }

                    showAppointments(date);

                    while (userPick == 0 || indexCounter(date) == 0 || userPick > indexCounter(date)) {
                        System.out.print("Please pick a appointment to remove: ");
                        userPick = scanner.nextInt();
                    }
                    removeAppointment(userPick, date);

                } else {
                    showAppointments(getDate());
                }


                System.out.print("Do you want to run this program again?(Y/y) ");
                char pick = scanner.next().charAt(0);
                count = 0;

                if (!(Character.toUpperCase(pick) == 'Y'))
                    runAgain = false;
            }
        }
    }

    /**
     * Helper method for removeAppointment
     * @param date - the date
     * @return - count
     */
    public int indexCounter(String date) {
        int count = 0;

        for (Appointment a : appointments) {
            if (a.returnDate().equals(date)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        AppointmentCalendar newCalendar = new AppointmentCalendar();
        newCalendar.startingAppointments();
        newCalendar.menu();
    }
}

