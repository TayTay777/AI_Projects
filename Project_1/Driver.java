import java.io.File;


class Driver {

    public static void main(String[] args) {

        File file = new File(args[0]);
        if(file.exists()){

        
        
        

        if (args[1].equals("-breadth")){

            Baggins BFSSolutions = new Baggins(file);
    
            //Prints BFS Solutions
            BFSSolutions.BFS();

        }
        else if (args[1].equals("-depth")){
            //code for dfs goes here
            Baggins DFSSolutions = new Baggins(file);
    
            //Prints BFS Solutions
            DFSSolutions.DFS();
        }
        else System.out.println("Error: Second argument must be \"-depth\" or \"-breadth\"");

    }
        else{
            System.out.println("failure");
		    System.exit(0);
        }
    }


}