import java.util.ArrayList;
import java.util.Scanner;

public class Baggins {

    File file;


    public Baggins(File file) {
        this.file = file;
    }



    void DFS(){

    }


    void BFS(){

    }



    //TO DO: groceries class now needs to fit the 
    //new classes below: item, items, and sack
    public class groceries {

        vector<sack> sacks;
        ArrayList<Item> unpackedItems;

        public groceries(groceries s) {
            sacks = new Vector<Bag>();
            for (Sack i : s.sacks)
                sacks.add(new Sack(i));
            upackedItems = new ArrayList<Item>(s.unpackedItems);
        }

        public groceries() {
            unpackedItems = new ArrayList<Item>(items.values());
            sacks = new Vector<Bag>(numBags);
            for (int x = 0; x < numSacks; x++)
                sacks.add(new Bag(0));
        }

        //This will print one solution
        //the DFS or BFS will keep calling 
        //this for every solution
        void printGroceries(){
            //TO DO: Make a toString from the 
            //solution values
        }

    }

    public class item {

        String name;
        ArrayList<String> compatible = new ArrayList<String>();
        int itemSize;

        public item(String name, ArrayList<String> compatible, int itemSize) {
            this.name = name;
            this.compatible = compatible;
            this.itemSize = itemSize;
        }

        boolean checkCompatible(String item) {
            if (compatible.contains(item)) {
                return true;
            } else
                return false;
        }

    }

    public class items {

        ArrayList<item> items = new ArrayList<item>();
        File file;
        Scanner fileScan;

        public items(File file) {
            this.file = file;

            // initialize the file
            fileScan = new Scanner(file);

        }

        // @return: items arraylist

        ArrayList<item> createItems() {

            // Gets all the groceries items for reference
            ArrayList<String> baseItems = new ArrayList<String>();
            // Scans past the # of bags and max bag size
            fileScan.nextLine();
            fileScan.nextLine();

            while (fileScan.hasNextLine()) {
                baseItems.add(fileScan.next());
                nextLine();

            }
            fileScan.reset();

            // Scans past the # of bags and max bag size
            fileScan.nextLine();
            fileScan.nextLine();

            // scans over every item line each while iteration
            while (fileScan.hasNextLine()) {

                String itemName;
                ArrayList<String> compatible = new ArrayList<String>();
                int itemSize;

                String itemLine = fileScan.NextLine();
                Scanner itemLineScan = new Scanner(itemLine);
                boolean plus = false;
                boolean hasCons = false;

                itemName = itemLineScan.next();
                itemSize = itemLineScan.nextInt();
                if (itemLineScan.hasnext()) {
                    hasCons = true;
                    if (itemLineScan.next().equals("+")) {
                        plus = true;
                    }
                }

                if (hasCons) {

                    if (plus) {
                        while (itemLineScan.hasNext()) {
                            compatible.add(itemLineScan.next());
                        }
                    }

                    else {
                        compatible.addAll(baseItems);
                        while (itemLineScan.hasNext()) {
                            compatible.remove(itemLineScan.next());
                        }
                    }

                }

                item newItem = new item(itemName, compatible, itemSize);
                items.add(newItem);

            }
            return items;
        }

    }

    public class sack {

        int maxSize;
        int openSpace;
        ArrayList<item> contents = new ArrayList<item>();

        public sack(int maxSize) {
            this.maxSize = maxSize;
            openSpace = maxSize;
        }

        void addItem(item item) {
            contents.add(item);
            openSpace = openSpace - item.itemSize;
        }

    }

}
