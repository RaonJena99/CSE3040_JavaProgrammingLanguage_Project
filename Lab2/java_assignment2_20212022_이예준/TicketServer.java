import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class TicketServer {
    private static final List<Integer> availableSeats = new ArrayList<>();
    private static final Object lock = new Object();

    static {
        for (int i = 1; i <= 20; i++) {
            availableSeats.add(i);
        }
    }

    public static void main(String[] args) {
        System.out.println("Ticket Server started...");

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            ExecutorService executor = Executors.newFixedThreadPool(5); // 최대 5개의 클라이언트 처리
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");
                executor.execute(new TicketHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class TicketHandler implements Runnable {
        private final Socket clientSocket;

        public TicketHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                boolean seatBooked = false;

                while (!seatBooked) {
                    synchronized (lock) {
                        if(availableSeats.isEmpty())  out.println("No seats available.");
                        else out.println("Available seats" + availableSeats);

                        String seatRequest = in.readLine();
                        if (seatRequest == null) break;
                        int seatNumber = Integer.parseInt(seatRequest);

                        if (availableSeats.contains(seatNumber)) {
                            availableSeats.remove((Integer) seatNumber);
                            out.println("Seat " + seatNumber + " booked successfully! Remaining seats: " + availableSeats);
                            System.out.println("Seat " + seatNumber + " booked. Remaining: " + availableSeats);
                            seatBooked = true;
                        } else {
                            out.println("Seat " + seatNumber + " is already taken. Please choose another seat.");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                    System.out.println("Client disconnected.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
