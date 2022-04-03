import DAOs.FighterDaoInterface;
import DAOs.MySqlFighterDao;
import DTOs.Fighter;
import Exceptions.DaoException;

import java.util.*;

public class Main {

    FighterDaoInterface FighterDao;
    ArrayList<Fighter> fighters;
    Map<String, Fighter> fighterHashMap;
    Map<String, Fighter> fighterTreeMap;
    PriorityQueue<Fighter> fighterQueue;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    public void start() {
        Fighter fighter1 = new Fighter("Israel Adesanya", 22, 1);
        Fighter fighter2 = new Fighter("Conor McGregor", 22, 6);
        Fighter fighter3 = new Fighter("Dustin Poirier", 28, 7);
        Fighter fighter4 = new Fighter("Charles Oliveira", 32, 8);
        Fighter fighter5 = new Fighter("Justin Gaethje", 23, 3);
        Fighter fighter6 = new Fighter("Kamaru Usman", 20, 1);
        Fighter fighter7 = new Fighter("Derrick Lewis", 26, 9);
        Fighter fighter8 = new Fighter("Amanda Nunes", 21, 5);
        Fighter fighter9 = new Fighter("Holly Holm", 14, 5);
        Fighter fighter10 = new Fighter("Jorge Masvidal", 35, 16);


        fighters = new ArrayList<>();
        fighters.add(fighter1);
        fighters.add(fighter2);
        fighters.add(fighter3);
        fighters.add(fighter4);
        fighters.add(fighter5);
        fighters.add(fighter6);
        fighters.add(fighter7);
        fighters.add(fighter8);
        fighters.add(fighter9);
        fighters.add(fighter10);


        fighterHashMap = new HashMap<>();
        fighterHashMap.put("I.A", fighter1);
        fighterHashMap.put("C.MG", fighter2);
        fighterHashMap.put("D.P", fighter3);
        fighterHashMap.put("C.O", fighter4);
        fighterHashMap.put("J.G", fighter5);
        fighterHashMap.put("K.U", fighter6);
        fighterHashMap.put("D.L", fighter7);
        fighterHashMap.put("A.N", fighter8);
        fighterHashMap.put("H.H", fighter9);
        fighterHashMap.put("J.M", fighter10);


        fighterTreeMap = new TreeMap<>(new ComparatorTreeMapKey());
        fighterTreeMap.put("Israel", fighter1);
        fighterTreeMap.put("Conor", fighter2);
        fighterTreeMap.put("Dustin", fighter3);
        fighterTreeMap.put("Charles", fighter4);
        fighterTreeMap.put("Justin", fighter5);
        fighterTreeMap.put("Kamaru", fighter6);
        fighterTreeMap.put("Derrick", fighter7);
        fighterTreeMap.put("Amanda", fighter8);
        fighterTreeMap.put("Holly", fighter9);
        fighterTreeMap.put("Jorge", fighter10);

        fighterQueue = new PriorityQueue<>();
        for (Fighter fighter : fighters) {
            fighterQueue.add(fighter);
        }

        FighterDao = new MySqlFighterDao();

        displayMainMenu();
    }

