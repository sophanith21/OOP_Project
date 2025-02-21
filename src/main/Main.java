// package src.main;

// import src.booking.Booking;
// import src.cinema.Cinema;
// import src.cinema.Movie;
// import src.cinema.ShowTime;
// import src.user.Authentication;
// import src.user.Customer;
// import src.user.User;

// import java.util.Scanner;

// public class Main {
//     private static User currentUser;
//     private static Cinema cinema;
    
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
        
//         // Ensure Cinema has a valid constructor
//         cinema = new Cinema();
        
//         login(scanner);
        
//         while (true) {
//             System.out.println("=== Cinema Management System ===");
//             System.out.println("1. Book a Ticket");
//             System.out.println("2. View Showtimes");
//             System.out.println("3. View Booked Tickets");
//             System.out.println("4. Cancel a Booking");
//             System.out.println("5. Logout");
//             System.out.println("6. Exit");
//             System.out.print("Enter your choice: ");
            
//             int choice = scanner.nextInt();
//             scanner.nextLine(); // Consume newline
            
//             switch (choice) {
//                 case 1:
//                     if (currentUser instanceof Customer) {
//                         bookTicket(scanner);
//                     } else {
//                         System.out.println("Only customers can book tickets.");
//                     }
//                     break;
//                 case 2:
//                     viewShowtimes();
//                     break;
//                 case 3:
//                     viewBookedTickets();
//                     break;
//                 case 4:
//                     if (currentUser instanceof Customer) {
//                         cancelBooking(scanner);
//                     } else {
//                         System.out.println("Only customers can cancel bookings.");
//                     }
//                     break;
//                 case 5:
//                     System.out.println("Logging out...");
//                     currentUser = null;
//                     login(scanner);
//                     break;
//                 case 6:
//                     System.out.println("Exiting system. Goodbye!");
//                     scanner.close();
//                     return;
//                 default:
//                     System.out.println("Invalid choice. Please try again.");
//             }
//         }
//     }

//     private static void login(Scanner scanner) {
//         while (currentUser == null) {
//             System.out.print("Enter username: ");
//             String username = scanner.nextLine();
//             System.out.print("Enter password: ");
//             String password = scanner.nextLine();
            
//             currentUser = Authentication.authenticate(username, password);
            
//             if (currentUser != null) {
//                 System.out.println("Login successful! Welcome, " + currentUser.getUsername());
//             } else {
//                 System.out.println("Invalid credentials. Please try again.");
//             }
//         }
//     }

//     private static void bookTicket(Scanner scanner) {
//         System.out.print("Enter movie name: ");
//         String movieName = scanner.nextLine();
//         Movie movie = cinema.findMovie(movieName);
        
//         if (movie != null) {
//             if (currentUser instanceof Customer) {
//                 try {
//                     Booking booking = new Booking((Customer) currentUser, movie);
//                     System.out.println("Ticket booked for " + movie.getTitle());
//                 } catch (Exception e) {
//                     System.out.println("Error booking ticket: " + e.getMessage());
//                 }
//             } else {
//                 System.out.println("Only customers can book tickets.");
//             }
//         } else {
//             System.out.println("Movie not found.");
//         }
//     }

//     private static void viewShowtimes() {
//         if (cinema != null) {
//             cinema.displayShowtimes();
//         } else {
//             System.out.println("No showtimes available.");
//         }
//     }

//     private static void viewBookedTickets() {
//         if (currentUser instanceof Customer) {
//             ((Customer) currentUser).viewBookings();
//         } else {
//             System.out.println("You do not have any bookings.");
//         }
//     }

//     private static void cancelBooking(Scanner scanner) {
//         if (currentUser instanceof Customer) {
//             System.out.print("Enter movie name to cancel: ");
//             String movieName = scanner.nextLine();
//             try {
//                 ((Customer) currentUser).cancelBooking(movieName);
//             } catch (Exception e) {
//                 System.out.println("Error canceling booking: " + e.getMessage());
//             }
//         } else {
//             System.out.println("You do not have any bookings.");
//         }
//     }
// }