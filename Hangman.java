import java.util.Scanner;//imports Scanner object
import javax.swing.JOptionPane;//imports JOptionPane object
/**
 * @Nishan D'Souza 
 * @1.0 
 */

public class Hangman
{
    public static void main(String[] args) {
        boolean cont = true; //initializes boolean primitive as true referenced by cont in order to control if the game is run or not
        boolean guessedBefore = false; //initializes boolean primitive as false referenced by guessedBefore to be used at the end to check the word
        int counter = 0;//initializes integer primitive as 0 to be used at the end of the code 
        String alphabet = "1234567890! @ # $ % ^ & * () -_ = + | ] } [ { ' ; : / ? . > , < ` ~ ";//initializes String object referenced by alphabet to check if inputs are valid
        while (cont) { //starts a while loop to control if the game continues or not. The initialized boolean cont is passed in as a parameter (explicit)
            Scanner sc = new Scanner(System.in);//declares new Scanner object referenced by sc and passes in System.in as a parameter - will be used to scan user input           
            String input = JOptionPane.showInputDialog(null, "Enter a word for Hangman. You can't use numbers or special characters!"); 
            /* declares String object referenced by input, sets value to return
             * of showInputDialog, which is invoked on the JOptionPane object. null and a string asking for a word are parameters of showInputDialog. This asks the user to enter
             * a word and sets it to input*/
            for (int i = 0; i < alphabet.length(); i++) {
                while (input.contains(alphabet.substring(i,i+1))) {
                    System.out.println("Enter only characters and numbers!");//if any special characters are in the input, printLn asks user to only enter characters or numbers
                    input = JOptionPane.showInputDialog(null, "Enter a word for Hangman. You can't use numbers or special characters!");//sets String object input to the new word that the user inputted, iterates through the loop until the condition is unsatisfied            

                }
            }
            
            int numChars = input.length();//initializes integer primitive as the return value of the length() method invoked on input, should return number of Characters in string            
            String answerStr = "";//initializes String object referenced by answerStr to an empty string to be modified in the following for loop
            for (int i = 0; i < numChars; i++) {//creates a for loop that iterates for number of characters in the input
                answerStr += "_ ";//concatenates answerStr and a blank for each letter in the word
            }  
            int numberOfGuesses = numChars + 2;//initializes integer variable to the number of Characters in a word + 2 to create the number of guesses that a user has
            String guessLetterBank = "";//initializes String object referenced by guessLetterBank to create a word bank for all of the guessed letters
            while (numberOfGuesses != 0) { //creates a while loop that runs for as long as numberOfGuesses is not 0, starts the guessing and checking part of hangman
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("");//Prints an empty line to create a space between each iteration of the while loop
                System.out.println("Your word " + answerStr);//Prints the answerStr(which starts as blanks but then changes) to show how much of the word is left
                System.out.println("Number of guesses remaining: " + numberOfGuesses);//Prints the number of guesses left by printing the variable numberOfGuesses
                System.out.println("You already have guessed: " + guessLetterBank);//Prints the letters already guessed by printing guessLetterBank
                System.out.println("Enter a letter for Hangman or enter a word for your full guess! Any wrong words will be penalized!");/* Prints a sentence 
                 * to tell the user to enter a letter, number, or word*/
                String letterEntered = sc.next();/*uses previously initialized Scanner object referenced by sc to set the letter that the user enters to a newly 
                declared and initialized String object referenced by letterEntered
                 */                 
                letterEntered = letterEntered.toLowerCase();//sets the letter to a lowerCase version to make sure an uppercase wasn't entered
                String newWord = "";//declares and initialzes a String referenced by newWord to an empty String to be used in the else statement following the next if's
                boolean correctEntry = false;
                correctEntry = true;
                for (int i = 0; i < alphabet.length(); i++) {//iterates the number of spaces that alphabet has
                    if (letterEntered.contains(alphabet.substring(i,i+1))) {//checks if the letterEntered contains the specified substring of alphabet
                        System.out.println("Enter only characters or numbers!");
                        correctEntry = false;//sets correctEntry to false to disallow a run of the program later on

                    }
                }           
                if (guessLetterBank.contains(letterEntered)) {//checks if the letterEntered is part of the word bank
                    System.out.println("Your letter or number was already used! Try again!");//if the condition is satisfied, tells the user the letter was used, skips rest of loop
                    correctEntry = false;
                    letterEntered = sc.nextLine();
                }
                if (letterEntered.length() > 1 && letterEntered.length() != input.length()) {//checks if a word that isn't the length of the first input is there
                    correctEntry = false;
                    System.out.println("You entered a word that wasn't the length of the word for you to guess! Try again!");
                    letterEntered = sc.nextLine();
                }
                if (letterEntered.length() > 1) {//checks if the letter entered is greater than 1 character long (a.k.a. a word)
                    correctEntry = false;
                    if (letterEntered.equals(input)) {//checks if the word entered is equal to the primary word entered at the beginning
                        System.out.println("You guessed the word right! Nice job!"); //congratulates the user
                        guessedBefore = true;//sets guessedBefore to true, used at end of code
                        break;//breaks out of while loop to end the game
                    }
                    else if (letterEntered.equals(input) == false) {//checks if the word entered is not equal to the primary word
                        System.out.println("That word wasn't the right answer! You lose a guess!");//if the word do not match, tells the user that the word is wrong
                        correctEntry = false;
                        numberOfGuesses--;//takes off a guess becuase the user was wrong
                    }        
                }
                if (correctEntry) {//checks if correctEntry is true, and if so runs the following
                    if (input.contains(letterEntered)) {//checks is the input contains the letter
                        for (int j = 0; j < numChars*2; j++) {//creates a for loop to run numChars*2, accounting for the spaces between blanks
                            if(j%2 == 0) {//it will only add a letter when the for loop is on a part of the string containing a _
                                if (input.charAt(j/2) == letterEntered.charAt(0)) {
                                    newWord += letterEntered;//if the letter is part of the first input, add the letter to the new word initialized before
                                }
                                else {
                                    newWord += answerStr.substring(j,j+1);//if the letter is not part of the word, add a substring that is there into the word
                                }
                            }
                            if (j%2 != 0) {
                                newWord += answerStr.substring(j,j+1);//if the part of the word is a space, which j%2 does, add a substring of the space
                            }
                        }
                        answerStr = newWord;//sets word with blanks in it at first to the modified word
                        newWord = "";//sets the modified word to nothing to restart the process
                    }

                    else {
                        System.out.println("That letter wasn't in the word!");//the the word did not contain the letter, print the letter wasn't in the word
                    }
                    numberOfGuesses--;
                    guessLetterBank+= letterEntered + " ";//adds the letterEntered to the word bank                
                    for (int i = 0; i < numChars; i++) {//for loop to create and add to a counter to be used later on
                        if (answerStr.charAt(i+i) == input.charAt(i)) {
                            counter++;
                        }
                        else {
                            counter = counter + 0;
                        }
                    }
                    if (counter == numChars) {//uses the counter to check if the user gets the word right before number of guesses gets to 0
                        System.out.println("You guessed the word right!");
                        counter = 0;
                        guessedBefore = true;
                        break;
                    }                
                    counter = 0;
                }
            }

            for (int i = 0; i < numChars; i++) {//uses the same logic as before in modifying the counter
                if (answerStr.charAt(i+i) == input.charAt(i)) {
                    counter++;
                }
                else {
                    counter = counter + 0;
                }
            }

            if (guessedBefore == false) {//gives a you win or you lose message based on the counter's value
                if (counter == numChars) {
                    System.out.println("You win!");
                }
                if (counter != numChars) { 
                    System.out.println("You lose!");
                }
            }
            System.out.println("Your word was " + input);
            System.out.println("Would you like to continue again? (y or n)");
            String contResp = sc.next();//initializes newly declared string contResp to the input by the user
            contResp = contResp.toLowerCase();
            if (contResp.equals("n")) {
                cont = false;
            }
        }
        System.out.println("Thanks for trying my program!");
    }
}
