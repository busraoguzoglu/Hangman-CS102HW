/* 
 *Author: Büþra Oðuzoðlu
 * Hangman class 
 * CS-102 Section 07 Homework
 */

import java.util.Random;

public class HangMan {
  
  // properties , constants
  
  public static final int  maxAllowedIncorrectTries = 6 ;
  public static final String[] secretWords =  {"java" , "homework" , "programming" , "computer" , "coding", "lecture" , "music" , "sophisticated" , "university" , "student"};
  
  // all letters of the alphabet are unchangeable 
  public static final String allLetters = new String("abcdefghijklmnopqrstuvwxyz");
  
  public String    secretWord ;
  public String   usedLetters ;
  public int  numberOfIncorrectTries ;
  public String knownSoFar;
  
// create a constructor of HangMan with the given properties
 
 public HangMan(){
  //creating secretWord by calling chooseSecretWord method as parameter
  //if the method is static we can use it as secretWord=new StringBuffer(chooseSecretWord());
  secretWord = this.chooseSecretWord();

  //setting number of incorrect tries to default number 0
  numberOfIncorrectTries = 0;

  //creating usedLetter
  usedLetters = "";

  //initializing knownSoFar which consists of Ô*Õ characters in the length of secretWord
  knownSoFar = "";
  for( int i = 0; i < secretWord.length(); i++){
    knownSoFar = knownSoFar + "*";
  }
   
 }

//methods
  public String chooseSecretWord()
   {
    int randomSelection;

    Random rand = new Random();
    randomSelection = rand.nextInt(10);

    return secretWords[randomSelection];

   }

   public int tryThis(char letter)
    {
        //Variables
        boolean flag = true;
        //keeps the occurance rate of the letter in the word
        int letterMatch = 0;
        
        //Code
        //Checks if letter is capitilised
        if (letter <= 'Z' && letter>= 'A')
        {
            //turns letter into lowercase
            letter = (char)(letter - 'A' +'a');
        }
        
        //Checks if given char is letter
        if (!(letter <= 'z' && letter>= 'a'))
        {
            return -1;
        }
        
        //Checks if letter is already used
        for (int i =0; i < (this.usedLetters).length(); i++)
        { 
            if (letter == (this.usedLetters).charAt(i))
            {
                return -2;
            }
        }
        
        //Checks if game is over by incorrect tries
        if (this.numberOfIncorrectTries >= this.maxAllowedIncorrectTries) 
        {
            //Since the game is over usedLetters is cleared
            this.usedLetters = "";
            return -3;
        }
        
        //Checks if game is over by correct guess
        if (!(this.knownSoFar.equals(this.secretWord)))
        { 
            //Checks letter if its in the word
            for (int i =0; i < (this.secretWord).length(); i++)
            {
                //if letter is in the i th index...
                if ((this.secretWord).charAt(i) == letter)
                {
                    //...Avoids outOfBounds Exception
                    if (i == this.secretWord.length() -1 )
                    {
                        //...Updates knownSoFar
                        this.knownSoFar = this.knownSoFar.substring(0,i) + letter; 
                    }    
                    //...Updates knownSoFar
                    this.knownSoFar = this.knownSoFar.substring(0,i) + letter + this.knownSoFar.substring(i+1);
                    //... adds letter to usedLetters
                    this.usedLetters = usedLetters + letter;
                    //... increments the count ofmatching letters
                    letterMatch++;
                    //... flag boolean to avoid other if condition from working
                    flag =false;
                }
            }
            // if letter is not in the word...
            if (flag)
            {
                //... adds letter to usedLetters
                this.usedLetters = usedLetters + letter;
                //increment the incorrect tries
                numberOfIncorrectTries++;
            }
            // returns the amout of matching letters
            return letterMatch;
        }
        
        //Since the game is over usedLetters is cleared
        this.usedLetters = "";
        //---> ends the game if word is guessed completely
        return -3;
    }

   public boolean isGameOver () {
     return this.hasLost() || (knownSoFar).equals(this.secretWord);
   }
   public boolean hasLost() {
     return numberOfIncorrectTries == maxAllowedIncorrectTries;
   }
  




} 