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

        
        
        if (args.length == 2){
            
            if (args[1].equals("-breadth")){

                Baggins BFSSolutions = new Baggins(file,false);
        
                //Prints BFS Solutions
                BFSSolutions.BFS();
    
            }
            else if (args[1].equals("-depth")){            
    
                Baggins DFSSolutions = new Baggins(file,false);
                DFSSolutions.DFS();

            }
            else if (args[1].equals("-local")){
    
    
                //long startTime = System.nanoTime();
    
                Baggins LSSolution = new Baggins(file, false);
                LSSolution.LS();
    
                // long endTime = System.nanoTime();
                // System.out.println((endTime - startTime) / 1000000);
    
    
            }
            else if (args[1].equals("-slow")){
                
                Baggins MRVLCV = new Baggins(file, false);
                MRVLCV.MrvLcvDFS(false);
                
            }
        }
        else {

            Baggins MRVLCV = new Baggins(file, false);
            MRVLCV.MrvLcvDFS(true);
        }

    }
        else{
            System.out.println("failure");
		    System.exit(0);
        }
    }


}