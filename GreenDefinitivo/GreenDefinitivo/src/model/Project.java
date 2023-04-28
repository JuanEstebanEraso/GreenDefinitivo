package model;

import java.time.LocalDate;
import java.util.Calendar;
import model.Stage;
import java.time.ZoneId;
import model.Stage;

public class Project {

    private Stage[] stages = new Stage[NUM_STAGES];
    private static final int NUM_STAGES = 6;
    private String nameProject;
    private String nameClient;
    private String phoneClient;
    private double budget;
    private String projectManagerClient;
    private String projectManagerGreen;
    private Calendar startDate;
    private Calendar plannedEndDate;
    private Calendar actualEndDate;
    private int numCapsules;

    int[] stageDurations;

    public Project(String nameProject, String nameClient, String phoneClient, double budget,
                   String projectManagerGreen, String projectManagerClient, Calendar startDate,Calendar plannedEndDate
                  , int[] stageDurations) {
        this.nameProject = nameProject;
        this.nameClient = nameClient;
        this.phoneClient = phoneClient;
        this.budget = budget;
        this.projectManagerClient = projectManagerClient;
        this.projectManagerGreen = projectManagerGreen;
        this.startDate = startDate;
        this.plannedEndDate = calculatePlannedEndDate(startDate, stageDurations);
        this.numCapsules = 0; 
    
        stages[0] = new Stage("START", true);
        stages[0].setBeginDate(startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        stages[1] = new Stage("ANALYSIS", false);
        stages[2] = new Stage("DESIGN", false);
        stages[3] = new Stage("EXECUTION", false);
        stages[4] = new Stage("CLOSE", false);
        stages[5] = new Stage("MANAGEMENT", false);

    }
    
    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }


    public void setEndDate(Calendar plannedEndDate) {
    this.plannedEndDate = plannedEndDate;
    }

 
    
        public static Calendar calculatePlannedEndDate(Calendar startDate, int[] stageDurations) {
            Calendar plannedEndDate = (Calendar) startDate.clone();
            for (int i = 0; i < stageDurations.length; i++) {
                int duration = stageDurations[i];
                plannedEndDate.add(Calendar.MONTH, duration);
            }
            plannedEndDate.add(Calendar.MONTH, -1); 
            return plannedEndDate;
          }
    


          
        public Stage getActiveStage() {
            for (int i = 0; i < stages.length; i++) {
                Stage stage = stages[i];
                if (stage.isActive()) {
                    return stage;
                }
            }
            return null; 
        }
        // public Stage getNextStage() {
        //    int  i=0;
        //    Stage stage = stages[i];
        //     if (stage != null) {
        //         for ( ;i < stages.length; i++) {
        //             if (stages[i] == stage) {
        //                 Stage nextStage = stages[i+1];
        //                 return nextStage;
        //             }
        //         }
        //     }
        //     return null; 
        // }
        public Stage getNextStage() {
            int i = 0;
            Stage stage = stages[i];
            while (stage != null && !stage.isActive()) {
                i++;
                stage = stages[i];
            }
            return stage;
        }
        
        public void culminateActiveStage(Calendar actualEndDate) {
            Stage activeStage = getActiveStage();
            if (activeStage != null) {
                activeStage.setEndDate(actualEndDate);
                activeStage.setActive(false);
                activeStage.setApproved(true);
        
                Stage nextStage = getNextStage();
                if (nextStage != null) {
                    nextStage.setActive(true);
                    nextStage.setApproved(false);
                    
                    culminateActiveStage(actualEndDate);
    
                } else {
                  
                    System.out.println("Last stage of project. No further stages to activate.");
                }
            }
        }
        
      
    public String getNameProject() {
        return nameProject;
    }
    public String getNameClient() {
        return nameClient;
    }


    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }
    public String getPhoneClient() {
        return phoneClient;
    }


    public void setPhoneClient(String phoneClient) {
        this.phoneClient = phoneClient;
    }
    public double getBudget() {
        return budget;
    }


    public void setBudget(double budget) {
        this.budget = budget;
    }
    public String getProjectManagerClient() {
        return projectManagerClient;
    }


    public void setProjectManagerClient(String projectManagerClient) {
        this.projectManagerClient = projectManagerClient;
    }
    public String getProjectManagerGreen() {
        return projectManagerGreen;
    }


    public void setProjectManagerGreen(String projectManagerGreen) {
        this.projectManagerGreen = projectManagerGreen;
    }
    
    public Calendar getPlannedEndDate() {
        return plannedEndDate;
    }


    public void setPlannedEndDate(Calendar plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }
    
   

 
    public void countCapsulesByType() {
        int tecnicoCount = 0;
        int gestionCount = 0;
        int dominioCount = 0;
        int experienciasCount = 0;
    
        for (Stage stage : stages) {
            for (Capsule capsule : stage.getCapsules()) {
                switch (capsule.getType()) {
                    case TECHNICAL:
                        tecnicoCount++;
                        break;
                    case MANAGEMENT:
                        gestionCount++;
                        break;
                    case DOMAIN:
                        dominioCount++;
                        break;
                    case EXPERIENCES:
                        experienciasCount++;
                        break;
                    default:
                        // Error handling for invalid capsule type
                        break;
                }
            }
        }
    
        System.out.println("Cápsulas técnicas: " + tecnicoCount);
        System.out.println("Cápsulas de gestión: " + gestionCount);
        System.out.println("Cápsulas de dominio: " + dominioCount);
        System.out.println("Cápsulas de experiencias: " + experienciasCount);
    }

}    