    public void displayMainMenu() {

        final String MENU_ITEMS = "\n*** Main Menu ***\n"
                + "1. Display all ArrayList elements\n"
                + "2. Find object by key from HashMap\n"
                + "3. Display TreeMap\n"
                + "4. PriorityQueue Sequence\n"
                + "5. Display PriorityQueue \n"
                + "6. Display All from Database\n"
                + "7. Find fighter by id \n"
                + "8. Delete fighter by id \n"
                + "9. Add to Database\n"
                + "10. Show all with positive win rate\n"
                + "11. Find all as JSON\n"
                + "12. Find by id as JSON\n"
                + "13. Exit\n"
                + "Enter Option [1-13]\n";

        final int DISPLAY_ARRAYLIST = 1;
        final int FIND_OBJECT_HASHMAP = 2;
        final int DISPLAY_TREEMAP = 3;
        final int PRIORITY_QUEUE = 4;
        final int DISPLAY_QUEUE = 5;
        final int DISPLAY_ALL_DB = 6;
        final int FIND_BY_ID = 7;
        final int DELETE_BY_ID = 8;
        final int ADD_TO_DB = 9;
        final int POSITIVE_RATIO = 10;
        final int FIND_ALL_JSON = 11;
        final int FIND_BY_ID_JSON = 12;
        final int EXIT = 13;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String userInput = keyboard.nextLine();
                option = Integer.parseInt(userInput);

                switch (option) {
                    case DISPLAY_ARRAYLIST:
                        System.out.println("Display all ArrayList elements chosen\n");
                        displayArrayList(fighters);
                        break;
                    case FIND_OBJECT_HASHMAP:
                        System.out.println("Find object by key from hashmap option chosen.\nType 'exit' to return to main menu");
                        findObjectFromHashmap(fighterHashMap);
                        break;
                    case DISPLAY_TREEMAP:
                        System.out.println("Display Treemap option chosen.");
                        displayTreeMap(fighterTreeMap);
                        break;
                    case PRIORITY_QUEUE:
                        System.out.println("PriorityQueue option chosen");
                        PriorityQueue();
                        promptEnterKey();
                        break;
                    case DISPLAY_QUEUE:
                        System.out.println("Display PriorityQueue chosen");
                        displayPriorityQueue(fighterQueue);
                        promptEnterKey();
                    case DISPLAY_ALL_DB:
                        System.out.println("Display all from db");
                        displayAllFromDB(FighterDao);
                        promptEnterKey();
                        break;
                    case FIND_BY_ID:
                        System.out.println("Find by id chosen\n Type 'exit' to return to menu");
                        findByIdFromDB(FighterDao);
                        promptEnterKey();
                        break;
                    case DELETE_BY_ID:
                        System.out.println("Delete by id chosen \n Type 'exit' to return to menu");
                        deleteByIdFromDB(FighterDao);
                        promptEnterKey();
                        break;
                    case ADD_TO_DB:
                        System.out.println("Add to db chosen");
                        addToDB(FighterDao);
                        promptEnterKey();
                        break;
                    case POSITIVE_RATIO:
                        displayPositiveRatioFromDB(FighterDao);
                        promptEnterKey();
                        break;
                    case FIND_ALL_JSON:
                        System.out.println("All fighters in JSON format");
                        FighterDao.findAllAsJSON();
                        promptEnterKey();
                        break;
                    case FIND_BY_ID_JSON:
                        findByIdFromDBasJSON(FighterDao);
                        promptEnterKey();
                        break;
                    case EXIT:
                        System.out.println("Exit menu option chosen");
                        break;
                    default:
                        System.out.println("Invalid Option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException | DaoException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");
    }

    private void deleteByIdFromDB(FighterDaoInterface FighterDao) throws DaoException {

        Scanner keyboard = new Scanner(System.in);
        int id;
        boolean check = false;

        while (true) {
            System.out.print("Enter ID: ");


            while (!keyboard.hasNextInt()) {

                String input = keyboard.next();
                if (checkExit(input)) {
                    check = true;
                    break;
                }
                System.out.println("Invalid input! - Please enter only int values");
                System.out.print("Enter ID: ");
            }
            if (check) {
                break;
            }
            id = keyboard.nextInt();


            if (FighterDao.deleteFighterByID(id)) {
                System.out.println("Fighter with id=" + id + " deleted from database");
                break;
            }

        }


    }

    public void displayArrayList(ArrayList<Fighter> fighters) {

        for (Fighter fighter : fighters) {
            System.out.println("DTOs.Fighter:(Name: " + fighter.getName() + ", Wins: " + fighter.getWins() + ", Losses: " + fighter.getLosses() +
                    ", Total Fights: " + fighter.getTotalFights() + ", Win/Loss ration: " + fighter.getWinLoseRatio() + " )");
        }
        promptEnterKey();
    }

    public void findObjectFromHashmap(Map<String, Fighter> hashMap) {

        System.out.print("Enter Initials of DTOs.Fighter (e.g Joe Bloggs = 'J.B' ) :");
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine().toUpperCase(Locale.ROOT);

        if (checkExit(input)) {
            return;
        }

        if (hashMap.get(input) == null) {
            System.out.println("Object with provided key not found");
        } else {
            System.out.println(hashMap.get(input));
        }
        promptEnterKey();
    }

    public void displayTreeMap(Map<String, Fighter> treeMap) {

        for (Map.Entry<String, Fighter> entry : treeMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        promptEnterKey();
    }

    public void promptEnterKey() {
        System.out.println("\nPress \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void PriorityQueue() {
        PriorityQueue<Fighter> priorityQueue = new PriorityQueue<>();

        //two third priority
        priorityQueue.add(new Fighter("DTOs.Fighter 1", 40, 10));
        priorityQueue.add(new Fighter("DTOs.Fighter 2", 40, 7));

        //two second priority

        priorityQueue.add(new Fighter("DTOs.Fighter 3", 50, 12));
        priorityQueue.add(new Fighter("DTOs.Fighter 4", 50, 17));

        //remove and display one

        System.out.println("Remove one and display");
        System.out.println(priorityQueue.remove() + "\n");

        //add one top priority
        priorityQueue.add(new Fighter("DTOs.Fighter 5", 60, 5));

        //remove and display all elements in priority order

        System.out.println("Display ALL");
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.remove());
        }

    }

    public void displayPriorityQueue(PriorityQueue priorityQueue) {

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.remove());
        }

    }

    public void displayAllFromDB(FighterDaoInterface FighterDao) {
        try {

            List<Fighter> fighters = FighterDao.findAllFighters();


            if (fighters.isEmpty()) {
                System.out.println("No fighters found");
            } else {
                for (Fighter fighter : fighters) {
                    System.out.println(fighter.toString());
                }
            }

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void findByIdFromDB(FighterDaoInterface FighterDao) throws DaoException {


        Scanner keyboard = new Scanner(System.in);

        int id;
        boolean check = false;

        while (true) {
            System.out.print("Enter ID: ");

            while (!keyboard.hasNextInt()) {
                String input = keyboard.next();

                if (checkExit(input)) {
                    check = true;
                    break;
                }
                System.out.println("Invalid input! - Please enter only int values");
                System.out.print("Enter ID: ");
            }
            if (check) {
                break;
            }
            id = keyboard.nextInt();

            if (FighterDao.findFighterByID(id) != null) {
                System.out.println("Fighter found: " + FighterDao.findFighterByID(id));
                break;
            }


            System.out.println("Fighter with that ID not found");
        }


    }

    void addToDB(FighterDaoInterface FighterDao) throws DaoException {

        boolean check = false;
        String name;
        int wins = 0;
        int losses = 0;
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter name of fighter");
        name = keyboard.nextLine();


        while (true) {
            System.out.print("Enter wins: ");

            while (!keyboard.hasNextInt()) {
                String input = keyboard.next();

                if (checkExit(input)) {
                    check = true;
                    break;
                }
                System.out.println("Invalid input! - Please enter only int values");
                System.out.print("Enter wins: ");
            }
            if (check) {
                break;
            }
            wins = keyboard.nextInt();
            break;
        }

        while (true) {
            System.out.print("Enter losses: ");

            while (!keyboard.hasNextInt()) {
                String input = keyboard.next();

                if (checkExit(input)) {
                    check = true;
                    break;
                }
                System.out.println("Invalid input! - Please enter only int values");
                System.out.print("Enter losses: ");
            }
            if (check) {
                break;
            }
            losses = keyboard.nextInt();
            break;
        }


        FighterDao.addFighterToDB(name, wins, losses);

        System.out.println("Fighter added.");

    }

    public void displayPositiveRatioFromDB(FighterDaoInterface FighterDao) {
        try {

            List<Fighter> fighters = FighterDao.findAllPositiveRatio();

            Collections.sort(fighters,new RatioComparator());

            if (fighters.isEmpty()) {
                System.out.println("No fighters found");
            } else {
                for (Fighter fighter : fighters) {
                    System.out.println(fighter.toString());
                }
            }

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    boolean checkExit(String input) {

        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Exiting current menu");
            return true;
        }

        return false;
    }


    public void findByIdFromDBasJSON(FighterDaoInterface FighterDao) throws DaoException {


        Scanner keyboard = new Scanner(System.in);

        int id;
        boolean check = false;

        while (true) {
            System.out.print("Enter ID: ");

            while (!keyboard.hasNextInt()) {
                String input = keyboard.next();

                if (checkExit(input)) {
                    check = true;
                    break;
                }
                System.out.println("Invalid input! - Please enter only int values");
                System.out.print("Enter ID: ");
            }
            if (check) {
                break;
            }
            id = keyboard.nextInt();

            if (FighterDao.findFighterByID(id) != null) {
                System.out.println("Fighter found as JSON\n " + FighterDao.findByIdAsJSON(id));
                break;
            }


            System.out.println("Fighter with that ID not found");
        }


    }

}
