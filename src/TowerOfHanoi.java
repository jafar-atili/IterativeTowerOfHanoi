
/*
 * Java Program to Solve Tower of Hanoi Problem using Stacks
 * Created by Jafar Atili
 */

import java.util.*;


/* Class TowerOfHanoi */
public class TowerOfHanoi
{
    public static int numberOfDisks;
    public static boolean even;
    public static final int MIN_VALUE = 1;
    public static int stepsCounter = 0;


    /* Creating Stack array*/
    public static Stack<Integer>[] tower = new Stack[4];


    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        tower[1] = new Stack<Integer>(); //left tower
        tower[2] = new Stack<Integer>(); //middle tower
        tower[3] = new Stack<Integer>(); //right tower

         /* Accepting number of disks */
        System.out.println("Enter number of disks");
        int num = scan.nextInt();
        numberOfDisks = num;
        even = isEven(num); //check if the number of disks is even
        init(num); //initialize `sthe stack
        Solve(); //our algorithm to solve the issue - iterative

    }


    //check if we allowed to move disk from source to destination
    public static boolean allowedToMove(int source, int destination){
        //we are allowed to move disk only if the destination stack is empty or the top of it contains a bigger disk
        if (tower[destination].isEmpty() || tower[destination].peek() > tower[source].peek())
            return true;
        else
            return false;
    }

    //move a disk from source to destination
    public static void move(int source, int destination){
        //check if we allowed to move
        if (allowedToMove(source, destination)) {
            tower[destination].push(tower[source].pop());
            //print the step
            stepsCounter++;
            display();

        }
    }

    //initializing the left stack with required amount of disks
    public static void init(int num){

        for (int i = num ; i > 0 ; i--){
            tower[1].push(i);
        }
        //print the starting step
        display();
    }


    //check if the number of discs is odd or even
    public static boolean isEven(int num){
        if ((num % 2) == 0)
            return true;
        return false;
    }


    //check if we have solved the issue yet
    public static boolean isSolved()
    {
        //it's solved if both right and mid towers are empty, and tower 3 is not empty and contain the Minimum value (1)
        if(!tower[3].isEmpty() && tower[3].peek() == MIN_VALUE && tower[1].isEmpty() && tower[2].isEmpty() )
            return true;
        return false;
    }

    //the algorithm to solve the issue
    public static void Solve(){

        //first thing is to move the minimum value
        moveMin();

        //while it's not solved
        while (!isSolved()){

            //if we are on a non empty tower and does not contain the min value, and we allowed to move disks
            if (!tower[1].isEmpty() && tower[1].peek() != MIN_VALUE && (allowedToMove(1,3) || allowedToMove(1,2)))
            {
                //if we allowed to move left
                if (allowedToMove(1,3))
                {
                    //move left
                    move(1, 3);
                    //move the min value
                    moveMin();
                }
                //else if allowed to move right
                else if (allowedToMove(1,2))
                {
                    //move right
                    move(1, 2);
                    //move the min value
                    moveMin();
                }
            }

            else if (!tower[2].isEmpty() && tower[2].peek() != MIN_VALUE && (allowedToMove(2,1) || allowedToMove(2,3))){
                if (allowedToMove(2,1))
                {
                    move(2, 1);
                    moveMin();
                }
                else if (allowedToMove(2,3))
                {
                    move(2, 3);
                    moveMin();
                }
            }

            else if(!tower[3].isEmpty() && tower[3].peek() != MIN_VALUE && (allowedToMove(3,2) || allowedToMove(3,1))) {
                if (allowedToMove(3, 2))
                {
                    move(3, 2);
                    moveMin();
                }
                else if(allowedToMove(3,1)){
                    move(3, 1);
                    moveMin();
                }
            }
        }
    }


    //move the min value
    public static void moveMin(){

        //if we are on the tower (stack that holding the MIN value
        if (!tower[1].isEmpty() && tower[1].peek() == MIN_VALUE) {
            //if the number of disks is even
            if (even)
                //move right
                move(1, 2);
            else
                //else move left
                move(1, 3);

            //check the same for the remaining 2 towers
        } else  if (!tower[2].isEmpty() && tower[2].peek() == MIN_VALUE) {
            if (even)
                move(2, 3);
            else
                move(2, 1);
        }
        else  if (!tower[3].isEmpty() && tower[3].peek() == MIN_VALUE) {
            if (even)
                move(3, 1);
            else
                move(3, 2);
        }
    }


    /*  Function to display --- was not written by me*/
    public static void display()
    {
        System.out.println("  A  |  B  |  C");
        System.out.println("---------------");
        for(int i = numberOfDisks - 1; i >= 0; i--)
        {
            String d1 = " ", d2 = " ", d3 = " ";
            try
            {
                d1 = String.valueOf(tower[1].get(i));
            }
            catch (Exception e){
            }
            try
            {
                d2 = String.valueOf(tower[2].get(i));
            }
            catch(Exception e){
            }
            try
            {
                d3 = String.valueOf(tower[3].get(i));
            }
            catch (Exception e){
            }
            System.out.println("  "+d1+"  |  "+d2+"  |  "+d3);
        }
        System.out.println("---------------");
        System.out.println("Num of Step "+ stepsCounter +"\n");
    }

}