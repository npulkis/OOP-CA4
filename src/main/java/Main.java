import java.util.*;

public class Main {

   ArrayList<Fighter> fighters;
   Map<String,Fighter> fighterHashMap;
   Map<String,Fighter> fighterTreeMap;

   public static void main(String[] args) {
      Main app = new Main();
      app.start();
   }

   public void start(){
      Fighter fighter1 = new Fighter("Israel Adesanya",22,1);
      Fighter fighter2 = new Fighter("Conor McGregor",22,6);
      Fighter fighter3 = new Fighter("Dustin Poirier",28,7);
      Fighter fighter4 = new Fighter("Charles Oliveira", 32,8);
      Fighter fighter5 = new Fighter("Justin Gaethje",23,3);
      Fighter fighter6 = new Fighter("Kamaru Usman",20,1);
      Fighter fighter7 = new Fighter("Derrick Lewis",26,9);
      Fighter fighter8 = new Fighter("Amanda Nunes",21,5);
      Fighter fighter9 = new Fighter("Holly Holm",14,5);
      Fighter fighter10 = new Fighter("Jorge Masvidal",35,16);


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
      fighterHashMap.put("I.A",fighter1);
      fighterHashMap.put("C.MG",fighter2);
      fighterHashMap.put("D.P",fighter3);
      fighterHashMap.put("C.O",fighter4);
      fighterHashMap.put("J.G",fighter5);
      fighterHashMap.put("K.U",fighter6);
      fighterHashMap.put("D.L",fighter7);
      fighterHashMap.put("A.N",fighter8);
      fighterHashMap.put("H.H",fighter9);
      fighterHashMap.put("J.M",fighter10);


      fighterTreeMap = new TreeMap<>(new ComparatorTreeMapKey());
      fighterTreeMap.put("Israel",fighter1);
      fighterTreeMap.put("Conor",fighter2);
      fighterTreeMap.put("Dustin",fighter3);
      fighterTreeMap.put("Charles",fighter4);
      fighterTreeMap.put("Justin",fighter5);
      fighterTreeMap.put("Kamaru",fighter6);
      fighterTreeMap.put("Derrick",fighter7);
      fighterTreeMap.put("Amanda",fighter8);
      fighterTreeMap.put("Holly",fighter9);
      fighterTreeMap.put("Jorge",fighter10);


      displayMainMenu();
   }

   public void displayMainMenu() {

      final String MENU_ITEMS = "\n*** Main Menu ***\n"
              + "1. Display all ArrayList elements\n"
              + "2. Find object by key from HashMap\n"
              + "3. Display TreeMap\n"
              + "4. Exit\n"
              + "Enter Option [1,2,3]\n";

      final int DISPLAY_ARRAYLIST = 1;
      final int FIND_OBJECT_HASHMAP = 2;
      final int DISPLAY_TREEMAP = 3;
      final int EXIT = 4;

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
                  System.out.println("Find object by key from hashmap option chosen");
                  findObjectFromHashmap(fighterHashMap);
                  break;
               case DISPLAY_TREEMAP:
                  System.out.println("Display Treemap option chosen.");
                  displayTreeMap(fighterTreeMap);
               case EXIT:
                  System.out.println("Exit menu option chosen");
                  break;
               default:
                  System.out.println("Invalid Option - please enter number in range");
                  break;
            }

         } catch (InputMismatchException | NumberFormatException e) {
            System.out.print("Invalid option - please enter number in range");
         }
      } while (option != EXIT);

      System.out.println("\nExiting Main Menu, goodbye.");
   }

   public void displayArrayList(ArrayList<Fighter> fighters){

      for (Fighter fighter : fighters) {
         System.out.println(fighter);
      }
      promptEnterKey();
   }

   public void findObjectFromHashmap(Map<String,Fighter> hashMap){
      System.out.println("Enter Initials of Fighter (e.g Joe Bloggs = 'J.B' ) :");

      Scanner keyboard = new Scanner(System.in);
      String input = keyboard.nextLine().toUpperCase(Locale.ROOT);

      if (hashMap.get(input) == null){
         System.out.println("Object with provided key not found");
      }else {
         System.out.println(hashMap.get(input));
      }
      promptEnterKey();
   }

   public void displayTreeMap(Map<String,Fighter> treeMap) {

      for (Map.Entry<String, Fighter> entry : treeMap.entrySet()) {
         System.out.println("Key: "+entry.getKey()+", Value: "+entry.getValue());
      }
      promptEnterKey();
   }
   public void promptEnterKey(){
      System.out.println("\nPress \"ENTER\" to continue...");
      Scanner scanner = new Scanner(System.in);
      scanner.nextLine();
   }

   }
