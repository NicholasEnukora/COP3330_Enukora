import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class App {
    public static void main(String[] ignored) throws ParseException, IOException, ClassNotFoundException {
        System.out.println("Main Menu");
        System.out.println("----------");
        System.out.println("1) create a new list");
        System.out.println("2) load an existing list");
        System.out.println("3) quit");

        App.TaskItem list = new App.TaskItem("/tmp/todos.list.db");

        while (true) {
            System.out.printf("> ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String argsString = reader.readLine();
            String[] args = argsString.split(" ");
            String firstArg = args[0];

            if ("1".equals(firstArg)) {
                listOperationMenu();
                firstOption(firstArg, args, list);
            } else if ("2".equals(firstArg)) {
                System.out.println("Enter the filename to load:");
            } else if ("3".equals(firstArg)) {
                System.exit(1);
            }
        }
    }

    public static void firstOption(String firstArg, String[] args, TaskItem list) throws IOException {

        while (true) {
            System.out.printf("> ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String argsString = reader.readLine();
            String[] argstwo = argsString.split(" ");
            String secondArg = argstwo[0];
            Scanner choice = new Scanner(System.in);

            if ("1".equals(secondArg)) {
                System.out.println(list.printList());
            } else if ("2".equals(secondArg)) {
                System.out.printf("Task ID: ");
                int id = choice.nextInt();
                System.out.printf("Task Title: ");
                String title = choice.nextLine();
                System.out.printf("Task Description: ");
                String description = choice.nextLine();
                System.out.printf("Task Due Date: ");
                String dueDate = choice.nextLine();
                list.AddItem(new App.TaskList(title), id, description, dueDate);
            } else if ("3".equals(secondArg)) {
                System.out.println(list.printList());
                System.out.print("Which task will you edit? ");
                String id = argstwo[2];
                System.out.println("Enter a new title for the task: ");
                String title = argstwo[3];
                System.out.println("Enter a new task description: ");
                String description = argstwo[4];
                System.out.println("Enter a new task due date (YYYY-MM-DD): ");
                String dueDate = argstwo[5];
            } else if ("4".equals(secondArg)) {
                System.out.println(list.printList());
                int id = Integer.parseInt(argstwo[1]);
                list.DeleteItem(id);
                System.out.println("Done deleting a todo id: " + id);
            } else if ("5".equals(secondArg)) {
                System.out.println(list.printList());
                System.out.println("Which task will you mark as completed?");
                int id = choice.nextInt();
            } else if ("6".equals(secondArg)) {
                System.out.println(list.printList());
                System.out.println("Which task will you unmark as completed?");
                int id = choice.nextInt();
            } else if ("7".equals(secondArg)) {
                list.save();
            } else if ("8".equals(secondArg)) {
                System.exit(1);
            }
        }
    }

    public static void listOperationMenu() {
        System.out.println("List Operation Menu");
        System.out.println("-------------------");
        System.out.println("1) view the list");
        System.out.println("2) add an item");
        System.out.println("3) edit an item");
        System.out.println("4) remove an item");
        System.out.println("5) mark an item as completed");
        System.out.println("6) unmark an item as completed");
        System.out.println("7) save the current list");
        System.out.println("8) quit to the main menu");
        System.out.printf("\n\n");
    }

    public static class TaskItem {

        private String saveLocation = "/tmp/todos.db";
        private TaskList root = new TaskList("");

        public TaskItem(String saveLocation) {
            this.saveLocation = saveLocation;
        }

        public TaskItem AddItem(TaskList todo, int id, String description, String dueDate) {
            if (id == 0) {
                root.add(todo);
            } else {
                root.findAndAddList(todo, id);
            }
            return this;
        }

        public TaskItem DeleteItem(int id) {
            if (id == 0) {
                System.out.printf("Can't delete the root");
                return this;
            } else {
                root.findAndDeleteTodo(id);
            }
            return this;
        }

        public String toString() {
            return root.toString();
        }

        public String printList() {
            return recursivePrint(root, 0);
        }

        private String recursivePrint(TaskList node, int level) {
            String result = "";
            String indent = "";
            for (int i = 0; i < level; i++) {
                indent += " ";
            }

            result += indent + "-" + node.getClass().getSimpleName() + "[id]=" + node.id + "\n";
            result += indent + "(todo): " + node.title + "\n";

            if (node.getClass() == TaskList.class) {
                for (Map.Entry<Integer, TaskList> n : ((TaskList) node).todos.entrySet()) {
                    result += recursivePrint(n.getValue(), level + 1);
                }
            }
            return result;
        }

        public TaskItem load() throws IOException, ClassNotFoundException {
            Path path = Paths.get(saveLocation);
            byte[] array = Files.readAllBytes(path);
            ByteArrayInputStream bis = new ByteArrayInputStream(array);
            ObjectInput in = null;

            try {
                in = new ObjectInputStream(bis);
                return (TaskItem) in.readObject();
            } finally {
                try {
                    bis.close();
                } catch (IOException ex) {
                }
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {
                }
            }
        }

        public void save() throws IOException {
            OutputStream file = new FileOutputStream(saveLocation);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = null;
            try {
                out = new ObjectOutputStream(bos);
                out.writeObject(this);
                file.write(bos.toByteArray());
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException ex) {
                }
                try {
                    bos.close();
                } catch (IOException ex) {
                }
            }
            file.close();
        }
    }

    public static class TaskList {
        public Map<Integer, TaskList> todos = new HashMap<Integer, TaskList>();
        public String title;
        private Integer id;

        public TaskList(String title) {
            super();
        }

        public TaskList add(TaskList todo) {
            todos.put(todo.id, todo);
            return this;
        }

        public TaskList delete(int id) {
            todos.remove(id);
            return this;
        }

        public TaskList findAndAddList(TaskList todo, int id) {
            if (this.id != id) {
                for (Map.Entry<Integer, TaskList> n : todos.entrySet()) {
                    if (n.getValue().getClass() == TaskList.class) {
                        ((TaskList) n.getValue()).findAndAddList(todo, id);
                    }
                }
            } else {
                add(todo);
            }

            return this;
        }

        public TaskList findAndDeleteTodo(int id) {
            if (todos.containsKey(id)) {
                todos.remove(id);
            } else {
                for (Map.Entry<Integer, TaskList> n : todos.entrySet()) {
                    if (n.getValue().getClass() == TaskList.class) {
                        ((TaskList) n.getValue()).findAndDeleteTodo(id);
                    }
                }
            }

            return this;
        }

        public String toString() {
            return todos.toString();
        }
    }
}