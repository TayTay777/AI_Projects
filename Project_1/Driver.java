import java.io.File;


class Driver {

    public static void main(String[] args) {
        File file = new File(args[0]);



        Baggins BFSSolutions = new Baggins(file);

        //Prints BFS Solutions
        BFSSolutions.BFS();



    }

}