package model;

import java.time.LocalDate;
import java.util.Calendar;
import model.Capsule;
import model.Capsule.CapsuleType;

public class Stage {

    private String name;
    private boolean status;
    public boolean approved;
    private LocalDate beginDate;
    private Calendar endDate;
    private LocalDate actualendDate;
    private Calendar realBeginDate;
    private Calendar realendDate;
    private Capsule[] capsules;
    private int numCapsules=0;

 
    public Stage(String name, boolean status) {
        this.name = name;
        this.status = status;
    }
    // public boolean registerCapsule(String id, String description, CapsuleType type, String learnedLessons) {
    //     if (numCapsules >= 50) {
    //         return false;
    //     }
    //     capsules[numCapsules] = new Capsule(id, description,type, learnedLessons, learnedLessons, learnedLessons);
    //     numCapsules++;
    //     return true;
    // }
    public boolean registerCapsule(String id, String description, CapsuleType type, String learnedLesson) {
        if (numCapsules >= 50) {
            System.out.println("Cannot register more than 50 knowledge capsules in this stage.");
            return false;
        }
        Capsule capsule = new Capsule(id, description, type, learnedLesson);
        capsules[numCapsules] = capsule;
        numCapsules++;
        return true;
    }
   
    public boolean isActive() {
        return status;
    }

    public Capsule[] getCapsules() {
        return capsules;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate2) {
        this.beginDate = beginDate2;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public boolean getStatus(){
         return status;
    }
    public void setStatus(boolean status) {
        this.status=status;
    }

    public int getNumMonths() {
        return 0;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }


    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }
    public String getName() {
        return name;
    }
    public boolean isApproved() {
        return approved;
    }
    public int getNumCapsules() {
        return numCapsules;
    }
    public void setNumCapsules(int numCapsules) {
        this.numCapsules = numCapsules;
    }
    public void setActive(boolean active) {
        this.status = active;
    }
    
}
