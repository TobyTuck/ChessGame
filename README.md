This project is part of my S.T.E.M. Train Scholarship Project from Arizona State University and the 
National Science Foundation. This project will consist of a multiplayer-player chess game, 
that verifies all moves made by either player.

A short breakdown of the class types:
    1) Chessboard - provides a simple GUI that displays a chess board to its users
    2) MyMouseAdapter - handles any MouseEvents, such as mouse clicks or drags, and interprets them
                        to the lower programs
    3) <chess piece>.java - a sub program that represents each type of chess piece, and are 
                            responsible for handling piece moves, image types, etc
        NOTE: all of these sub programs inherit from the 'ChessPiece' sub program, which provide
              common fields and methods used by all piece, especially for determing the validity of 
              piece moves

    4) <chess piece>.png - image for each chess piece that represents the piece on the chess board

Feel free to send a pull request for this program if you would like to run it yourself
