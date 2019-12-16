Taylor Paul Roberts
Project Suspicion
12/15/2019


***Intro***

        Suspicion is ultimately a game a chance. There is no guarantee that a player will
    win, even with the most ultimate of strategies. Saying that, a strategy is guaranteed 
    to improve chances of winning. 

        I have played a lot of games, like a lot. Board games are somewhat of a past-time for 
    me, meaning way in the past, I used to play them. This definitely brought back a lot 
    of memories involving monopoly, life, and for some reason operation. 

        I think this game is pretty difficult. It's not hard, but if you're playing against a 
    somewhat intelligent person, it could be impossible to win without a strategy. The game
    makes it kind've easy on the player, by giving them a sheet of players to mark off, so 
    ehh, it's this or that in term of difficulty. 

        As far as branching goes, there is a lot to consider. When a decision about a move is made
    it can give a clue as to who you, the player are, right? Everytime a move is made, a new 
    strategy is can be thought up by every player. It's kinda like, from the player's perspective,
    "Ok, if go here, there is new knowledge that all the other players have of me making this decision,
    wich will affect there choices at the end." That's one branch, the knowledge of moves branch. Then 
    there is a points branch as well the the player has. In other words it would be like the player 
    thinking "Ok, they'll get this new knowledge of me, but the possible points they could get has 
    a small chance of even happening vs. the points I know I'm getting now.", in a simple view of things.
    Mathmatically, these numbers would be calculated in the same manner, and the highest yielding value
    would be the best choice. 

