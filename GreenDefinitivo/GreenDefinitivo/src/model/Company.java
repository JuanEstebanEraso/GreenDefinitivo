package model;

import java.time.LocalDate;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import model.Project;
import model.Stage;
import model.Capsule.CapsuleType;

public class Company {
  public final int MAX = 10;
  private Project[] projects = new Project[MAX];
  int[] stageDurations;

  public Company() {
      // Inicializar el arreglo de proyectos en null
      for (int i = 0; i < MAX; i++) {
          projects[i] = null;
      }
  }
    public String addProject(String nameProject, String nameClient, double budget,
        String projectManagerClient, String projectManagerGreen, String phoneClient,
        Calendar startDate, Calendar plannedEndDate, int[] stageDurations) {
    boolean termino = false;
    for (int i = 0; i < MAX ; i++) {
        if (projects[i] == null) {
            projects[i] = new Project(nameProject, nameClient, phoneClient, budget,
                    projectManagerGreen, projectManagerClient, startDate, startDate, stageDurations);
            termino = true;
            break;
        }
    }
    if (termino) {
        return "Project created successfully.";
    } else {
        return "Could not create project as there is no space.";
    }
}



    public boolean validateProject(String nameProject){
      boolean validate=true;
  
      for(int i=0;i<MAX && validate;i++){
        if(projects[i]!=null && projects[i].getNameProject().equals(nameProject)){
          validate=false;
        }
        
      }
      return validate; 

    }
          
    public void showProjects(){
      for(int i = 0; i < MAX; i++){
        if(projects[i] != null){
          System.out.println(projects[i].getNameProject());
        }
      }
    }
 
    public Project getProject(String projectName) {
      for (Project project : projects) {
          if (project.getNameProject().equals(projectName)) {
              return project;
          }
      }
      return null; // Project not found
  }
public static void showProjectInfo(Company company, String projectName) {
  Project project = company.getProject(projectName);
  if (project == null) {
      System.out.println("Project not found.");
  } else {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      System.out.println("Name: " + project.getNameProject());
      System.out.println("Client name: " + project.getNameClient());
      System.out.println("Client phone: " + project.getPhoneClient());
      System.out.println("Budget: " + project.getBudget());
      System.out.println("Green project manager: " + project.getProjectManagerGreen());
      System.out.println("Client project manager: " + project.getProjectManagerClient());
      System.out.println("Start date: " + dateFormat.format(project.getStartDate().getTime()));
      System.out.println("Planned end date: " + dateFormat.format(project.getPlannedEndDate().getTime()));
  }
}



public static Calendar calculatePlannedEndDate(Calendar startDate, int[] stageDurations) {
  Calendar plannedEndDate = (Calendar) startDate.clone();
  for (int i = 0; i < stageDurations.length; i++) {
      plannedEndDate.add(Calendar.MONTH, stageDurations[i]);
  }
  return plannedEndDate;
}



 
    public Project[] getProjects() {
        return projects;
    }
    
    
  
  }


