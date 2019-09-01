/* 
 *Author: Büþra Oðuzoðlu
 * Hangman Main class 
 * CS-102 Section 07 Homework
 */

import java.util.Scanner; 

public class Main 
{ 
    public static void main( String[] args) 
    { 
        //Variables including object references 
        Scanner scan; 
        HangMan game; 
        String userInput; 
        String letter; 
        int errorValue;
        boolean temp;
        
        //Initialize the variables (other variables will be given values as program proceeds) 
        scan = new Scanner( System.in); 
        temp = true; 
        
        //Create the user-interface and play the game 
        while( temp ) 
        { 
            game = new HangMan(); 
            System.out.println( "Welcome to Hang-man game"); 
            System.out.println( "You have " + 6 + " tries"); 
            System.out.println( "Enter any button except q to proceed, q to quit"); 
            userInput = scan.next(); 
            
            //Outer loop for re-playing of the game
            if( !userInput.equals( "q")) 
            { 
                //Inner loop for the simulation of the game 
                while( !game.isGameOver() && !game.hasLost()) 
                { 
                    System.out.println( "The word that you're looking for: " + game.knownSoFar); 
                    System.out.println( "Number of tries left : " + (6 -  game.numberOfIncorrectTries)); 
                    System.out.println( "Letters in the english alphabet: " + game.allLetters); 
                    System.out.println( "Used letters: " + game.usedLetters); 
                    System.out.println( "Please enter a letter");
                    letter = scan.next(); 
                    errorValue = game.tryThis( letter.charAt(0) /* or just letter itself (depending on the implementation of the method)*/); 
                    
                    if( errorValue == -1) // Invalid input 
                    {
                        System.out.println( "Please enter a valid letter"); 
                    }
                    else if( errorValue == -2) // Repeated letter 
                    {
                        System.out.println( "You entered this letter before, please enter a different letter"); 
                    }
                    else if( errorValue == 1) // Correct answer 1 repetition 
                    {
                        System.out.println( "The letter you entered is repeated 1 time"); 
                    }
                    else if( errorValue > 1) // Correct answer multiple representation
                    {
                        System.out.println( "The letter you entered is repeated " + errorValue + " times"); 
                    }
                    else if( errorValue == 0) // Wrong answer 
                    {
                        System.out.println( "Unfortunately the letter you entered is not in the word"); 
                    }
                    else if( errorValue == -3) // Game Over 
                    {
                        System.out.println( "Game Over"); 
                    }
                    if( game.hasLost()) 
                    {
                        System.out.println( "Unfortunately, you lost"); // Implementing a method showing the secret word would be nice 
                    }
                    else if( game.isGameOver()) 
                    {
                        System.out.println( "You won, congratulations"); 
                    }
                } 
            } 
                               
            else 
            { 
                temp = false; 
                System.out.println( "Bye - bye, thanks for playing"); 
            } 
        } 
    } 
}
        
        