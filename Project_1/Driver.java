import java.io.File;
/**
 * Driver class calles baggins to run a DFS or BFS
 * 
 * @author Taylor Roberts
 * @author Hailee Kiesecker
 */

class Driver {

    public static void main(String[] args) {

        File file = new File(args[0]);
        if(file.exists()){

        
        
        

        if (args[1].equals("-breadth")){

            Baggins BFSSolutions = new Baggins(file,false);
    
            //Prints BFS Solutions
            BFSSolutions.BFS();

        }
        else if (args[1].equals("-depth")){
            //code for dfs goes here
            Baggins DFSSolutions = new Baggins(file,false);
    
            //Prints BFS Solutions
            DFSSolutions.DFS();
        }
        else if (args[1].equals("-local")){
            Baggins LSSolution = new Baggins(file, false);

            LSSolution.LS();
        }
        else if (args[1].equals("-slow")){
            Baggins MRVSolution = new Baggins(file,false);
            MRVSolution.MRV();

        }

        else {
            Baggins arcSolution = new Baggins(file, true);
            arcSolution.MRV();
           
        }

    }
        else{
            System.out.println("failure");
		    System.exit(0);
        }
    }


}