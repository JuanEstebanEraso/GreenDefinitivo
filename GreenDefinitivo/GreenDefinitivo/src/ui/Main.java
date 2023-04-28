package ui;
import model.Company;
import model.Project;
import model.Stage;


import java.time.LocalDate;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import model.Capsule.CapsuleType;
import java.util.Scanner;
public class Main{
       public static void main(String[] args) {
        Company green =new Company();
        Scanner sn=new Scanner(System.in);
        boolean salir=false;
        int option;
        while(!salir){
            System.out.println("1.Create a Project");
            System.out.println("2.Culminating stage of a project");
            System.out.println("3.Project Info");
            System.out.println("4.Register capsule");
            System.out.println("4.approve capsule");
            System.out.println("5.post capsule");
            System.out.println("6.show created projects");
            System.out.println("7. Exit");


            System.out.println("enter the option you want");
            option=sn.nextInt();
            sn.nextLine(); 
                switch(option){
                    case 1:
                    createProject(green);
                    break;
                    case 2:
                    culminateStageProject(green);
                    break;
                    case 3:
                    System.out.println("Enter project name:");
                    String projectName = sn.nextLine();
                    green.showProjectInfo(green,projectName);
                    break;

                    case 4:
                    registerCapsule(green);
                    break;
                    case 5:
                    
                    break;
                    case 6:
                    green.showProjects();
                    break;
                    case 7:
                    
                    break;
                    case 8:
                   salir=true;
                   break;
                default:
                   System.out.println("Solo números entre 1 y 7");
                }

            System.out.println();
        }
        
    }


    public static void createProject(Company green) {
        String nameProject;
        String nameClient;
        String phoneClient;
        double budget;
        Calendar startDate;
        Calendar plannedEndDate;
        String projectManagerClient;
        String projectManagerGreen;
        int[] stageDurations = new int[6]; // arreglo para almacenar la duración de cada etapa
    
        Scanner sn = new Scanner(System.in);
        System.out.println("Enter the project name:");
        nameProject = sn.nextLine();
        System.out.println("Enter the client name:");
        nameClient = sn.nextLine();
        System.out.print("Enter the start date (yyyy-MM-dd): ");
        String startDateString = sn.nextLine();
        startDate = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate.setTime(dateFormat.parse(startDateString));
        } catch (ParseException e) {
            System.out.println("Invalid start date format. Please enter date in yyyy-MM-dd format.");
            return;
        }
    
        System.out.println("Enter the client phone:");
        phoneClient = sn.nextLine();
        System.out.println("Enter the budget:");
        budget = sn.nextDouble();
        System.out.println("Enter the client project manager:");
        projectManagerClient = sn.nextLine();
        sn.nextLine();
        System.out.println("Enter the GreenSQA project manager:");
        projectManagerGreen = sn.nextLine();
    
        // Pedir la duración de cada etapa
        for (int i = 1; i <= 6; i++) {
            System.out.println("Enter the duration of stage " + i + " (in months):");
            stageDurations[i - 1] = sn.nextInt();
        }
    
        plannedEndDate = calculatePlannedEndDate(startDate, stageDurations);
        if(green.validateProject(nameProject)){
            System.out.println(green.addProject(nameProject, nameClient, budget, projectManagerClient, projectManagerGreen, phoneClient, startDate, plannedEndDate, stageDurations));
        } else {
            System.out.println("The project already exists");
        }
    }
    
    public static Calendar calculatePlannedEndDate(Calendar startDate, int[] stageDurations) {
        Calendar plannedEndDate = (Calendar) startDate.clone();
        for (int i = 0; i < stageDurations.length; i++) {
            plannedEndDate.add(Calendar.MONTH, stageDurations[i]);
        }
        return plannedEndDate;
    }
    public static void culminateStageProject(Company green) {

        Scanner sn = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
        System.out.println("Enter the project name:");
        String projectName = sn.nextLine();
        Project project = green.getProject(projectName);
    
        if (project == null) {
            System.out.println("Project not found.");
        } else {
            // Obtener la etapa activa del proyecto
            Stage activeStage = project.getActiveStage();
    
            if (activeStage == null) {
                System.out.println("No active stage found.");
            } else {
                System.out.println("Active stage: " + activeStage.getName());
                System.out.println("Start date: " + activeStage.getBeginDate());
    
                System.out.println("Do you want to approve: "+ activeStage.getName()+ " the active stage? (y/n)");
                String answer = sn.nextLine();
    
                if (answer.equalsIgnoreCase("y")) {
                    System.out.println("Enter the actual end date of the stage (yyyy-MM-dd):");
                    String actualEndDateString = sn.nextLine();
                    Calendar actualEndDate = Calendar.getInstance();
                    try {
                        actualEndDate.setTime(dateFormat.parse(actualEndDateString));
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
                        return;
                    }

                    activeStage.setEndDate(actualEndDate);
                    activeStage.setActive(false);
    
                    activeStage.setApproved(true);
                    
                    // Activar la siguiente etapa y establecer su fecha de inicio
                    Stage nextStage = project.getNextStage();
                    if (nextStage != null) {
                        nextStage.setActive(true);
                       
                        nextStage.setBeginDate(activeStage.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        
                    }
                   
                    System.out.println("activeStage: " + activeStage);
                    System.out.println("nextStage: " + nextStage);
                    System.out.println("Stage culminated successfully.");
                } else {
                    System.out.println("Stage not approved.");
                }
            }
        }
    }


    public static void registerCapsule(Company green) {
        Scanner sn = new Scanner(System.in);
    
        System.out.println("Type the capsule's ID:");
        String id = sn.nextLine();
    
        System.out.println("Write a short description for the capsule:");
        String description = sn.nextLine();
    
        System.out.println("Choose the capsule's type:");
        int typeChoice = 0;
        CapsuleType type = null;
        while (typeChoice < 1 || typeChoice > 4) {
            System.out.println("Select the unit's type:\n1. Technical\n2. Management\n3. Domain\n4. Experiences");
            typeChoice = sn.nextInt();
            sn.nextLine(); // consume new line character
            switch (typeChoice) {
                case 1: 
                    type = CapsuleType.TECHNICAL;
                    break;
                case 2:
                    type = CapsuleType.MANAGEMENT;
                    break;
                case 3:
                    type = CapsuleType.DOMAIN;
                    break;
                case 4:
                    type = CapsuleType.EXPERIENCES;
                    break;
                default:
                    System.out.println("Invalid option. Please select a number from 1 to 4.");
                    break;
            }
        }
    
        System.out.println("Write the learned lesson:");
        String learnedLessons = sn.nextLine();
    
        System.out.println("Type the project name:");
        String projectName = sn.nextLine();
        Project project = green.getProject(projectName);
        if (project == null) {
            System.out.println("Project not found. Please check the name and try again.");
        }
        else{

        Stage stage = project.getActiveStage();
        
        if (stage == null) {
            System.out.println("No active stage found. Please activate a stage for the project first.");
            return;
        }
    
        if (stage.getNumCapsules() >= 50) {
            System.out.println("The stage already has the maximum number of capsules.");
            return;
        }
    
        if(stage.registerCapsule(id, description, type, learnedLessons)){
            System.out.println("The capsule was registered successfully!");
        } else {
            System.out.println("There was a problem with the capsule registration. Check system storage.");
        }
    }
}
}
    
