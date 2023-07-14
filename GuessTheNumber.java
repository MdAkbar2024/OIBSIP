import java.util.*;
public class GuessTheNumber{

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int randomnumber=(int)(Math.random()*100)+1;
        int guess=0;
        int numofguesses=0;
        boolean win= false;
        System.out.println("Comprter: I already guess the number between 1 to 100 .can you guess what it is?");
        while(win==false){

            guess=sc.nextInt();
            numofguesses++;

            if(guess==randomnumber){
                win= true;
            
                System.out.println("Congratulations! You guessed the number in"+ numofguesses +"guesses");
            } else if(guess<randomnumber){
                System.out.println("Your guess is too low. try again.");
            } else if(guess>randomnumber){
                System.out.println("Your guess is too high.Try again.");
            }
        }
        sc.close();

    }
}
