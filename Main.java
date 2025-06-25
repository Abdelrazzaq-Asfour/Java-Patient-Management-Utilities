import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientQueue patientQueue = new PatientQueue();

        System.out.print("Enter number of patients to add: ");
        int numPatients = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numPatients; i++) {
            System.out.print("Enter patient name: ");
            String name = scanner.nextLine();
            System.out.print("Enter disease details: ");
            String disease = scanner.nextLine();
            System.out.print("Enter urgency (lower = more urgent): ");
            int urgency = scanner.nextInt();
            scanner.nextLine();
            patientQueue.enqueue(name, disease, urgency);
        }

        while (true) {
            System.out.println("\n1. Add patient\n2. Serve patient\n3. Show queue\n4. Dose Calculator\n5. Sort Array\n6. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter patient name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter disease details: ");
                    String disease = scanner.nextLine();
                    System.out.print("Enter urgency (lower = more urgent): ");
                    int urgency = scanner.nextInt();
                    scanner.nextLine();
                    patientQueue.enqueue(name, disease, urgency);
                    break;
                case 2:
                    patientQueue.dequeue();
                    break;
                case 3:
                    patientQueue.display();
                    break;
                case 4:
                    runDoseCalculator(scanner);
                    break;
                case 5:
                    runArraySorting(scanner);
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void runDoseCalculator(Scanner scanner) {
        String repeat;
        do {
            System.out.print("Enter initial dose (mg): ");
            double dose = scanner.nextDouble();
            System.out.print("Enter decrease percentage per day: ");
            double decrease = scanner.nextDouble();
            int days = calculateDoseDays(dose, dose, decrease);
            System.out.println("Days until medication stops: " + days);
            System.out.print("Repeat? (y/n): ");
            repeat = scanner.next();
        } while (repeat.equalsIgnoreCase("y"));
    }

    private static int calculateDoseDays(double currentDose, double originalDose, double decrease) {
        if (currentDose <= 0) return 0;
        double newDose = currentDose - (decrease * originalDose);
        System.out.printf("New daily dose: %.2f mg\n", newDose);
        return 1 + calculateDoseDays(newDose, originalDose, decrease);
    }

    private static void runArraySorting(Scanner scanner) {
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            System.out.print("Enter value for element " + i + ": ");
            array[i] = scanner.nextInt();
        }

        System.out.println("Choose algorithm: 1) QuickSort 2) SelectionSort");
        int method = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Array format: a) Sorted b) Reverse c) Random d) Partially Sorted");
        String format = scanner.nextLine();
        switch (format.toLowerCase()) {
            case "a": SortUtils.selectionSort(array); break;
            case "b": SortUtils.reverse(array); break;
            case "c": SortUtils.randomize(array); break;
            case "d": SortUtils.partiallySorted(array); break;
        }

        if (method == 1) SortUtils.quickSort(array, 0, array.length - 1);
        else SortUtils.selectionSort(array);

        System.out.println("Sorted array: " + Arrays.toString(array));
    }
}

class Patient {
    String name, disease;
    int urgency;
    Patient next;

    public Patient(String name, String disease, int urgency) {
        this.name = name;
        this.disease = disease;
        this.urgency = urgency;
        this.next = null;
    }
}

class PatientQueue {
    private Patient head, tail;

    public void enqueue(String name, String disease, int urgency) {
        Patient newPatient = new Patient(name, disease, urgency);

        if (head == null) {
            head = tail = newPatient;
            tail.next = head;
        } else if (head.urgency > urgency) {
            newPatient.next = head;
            tail.next = newPatient;
            head = newPatient;
        } else if (tail.urgency <= urgency) {
            tail.next = newPatient;
            tail = newPatient;
            tail.next = head;
        } else {
            Patient current = head;
            while (current.next != head && current.next.urgency <= urgency) {
                current = current.next;
            }
            newPatient.next = current.next;
            current.next = newPatient;
        }
    }

    public void dequeue() {
        if (head == null) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("Serving: " + head.name);
        if (head == tail) head = tail = null;
        else {
            head = head.next;
            tail.next = head;
        }
    }

    public void display() {
        if (head == null) {
            System.out.println("Queue is empty.");
            return;
        }

        Patient temp = head;
        do {
            System.out.println("Name: " + temp.name + ", Disease: " + temp.disease + ", Urgency: " + temp.urgency);
            temp = temp.next;
        } while (temp != head);
    }
}

class SortUtils {
    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int pivot = arr[low + (high - low) / 2];
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++; j--;
            }
        }
        quickSort(arr, low, j);
        quickSort(arr, i, high);
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void reverse(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void randomize(int[] arr) {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void partiallySorted(int[] arr) {
        selectionSort(arr);
        if (arr.length >= 3) {
            int temp = arr[0];
            arr[0] = arr[2];
            arr[2] = temp;
        }
    }
}