Calculating Branching Factor:

    Before anything else, let's establish that 6 players are playing the game. 

        The pieces on the board are randomly placed, and consider corner cases, pieces can only be moved 2 - 4 
    different places. Two pieces get moved every turn. The branching factor would be the total numer of legal
    moves per roll, I think?, so we have 2 * 4 for the corner cases, 3 * 4 for the middle-edge places, and 4
    moves for the center position. This totals 24 possible moves. Saying that, there are 

    With a max of 4 possible moves for a piece, with two pieces each roll, there is a max 4 x 4 branching factor.
        -There is just one spot (the middle) that allows 4 moves
    With a min of 2 possible moves for a piece, with two pieces each roll, there is a min 2 X 2 branching factor.
        -There are 4 corner spots on the board
    With the mid-edge locations there are 3 possible moves for a piece, with two pieces each roll, 
    there is a 3 x 3 branching factor.
        -There are 4 mid-edge spots on the board

    Taking all of that into consideration, 

    (4x4) * 1 = 16 
    (2x2) * 4 = 16
    (3x3) * 4 = 36

    (16 + 16 + 36) / 9 = 7.56 

    So on average there is a 7.56 branching factor, with a max branching factor of 16 (4x4), and min of 4 (2x2) 
    for just moving the guest pieces after a roll. 

    The player also has a possibility of claiming gems, asking a question, checking invitation, and moving a player. 
    All of these would need to be considered for the branching factor as well. Before calculating them, one must know
    how many cards there are, and how many of these actions are on each card. 

    There are 28 cards, as follows (Copied from Suspicion.java):

            "get,yellow:ask,Remy La Rocque,",
            "get,:viewDeck",
            "get,red:ask,Nadia Bwalya,",
            "get,green:ask,Lily Nesbit,",
            "viewDeck:ask,Buford Barnswallow,",
            "get,red:ask,Earl of Volesworthy,",
            "get,:ask,Nadia Bwalya,",
            "get,green:ask,Stefano Laconi,",
            "get,yellow:viewDeck",
            "get,:ask,Dr. Ashraf Najem,",
            "get,green:viewDeck",
            "get,red:viewDeck",
            "get,:ask,Mildred Wellington,",
            "get,:move,",
            "get,:ask,Earl of Volesworthy,",
            "get,:ask,Remy La Rocque,",
            "viewDeck:ask,Viola Chung,",
            "get,:ask,Stefano Laconi,",
            "get,:ask,Viola Chung,",
            "get,:viewDeck",
            "get,:ask,Lily Nesbit,",
            "get,yellow:ask,Mildred Wellington,",
            "get,:ask,Buford Barnswallow,",
            "get,:move,",
            "move,:ask,Dr. Ashraf Najem,",
            "get,:viewDeck",
            "get,:ask,Trudie Mudge,",
            "move,:ask,Trudie Mudge,"

    The player also has two cards, and can only choose one. With a full game of players, the player must
    do all the actions on a single card, otherwise only choose one of the actions on the card. Let's 
    asume this is a game with a full set of players, so the player must do the two actions, on the card 
    they choose. 

    With two cards, the branching factor is multiplied by 2, and then actions are chosen are the card. 

    The possible actions are listed above. That is 28 different actions. Out of those actions, 15 gem cards
    can be any gem chosen, with a min amount of options being 1, and max 2. Out of the 9 board rooms, there are only 
    3 that have one gem as an option, while the other 6 have 2. Also, out of the 15 any-gem cards, 2 of them have the 
    move option. This option increases the branches by a factor of 8, one branch for every possible spot. 

    To add to the complexity, an ask card can have a branching factor of 0 to (# of players) * 10, this branching factor
    has to do with the score card, and how it's state can branch as well. This calculation can be really complex, so 
    for the sake of simplicity, we will say about 5.


    For just the get-any gem branching factor,
    2 * 9 = 18
    1 * 3 = 3

    BF: (18 + 3) / 12 = 1.75 average

    This also affects the score cards, giving locations away by showing where the player's guest could possibly be.
    We can multiply this the same as the ask card. 
    
    So the branching factor would be 1.75 * 5 = 8.75


    Viewing the card deck has 4 possible draws with 6 players. If the same player gets to draw from the card deck 
    twice, the odds of them drawing the same card is 1/4. If the same card is drawn, there would be no new branches.
    Saying that, to keep this simple, because of the simplication of the score card, we can just keep it at 4, and 
    it will still be very close to the real branching factor. Like the gem-any gem card, this card affects the branching 
    factor of the score card. 
    
    Again, we can multiply this branching factor by the score card branching factor, giving
    us 4 * 5 = 20


    In order to make this a lot simpler to visualize, we can just calculate the branching factor for each individual 
    card first, sum them all up, then divide by 28 to get the average branching factor for playing a card. 


            "get,yellow:ask,Remy La Rocque,",       BF: 1 * 5 = 5
            "get,:viewDeck",                        BF: 8.75 * 4 (Guests - # of players) * 5 (player card change) = 175
            "get,red:ask,Nadia Bwalya,",            BF: 1 * 5 = 5
            "get,green:ask,Lily Nesbit,",           BF: 1 * 5 = 5
            "viewDeck:ask,Buford Barnswallow,",     BF: 20 * 5 = 100
            "get,red:ask,Earl of Volesworthy,",     BF: 1 * 5 = 5
            "get,:ask,Nadia Bwalya,",               BF: 8.75 * 5 = 43.75
            "get,green:ask,Stefano Laconi,",        BF: 1 * 5 = 5
            "get,yellow:viewDeck",                  BF: 1 * 4 * 5 = 20
            "get,:ask,Dr. Ashraf Najem,",           BF: 8.75 * 5 = 43.75
            "get,green:viewDeck",                   BF: 20
            "get,red:viewDeck",                     BF: 20
            "get,:ask,Mildred Wellington,",         BF: 8.75 * 5 = 43.75
            "get,:move,",                           BF: 8.75 * 8 = 70
            "get,:ask,Earl of Volesworthy,",        BF: 8.75 * 5 = 43.75
            "get,:ask,Remy La Rocque,",             BF: 8.75 * 5 = 43.75
            "viewDeck:ask,Viola Chung,",            BF: 20 * 5 = 100
            "get,:ask,Stefano Laconi,",             BF: 8.75 * 5 = 43.75
            "get,:ask,Viola Chung,",                BF: 8.75 * 5 = 43.75
            "get,:viewDeck",                        BF: 8.75 * 20 = 175
            "get,:ask,Lily Nesbit,",                BF: 8.75 * 5 = 43.75
            "get,yellow:ask,Mildred Wellington,",   BF: 5
            "get,:ask,Buford Barnswallow,",         BF: 8.75 * 5 = 43.75
            "get,:move,",                           BF: 8.75 * 8 = 70
            "move,:ask,Dr. Ashraf Najem,",          BF: 8 * 5 = 40
            "get,:viewDeck",                        BF: BF: 8.75 * 20 = 175
            "get,:ask,Trudie Mudge,",               BF: BF: 8.75 * 5 = 43.75
            "move,:ask,Trudie Mudge,"               BF: 8 * 5 = 40

    Calculating the average branching factor for the cards, we get 1472.5 / 28 = 52.59 branches per card play


    Now, taking all the calculations together, the final branching factor can be calculated. 

    7.56 (roll BF) * 2 (choosing one card out of the two, BF) * 52.59 (card play BF) = 795.16 BF


***Decision or decisions

        There are a lot of decision to take into account as well. When I initially played this game is class,
    I thought it most important to not reveal who I was. After running very few calculations in my head, I 
    soon realized having a greedy approach is more effective at winning the game then avoiding my player's 
    identity to be reavealed. If they do guess my player, it is only 6 points in there favor. Saying that, 
    it does also make it easier to find out who other players are if I am reavealed, but the other players 
    must catch up to me to win, so they would inevitably have to reaveal there location as well. This is my
    strategie, although I'm 100% positive there is a fine tuned strategie that combines this with not reavealing
    the location. Also, part of my strategy is to place characters in places such that they only have one gem option,
    or in a place where there is only a gem choice that only they can make. This is not a very reliable strategy
    though, because guests are constantly moved around, making it a sort of plan B. Cards are another thing. When 
    a player asks you if you see someone, or even when a player moves someone anywhere they want, this can be to 
    gain gems or to subvert awareness on who the player's guest is. I don't really record that information, for 
    that reason. Main thing to consider is using other player's Gem-any card choices to rule players out, your own
    ask cards to rule players out, and as much as you can, collect three sets of gems without worrying about reavealing
    yourself too much, but if you have the option of getting a set of 3 with or without reavealing yourself, might 
    as well take the without reavealing yourself option.


***Results

    Bot Name: RBotForStudents

    Running 100 Matches with random Bots (excluding RBotV4, because it would just win them all anyways):

    Current tournament results...
    PlayerName,GuessScore,GemScore,TotalScore,wins
    RBotS1.class3,3430,6041,9471,32
    RBotS3.class4,3500,5690,9190,23
    RBotForStudents.class1,3500,5861,9361,23
    RBotS4.class5,3451,5565,9016,17
    RBot.class0,2842,5597,8439,3
    RBotDumb.class2,2590,5619,8209,2


    Running 3, 50 match games with mostly dumbBots;


    Current tournament results...
    PlayerName,GuessScore,GemScore,TotalScore,wins
    RBotForStudents.class1,1750,3095,4845,31
    RBot.class0,1519,2905,4424,7
    RBot.class3,1456,2829,4285,4
    RBotDumb.class2,1323,2862,4185,3
    RBotDumb.class5,1365,2835,4200,3
    RBotDumb.class4,1407,2876,4283,2


    Current tournament results...
    PlayerName,GuessScore,GemScore,TotalScore,wins
    RBotForStudents.class1,1750,2997,4747,27
    RBot.class0,1477,2818,4295,9
    RBotDumb.class5,1337,2891,4228,6
    RBotDumb.class2,1442,2931,4373,5
    RBotDumb.class4,1400,2908,4308,2
    RBot.class3,1484,2851,4335,1


    Current tournament results...
    PlayerName,GuessScore,GemScore,TotalScore,wins
    RBotForStudents.class1,1750,3043,4793,31
    RBotDumb.class2,1365,2806,4171,5
    RBot.class3,1463,2853,4316,5
    RBotDumb.class4,1337,2842,4179,4
    RBot.class0,1414,2878,4292,3
    RBotDumb.class5,1365,2812,4177,2


    Running 3, 50 Match games with the same random bots;


    Current tournament results...
    PlayerName,GuessScore,GemScore,TotalScore,wins
    RBotS1.class3,1715,3013,4728,21
    RBotForStudents.class1,1750,2938,4688,16
    RBotS3.class4,1750,2782,4532,7
    RBotS4.class5,1701,2784,4485,3
    RBot.class0,1372,2836,4208,2
    RBotDumb.class2,1295,2718,4013,1


    Current tournament results...
    PlayerName,GuessScore,GemScore,TotalScore,wins
    RBotForStudents.class1,1750,2994,4744,16
    RBotS1.class3,1729,2959,4688,14
    RBotS3.class4,1750,2876,4626,11
    RBotS4.class5,1701,2803,4504,6
    RBot.class0,1407,2807,4214,2
    RBotDumb.class2,1400,2790,4190,1


    Current tournament results...
    PlayerName,GuessScore,GemScore,TotalScore,wins
    RBotS1.class3,1722,3010,4732,19
    RBotForStudents.class1,1750,2932,4682,11
    RBotS3.class4,1750,2825,4575,9
    RBotS4.class5,1729,2808,4537,8
    RBot.class0,1393,2779,4172,2
    RBotDumb.class2,1274,2748,4022,1


        These results are based on setting the number of gems to 12 for each color. I noticed that the 
    more moves (because of more gems) the more obvious it was who was who. There was littery 0 entropy
    almost everytime for the choices available. When I changed the number of gems to 2, it became less
    obvious to the other players who I was, and I ended up winning more games. Because I am using 6
    bots, the game rules state 12 gems should be used if there are 6 players, so this is accurate to 
    a real game. 


***Bot modifications***


    Using this awesome program suite, I started focusing on using the knowledge base to make my decisions. I 
wanted to get the simple things out of the way first, that would improve my results drastically, so I 
researched the code to figure out how the knowledge base (score card) worked. When I starting writing code
I thought I needed to make my own KB, but later, after running simulations, I found out I didn't need to. 
To my surprise, the players that could be seen/not-seen from an ask action were automatically taken off
the scorecard by means of removing players from an array that stored possible guests for each player. 
After spending a good deal of time finding that out, I turned to gem choices. The quickest way to get 
points in this game, as mentioned above, is to get sets. The code is currently set to pick a random gem
when "get," followed by no color, is the card action. I changed this to pick a gem I had the least of. 
After running test with that, I found my scores skyrocketed. After that I wanted to hide my location as 
well. I say, there was no other players on a gem I chose, it would right away reveal my location. I utilized
the getGuestsInRoomWithGem() method and ran a bunch of matches again. Surprising, this didn't improve my 
scores. I ran quite a few simulations with different bot setups, and still, not using getGuestsInRoomWithGem()
was a better choice. 

    I also was considering using the entropy functions to my advantage, but everytime a ran a game with 12 
gems, I always got 0 entropy for every player. When running the matches with 2 gems though, the entropy
would be very useful, because there where a few 0 totalcount value guests for each player. I could have 
easily removed those guests from the player object's possibleGuests array, but the game cannot even have
2 gems if there is 6 players, so I put that on the back burner. 