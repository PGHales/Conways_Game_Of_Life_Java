# Conways_Game_Of_Life_Java
A simple recreation of Conway's Game of Life in Java

7/22/2021

This is a small project I worked on at the beginning of this summer to start practising using Java again. It was also a project I used to apply what I learned about making a "game" where objects are updated and rendered on a screen a certain number of times so I could use that for other projects I have also worked on. The code is defintely not perfect, and needs a lot of improvement, which I may do at some point in the future, although for now I am uploading what I currently have here on GitHub.

You can look up what Conway's Game of Life is if you're interested in what it is, but I will summarize what it is here as best as possible using what information I found on Wikipedia (https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)

The game was created by a mathmetician named John Horton Conway in 1970 which simulates the growth of life using a grid of spaces (or cells) which are either alive (on) or dead (off). There are four rules to the game, which are:

1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.
2. Any live cell with two or three live neighbours lives on to the next generation.
3. Any live cell with more than three live neighbours dies, as if by overpopulation.
4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

You start by selecting which cells you want to be alive, and then run the simulation to see how your civilization reproduces over different generations.

I learned how to make a game with Java from RealTutsGML on Youtube, which greatly influenced this project, so check him out if you are interested in learning more about game development in Java.
Here is a link to the playlist of videos I watched to learn how to make a game: https://www.youtube.com/playlist?list=PLWms45O3n--6TvZmtFHaCWRZwEqnz2MHa
