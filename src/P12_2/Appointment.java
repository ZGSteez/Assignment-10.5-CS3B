package P12_2;

public class Appointment {
    private String description;
    private String date;
    private String beginningTime;
    private String endingTime;

    public Appointment(String description, String date, String beginningTime, String endingTime){
        this.description = description;
        this.date = date;
        this.beginningTime = beginningTime;
        this.endingTime = endingTime;
    }

    public String returnDescription(){
        return description;
    }

    public String returnDate(){
        return date;
    }

    public String returnBeginningTime(){
        return this.beginningTime;
    }

    public String returnEndingTime(){
        return this.endingTime;
    }
}
