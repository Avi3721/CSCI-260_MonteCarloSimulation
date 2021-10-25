/* 
 * Name - Avivartta Krishna
 * ID -126351
 * This is Part A, Part B and Part C of the Computer Project
 */

public class MonteCarloStudy
{
    public static void main(String[] arguments) 
    {
        /* 
         * Monte Carlo requires many trials
         * I will conduct the simulation 38416 times
         * As we find from the equation given in the book at page 118 of the book, that the size of This MonteCarlo Study Should Be 38146
         * The Trees_Burned will keep track of trials where more than 40% of trees are on fire 
         */

        int Number = 38417; // One More Than Needed, To Complete The Loop
        int Trees_Burned = 0;

        //This array will store amount of trees on fire per trial

        int[] onFire = new int[Number];

        for (int x = 0; x < Number; x++) 
        {
            /*
             * Each Trial Will Give Us A Result
             * Result We Want: Probability That More Than 40% Of Forest Burned Down
             * We Will Store The Outcome Of Each Simulation And Then Determine The Probability (OverAll MatchCase)
             * From The QUestion It Is Given That There Are 1200 Trees In The Forest [40*30 Grid]
             * Each Tree Being On fire Or Not On Fire Can Be Considered A Binomial
             * Store A 1 For Fire And 0 For No Fire In The Array
             */

            int[][] trees = new int[40][30]; /* Grid of 1200 trees */

            for (int y = 0; y < 40; y++) 
            {
                for (int z = 0; z < 30; z++) 
                {
                    //Given In The Question - Tree On Top Left Of Forest Is On Fire
                    trees[0][0] = 1;

                    //From The Question We Also Know That There Are Four Ways In Which A Tree Can Catch On Fire
                    double left = Math.random();
                    double right = Math.random();
                    double top = Math.random();
                    double below = Math.random();

                    if ((left < .85 && y != 0 && trees[y - 1][z] == 1)
                    || (right < .35 && y != 39 && trees[y + 1][z] == 1)
                    || (top < .35 && z != 29 && trees[y][z + 1] == 1)
                    || (below < .35 && z != 0 && trees[y][z - 1] == 1)) 
                    {
                        trees[y][z] = 1;
                    } 
                    else
                        trees[y][z] = 0;
                }
            }

            int count = 0;

            //These Two For Loops Calculate How Many Trees Burn During The Whole Process
            for (int y = 0; y < trees.length; y++) 
            {
                for (int z = 0; z < trees[y].length; z++) 
                {
                    if (trees[y][z] == 1) 
                    {
                        count++;
                    }
                }
            }

            onFire[x] = count;
            System.out.println("Trees On Fire In Trial " + x + " = " + onFire[x]);

            double D = new Double(count);
            double percentage = (D / 1200.0) * 100;
            System.out.println("Percentage Of Trees On Fire In Trial " + x + " = " + percentage);
            System.out.println("");

            if (percentage > 0.4) 
            {
                Trees_Burned++;
            }
        }

        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Trials Where More Than 40% Of Trees Are Burned = " + Trees_Burned);
        double T1 = new Double(Trees_Burned);
        double N = new Double(Number);
        double probability = (T1 / N);
        System.out.println("Probability Of More Than 40% Forest Is Burned = " + probability);

        int Total = 0;
        for (int z = 0; z < onFire.length; z++) 
        {
            Total = Total + onFire[z];
        }
        int average = Total/Number;
        System.out.println("Average Number Of Trees On Fire From Total Trials = " + ((average) * 4));

        /* To Calculate Standard Deviation, We Follow The Following Steps:
         * Step 1: Find the mean.
         * Step 2: For each data point, find the square of its distance to the mean.
         * Step 3: Sum the values from Step 2.
         * Step 4: Divide by the number of data points.
         * Step 5: Take the square root.
         */
        double S_D1 = ((average) * (average));
        double S_D2 = (S_D1 / Number);
        double SD = Math.sqrt(S_D2);
        System.out.println("Standard Deviation Of X = " + SD);
        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------------------------");
    }
}
