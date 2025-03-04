import java.io.*;
import java.net.*;
import java.util.Random;

public class TicketClient {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            String clientName = "Client-" + i;
            new Thread(new ClientTask(clientName)).start();
        }
    }

    static class ClientTask implements Runnable {
        private final String clientName;

        public ClientTask(String clientName) {
            this.clientName = clientName;
        }

        @Override
        public void run() {
            try (Socket socket = new Socket("localhost", 8080);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
            ) {
                System.out.println(clientName + " connected to the server.");

                boolean seatBooked = false;

                while (!seatBooked) {
                    String serverResponse = in.readLine();
                    System.out.println(clientName + " received : " + serverResponse);

                    if (serverResponse.equals("No seats available.")) {
                        break;
                    }

                    // Step 2: Choose a random seat from the available seats
                    String[] availableSeats = serverResponse.replace("Available seats", "").replace("[", "").replace("]", "").split(", ");
                    int chosenSeat = Integer.parseInt(availableSeats[new Random().nextInt(availableSeats.length)]);

                    // Step 3: Send the chosen seat to the server
                    System.out.println(clientName + " chose seat " + chosenSeat);
                    out.println(chosenSeat);

                    // Step 4: Receive booking confirmation or error
                    String bookingResponse = in.readLine();
                    System.out.println(clientName + ": " + bookingResponse);

                    if (bookingResponse.contains("booked successfully")) {
                        seatBooked = true;
                    } else {
                        sleepRandomTime();
                    }
                }
            } catch (IOException e) {
                System.out.println(clientName + " could not connect to the server.");
                e.printStackTrace();
            }
        }

        private void sleepRandomTime() {
            try {
                int sleepTime = new Random().nextInt(6) + 5; // Random time between 5 and 10 seconds
                Thread.sleep(sleepTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
