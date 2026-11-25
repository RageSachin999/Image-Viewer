import java.util.Scanner;

// Node class
class ImageNode {
    String imagePath;
    ImageNode prev, next;

    ImageNode(String path) {
        imagePath = path;
        prev = next = null;
    }
}

// Image Viewer using Doubly Linked List
class DoublyLinkedList {

    ImageNode head, tail, current;
    int size = 0;

    // Add image
    void addImage(String path) {

        if (path == null || path.trim().isEmpty()) {
            System.out.println("Invalid image path!");
            return;
        }

        ImageNode newNode = new ImageNode(path);

        if (head == null) {
            head = tail = current = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
        System.out.println("Image added successfully!");
    }

    // View current image
    void viewImage() {

        if (current == null) {
            System.out.println("No image available!");
        } else {
            System.out.println("Viewing Image: " + current.imagePath);
            System.out.println("Position: " + getCurrentPosition()
                    + "/" + size);
        }
    }

    // Next image
    void nextImage() {

        if (current == null || current.next == null) {
            System.out.println("No next image!");
            return;
        }

        current = current.next;
        viewImage();
    }

    // Previous image
    void previousImage() {

        if (current == null || current.prev == null) {
            System.out.println("No previous image!");
            return;
        }

        current = current.prev;
        viewImage();
    }

    // First image
    void firstImage() {

        if (head == null) {
            System.out.println("No images available!");
            return;
        }

        current = head;
        viewImage();
    }

    // Last image
    void lastImage() {

        if (tail == null) {
            System.out.println("No images available!");
            return;
        }

        current = tail;
        viewImage();
    }

    // Delete current image
    void deleteImage() {

        if (current == null) {
            System.out.println("No image to delete!");
            return;
        }

        ImageNode temp = current;

        if (temp.prev != null)
            temp.prev.next = temp.next;
        else
            head = temp.next;

        if (temp.next != null)
            temp.next.prev = temp.prev;
        else
            tail = temp.prev;

        current = (temp.next != null)
                ? temp.next : temp.prev;

        size--;

        if (size == 0)
            head = tail = current = null;

        System.out.println("Image deleted successfully!");
    }

    // Display all images
    void displayAll() {

        if (head == null) {
            System.out.println("Image viewer is empty!");
            return;
        }

        ImageNode temp = head;
        int i = 1;

        System.out.println("\nAll Images:");

        while (temp != null) {
            System.out.println(i + ". " + temp.imagePath);
            temp = temp.next;
            i++;
        }
    }

    // Total images
    void totalImages() {
        System.out.println("Total Images: " + size);
    }

    // Current position
    int getCurrentPosition() {

        int position = 1;
        ImageNode temp = head;

        while (temp != null) {

            if (temp == current)
                return position;

            temp = temp.next;
            position++;
        }

        return 0;
    }
}

// Main class
public class ImageViewer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DoublyLinkedList viewer = new DoublyLinkedList();

        int choice;

        do {
            System.out.println("\n===== IMAGE VIEWER =====");
            System.out.println("1. Add Image");
            System.out.println("2. View Current Image");
            System.out.println("3. Next Image");
            System.out.println("4. Previous Image");
            System.out.println("5. First Image");
            System.out.println("6. Last Image");
            System.out.println("7. Delete Current Image");
            System.out.println("8. Display All Images");
            System.out.println("9. Total Images");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter image path: ");
                    String path = sc.nextLine();
                    viewer.addImage(path);
                    break;

                case 2:
                    viewer.viewImage();
                    break;

                case 3:
                    viewer.nextImage();
                    break;

                case 4:
                    viewer.previousImage();
                    break;

                case 5:
                    viewer.firstImage();
                    break;

                case 6:
                    viewer.lastImage();
                    break;

                case 7:
                    viewer.deleteImage();
                    break;

                case 8:
                    viewer.displayAll();
                    break;

                case 9:
                    viewer.totalImages();
                    break;

                case 0:
                    System.out.println("Exiting Image Viewer...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}
