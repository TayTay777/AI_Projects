CS457 
Fall 2019
P2 - Bagging Problem 
Hailee Kiesecker
Taylor Roberts 


This program suite was designed to show different ways to optimize 
the way a solution is found using three primary techniques. MRV/LCV 
huristics, arc consistency, and a Random-restart hill climbing local 
search. 


Environment: The goal is to bag a given list of items into a cart. 
The cart's number of sacks is given, as well as the size of the sacks.


Item Constraints: each item has a given constraint that specifies 
wither it can be bagged with a certain item or not. Here is an 
example .txt file that is inputted into the programs first argument.

Example:

3                 
7                   
bread  3 + rolls   
rolls  2 + bread   
squash 3 - meat     
meat   5         
lima_beans 1 - meat

As you can see in this example, there is quite a lot going on. The 
first two numbers represent the number of sacks, then the size of 
each of those sacks. The next line down (bread  3 + rolls) shows 
the item, the size of the item, then its constraints. The +/- signs 
indicate wither it can be sacked, or not be sacked with the items 
proceeding the sign. A + sign indicates only those items can be 
bagged with it. A - sign indicates only those items cannot be 
bagged with it. Here is a solution for this example.

Solution:

success
lima_beans      squash
rolls   bread
meat



**MRV/LCV**

To RUN:
bash bagit.sh <textfile> -slow

This search utilizes the depth first search algorithm. The MRV is 
a value assigned to an item that is going into a sack. If an item 
can fit into, say 4 sacks, then it's MRV value would be 4. The LCV 
value is set based on how many items can still fit into sack after 
a disired item to be added is added to the sack. Say I wanted to 
add an item to sackOne. If I added it to sackOne, there would still 
be 4 other items that could fit into it. sackOne's LCV would be 4 
then. 

Adding an item: All the unpacked items are looked at, and a new 
MRV value is calculated for them. Whichever item has the highest 
MRV value is chosen to be placed into a sack first. The MRV's goal 
is to use up the item that has the most uses first, so the other 
smaller range items are less likely to constrain all the other items 
early on. The sack to place the item into is then chosen based off 
the LCV. Because DFS is a stack, the reverse calculated first in 
order for the highest MRV value in the highest LCV value sack to 
be on top of the stack. In other words, the first calculated will 
be on the bottom of the stack.


**Arc Consistency**

to RUN:
bash bagit.sh <textfile> 

This utilizes the MRV/LCV from above, with an added arc consistency 
check. The arc consistency check happens after an item is added. Once 
added, all the remaining items are checked to make sure they can still 
fit into a sack. If they can, everything proceeds as normal, if not the 
cart is not added to the stack. Essentially this is taking potentially 
1 to number of unpacked items uneccessary steps out of the search. 


**Local Search**

to RUN: 
bash bagit.sh <textfile> -local

This type of local search is a Random-Restart local search. There is 
no searching involved with this search, it is purely one state changing 
every iteration. It starts out with all the items randomly placed into 
random sacks. The items are then checked for conflicts. If no conflicts, 
it is a solution. If there is a conflict(s), the conflict is randomly 
chosen, then placed into a sack with the least amount of conflict. This 
process of contraint checking, then moving conflicts is reapeated over 
and over again until a solution is found. There is a high chance the 
conflicted items cannot be placed in a less conflicted spot, reaching 
a stand still called a local maximum. When this happens, the items will 
be stuck in an endless loop, only changing the same item back and forth, 
or not at all. In order to combat this, a random restart is initiated, 
where all the items are placed into the sacks randomly, like in the 
beginning.


**Testing**

For the testing I ran a simple time test in milleseconds 5 times each 
for the different searches.

DFS:
37
37
45
46
46

MRV/LCV:
39
47
48
49
46


MRV/LCV with ARC:
49
47
47
46
46


LOCAL:
52
51
55
55
46


These tests where ran against the example given in this README. 
Conclusively we can see there is noise affecting the results. Saying 
that, MRV/LCV and MRV/LCV+AC are expected to out perform DFS. They are 
all performing about the same. I have attempted to generate a more 
complicated test fitting these search algorithms but I the results are 
still simular. I will say this though, the MRV/LCV and MRV/LCV+AC have 
a lot more nested for loops than DFS. If the nested for loops were gone, 
the speed would most like like make a huge difference then. Saying this, 
I'm am not completely confident the arc consistency is working correctly, 
and unfortunately I have ran out of time in order to further investigate. 
Cheers!