Taylor Roberts
Checkers Project 
November 22, 2019


should cover how you developed your player, the things you tried, how you tested it, 
and detailing the final approach and heuristics you used, with a conclusion on how well it worked. 


***How to compile and Run***

To compile simple run make:
<user> $ make

To run there are some options available. In the example below

./checkers computer other_players/depth 3 -MaxDepth 5

"./checkers" runs the program, "computer" is player1, "other_players/depth" is player2,
'3' is the amount of secondes each player has to make, and "-MaxDepth 5" is how far the 
branch node levels go in the move decision tree, which is explained below under 
"Development of Player" Note, if you choose "computer" as a player, then player1 is the 
AI using the alpha-beta algorthim explained below. 

Here is the syntax for the command. 

./checkers player1 player2 SecPerMove [-MaxDepth x]

Note, "-MaxDepth x" does not need to be chosen


***Development of Player***

Starting out, the player was already had a random move selection, where out of 
all the possible legal moves, a random one was selected. I extended that function 
by added a decision tree for the player to pick the best possible solution, based
on the other player also picking the best solution possible. The algorithm is called
the alpha-beta_search that performs pruning of trees as well. The algorithm works by 
making a tree of all possible moves the the player,The tree is traversed in such a way 
that when a move is chosen, the other players move is also chosen as if they were playing 
there best move. The points of these moves are based off the other players decision to give
the player the lease amount of points. Of course there are some branches that pruned, because 
it is evident a value minimum value smaller than the previous node traversed exists, but this 
is to get the basic idea accross. I will go into the specifics of the algorithm after a simple 
example of the idea of the algorithm. 

Example:

Your Player's moves: Right, or Left.

Right Branch:                                     Left Branch:

right moves yields: 2 points                      right moves yields: 2 points
oponents move yields: 2 points                    oponents move yields: 1 points

Branch total 0 points.                            Branch total +1 points.


In this situation, the algorithm would choose the Left Branch for your player.

Tree Example:

First of all, it is important to remember the player gets to choose what branch they want 
that is immediately below them. Each node level is called either min or max node level. The 
max node represents the player that the algorithm is helping win, and the min node level represents
the opponent. This algorthim is suffisticated because it calculates the decisions of the opponent
as if they where just as smart of an AI. 

Max level:                   (A)
                         /    |    \

                     /        |        \

                  /           |           \
Min level:       (B)         (C)         (D)
                / | \       / | \       / | \
               /  |  \     /  |  \     /  |  \
Max level:   (3)(12) (8) (2) (?) (?) (14)(5) (2)

As you can see, at the min level, we have 3 nodes. Each min node has three choices. As stated earlier,
the min node is represented by the player's opponent, and the nodes imidiately below are chosen by the
nodes above. The first min level node will never be pruned, because it does not have any other min level
nodes to compare with. This will make sense soon. Going through the Min nodes, the opponent at node B 
has a choice of you (max nodes) scoring 3, 12, or 8 points. It would be wise for your opponent to choose
3, so the value for B is 3, next min node, at C, your oponent looks at the first choice, which is 2 
points. Here is where the pruning happens. Don't forget about node A, it is represented by the player, 
and it too has to choose which values are the best. Seeing as the opponent has one option available to 
the player (node A), the option being 3 points at node B, and the fact that the opponent is definitely 
going to choose 2 or smaller at node C, the algorithm already knows B will always be a better choice 
than C. Again, the opponent is going to choose 2 or smaller at node C. The player at node A is going to 
choose the largest value available. We know 3 is greater than any number that is 2 or smaller, so the 
rest of the branches in node C do not need to be looked at because node C won't even be chosen. We now
have 3 stored as the best choice between B and C, next is D. D does not show any values less than 3 
until the last branch, so unfortunately we didn't save any time by pruning the branches. In the end, the
move chosen is node B, which yields the most points. The algorithm itself has a system of storing these 
values efficiently, though I need not go into too much detail. 

The algorthm in the book "Artificial Intelligence A Modern Approach" is on page 170, along with an 
example using the same values as above on page 169.

The code for MinVal and MaxVal functions followed by findbestmove function are in the myprog.c lines
415-577 along with the original source code in findbestmove commented out. 

I owe a huge thank you to Dr. Anderson for helping us students out in class with them. 


***Testing The Code***

Although I had a lot of help with the code by following along in class, I still had some issues of my 
own. There was an issue with passing in a state value, where it was mistakenly seen as an address of a 
pointer when it should have been a pointer. That was kinda funny, and an easy fix, thankfully. There was
a lot of optimizations to do to the code as well. I would have like to optimize the point evaluation
by taking the evalboard function out, and calculating the score within the findbestmove function, but I
ran out of time. fprintf was my friend in this assignment, and I will keep that with me forever. With it
I was able to print out strings before the program crashed. That helped a lot. Basically that was how I 
debugged the program, seeing where the fprintf printed showed me where the code was having a hickup so 
to speak. 


***Issues With Codeing***

My biggest issue was getting all the libraries that are required of the program to compile and install 
in the right place on my windows computer that runs linux on an ubuntu shell, but that didn't work out.
Oddly enough, my mac already had all most of the libraries installed, all I had to do was run brew for 
a couple libraries, and manually install x11r6 via a special mac specific download. homebrew is a great 
tool for mac. It has helped me out so many times. I learned a lot installing packages on both my Operating
systems, so I'll be prepared for that in the future. Of course after I spent hours and hours getting the 
right libraries installed, Dr. Andersen said I would just run it on the school's server, onyx... hahaha... 
I should have thought of that!

After the libraries were installed, I had an issue with the other player not making a decision fast enough,
even on a small about of time. Dr. Andersen helped me out, by figuring out in 2 seconds that the player files
were not executable. After that, a couple of runs later and some simple compiler helpful debugging, I had the 
program running perfectly. 


***Thoughts***

Great project, very helpful in learning how AI can be applied to games. Good choice on a game, rather than 
something boring. Thanks for all the help!







