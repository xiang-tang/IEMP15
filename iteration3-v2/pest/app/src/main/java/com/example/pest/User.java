package com.example.pest;

public class User implements Comparable{
    private String userName;
    private String userEmail;
    private int totalPoints;
    private int reportTimes;
    private int rank;


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getReportTimes() {
        return reportTimes;
    }

    public void setReportTimes(int reportTimes) {
        this.reportTimes = reportTimes;
    }

    public User(String userName, int totalPoints, int reportTimes) {
        this.userName = userName;
        this.totalPoints = totalPoints;
        this.reportTimes = reportTimes;
    }

    public User(String userName, String userEmail, int totalPoints) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.totalPoints = totalPoints;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        return user.totalPoints - this.totalPoints;
    }
}
