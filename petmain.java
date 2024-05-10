import java.util.Scanner;

public class petmain {
    public static void main(String[] args) {
        PetDatabaseManager manager = new PetDatabaseManager("jdbc:mysql://localhost:3306/codechum", "root", "");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose what to do:");
            System.out.println("1.) Register Pet");
            System.out.println("2.) Retrieve Pet");
            System.out.println("3.) Vaccinate Pet");
            System.out.println("0.) Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.println("Enter Pet Details:");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Type: ");
                    String type = scanner.nextLine();
                    System.out.print("Proceed to save? (Y/n): ");
                    String proceed = scanner.nextLine();
                    if (proceed.equalsIgnoreCase("Y")) {
                        if (manager.registerPet(name, type)) {
                            System.out.println("Successfully saved!");
                        } else {
                            System.out.println("Something went wrong!");
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter Pet ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Consume newline character
                    System.out.print("Retrieve pet? (Y/n): ");
                    String retrieve = scanner.nextLine();
                    if (retrieve.equalsIgnoreCase("Y")) {
                        Pet pet = manager.retrievePet(id);
                        if (pet != null) {
                            System.out.println("Pet Details:");
                            System.out.println(pet.toString());
                        } else {
                            System.out.println("Pet not found!");
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter Pet ID to vaccinate: ");
                    int petId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline character
                    if (manager.vaccinatePet(petId)) {
                        System.out.println("The pet has been vaccinated!");
                    } else {
                        System.out.println("Failed to vaccinate the pet!");
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}
