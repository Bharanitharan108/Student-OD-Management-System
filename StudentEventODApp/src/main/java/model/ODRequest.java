package model;

public class ODRequest {
    private final String name;
    private final String regNo;
    private final String department;
    private final String event;
    private final String date;
    private final String email;

    public ODRequest(String name, String regNo, String department, String event, String date, String email) {
        this.name = name;
        this.regNo = regNo;
        this.department = department;
        this.event = event;
        this.date = date;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getDepartment() {
        return department;
    }

    public String getEvent() {
        return event;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }
}
