package baseconverter4;

public class BaseConverter4 {

    /**
     * Main Method This method takes in user input of the format "convert x_x
     * x_x" This is taken as a String Array named args. Each element in the args
     * array is then separated and specific action is taken.
     *
     * @param args Will always contain 3 elements that determine the operation
     * and take in initial and final base.
     */
    public static void main(String[] args) {

        //BaseConverter10toAny(AB,15,19);
        //BaseConverter10toAny(AB,15,19);
        String[] array = args[1].split("_");
        String initialValue = array[0];
        int startingBase = Integer.parseInt(array[1]);
        int finalBase = 0;
        double total = 0;

        //three arguments
        if ("convert".equals(args[0])) {
            //System.out.println("Please insert arguments: ");

            StringBuilder args2 = new StringBuilder(args[2]);
            args2.deleteCharAt(0);
            String newArgs2 = args2.toString();

            finalBase = Integer.parseInt(newArgs2);

            String finalAnswer = BaseConverterAnytoAny(initialValue, startingBase, finalBase);

            System.out.println("Final Answer will be: " + finalAnswer);

        } else if ("add".equals(args[0])){
            String[] arrayAdd = args[2].split("_");
            String initialValueArgs2 = arrayAdd[0];
            int secondValueArgs2 = Integer.parseInt(arrayAdd[1]);
            
            total = (BaseConverterAnyto10(initialValue,startingBase,finalBase)) + (BaseConverterAnyto10(initialValueArgs2,secondValueArgs2,0));
            //System.out.println("Total is: " + total);
            //BaseConverter10toAny is not meant to handle negative numbers and that's why nothing shows up in terminal.
            String finalAnswer = BaseConverter10toAny(total,0   ,startingBase);
            System.out.println("Final Answer will be: " + finalAnswer);
            
            
        } else if ("multiply".equals(args[0])){
            String[] arrayAdd = args[2].split("_");
            String initialValueArgs2 = arrayAdd[0];
            int secondValueArgs2 = Integer.parseInt(arrayAdd[1]);
            
            total = (BaseConverterAnyto10(initialValue,startingBase,finalBase)) * (BaseConverterAnyto10(initialValueArgs2,secondValueArgs2,0));   
            String finalAnswer = BaseConverter10toAny(total,0   ,startingBase);
            System.out.println("Final Answer will be: " + finalAnswer);
            
        } else {
            System.out.println("Invalid input: Please enter either "
                    + "convert, add, subtract, multiply, or divide.");
        }

    }

    /**
     * Returns the final answer in the convert operation
     *
     * @param a String that is the first number in args[1]
     * @param b int that is the second number in args[1]
     * @param c int that is the first number in args[2]
     */
    public static String BaseConverterAnytoAny(String a, int b, int c) {

        String finalAnswer = BaseConverter10toAny(BaseConverterAnyto10(a, b, c), b, c);

        return finalAnswer;
    }

        /**
     * This method converts any base to base 10
     *
     * @param initialValue String that is the first number in args[1]. This number will be
     * converted to base 10
     * @param stratingBase Int that is the second number in args[1]. This number is the
     * initial base.
     * @param finalBase Int that is the second number in args[2]. This number is the
     * final base.
     * @return
     */
    public static double BaseConverterAnyto10(String initialValue, int startingBase, int finalBase) {

        double finalAnswer = (transfer(initialValue, startingBase, finalBase));

        if (initialValue.charAt(0) == '-') {

            finalAnswer = finalAnswer * -1;

            System.out.println(finalAnswer);
            return finalAnswer;

        } else {
            System.out.println(finalAnswer);
        }
        return finalAnswer;

    }

    //returns positive number
    public static double transfer(String initialValue, int startingBase, int finalBase) {

        String alphabetStartingAtIndex10 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int initialExponent = (initialValue.length() - 1);

        String initialLength = initialValue;

        double total = 0;
        int placement = 0;

        if (initialValue.contains(".")) {

            String[] arrayValue = initialValue.split("\\.");

            int leftHandLength = arrayValue[0].length();

            initialExponent = (leftHandLength - 1);

        }

        if (initialValue.charAt(0) == '-') {

            StringBuilder modifiedInitialValue = new StringBuilder(initialValue);
            modifiedInitialValue.deleteCharAt(0);
            initialValue = modifiedInitialValue.toString();
            initialExponent--;

        }

        for (int i = 0; i < initialValue.length(); i++) {
            char numericValueIndex = initialValue.charAt(i);
            int valueIndex = 0;

            for (int j = 0; j < alphabetStartingAtIndex10.length(); j++) { //travel through initialValue

                if (numericValueIndex == '.') {
                    valueIndex = 0;

                    initialExponent = 0;

                    break;

                }

                if (numericValueIndex == alphabetStartingAtIndex10.charAt(j)) {

                    valueIndex = j;
                    break;
                }

            }

            total = total + (valueIndex * Math.pow(startingBase, initialExponent));

            initialExponent--;

        }

        return total;

    }

    /**
     * This method takes in a number in base 10 and converts it to the
     * appropriate number in any other base
     *
     * @param initialValue
     * @param b
     * @param convertBase
     * @return
     */
    public static String BaseConverter10toAny(double initialValue, int b, int convertBase) {

        
        String alphabetStartingAtIndex10 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //double newA = initialValue;

        String stringA = Double.toString(initialValue);

        int counter = 0, quotient = 0;
        double newH = 0, o = 0;
        String answer = "";
        String multAtEnd = "";
       
        
        if(initialValue<0){
            initialValue = initialValue *-1;
            multAtEnd = multAtEnd +"-";
        }

        for (int i = 0; i < 25; i++) {
            if (Math.pow(convertBase, i) > initialValue) {
                counter = i--;
                break;
            }
        }
        counter--;

        while (initialValue >= 0 && counter > -5) {
            double remainder = (initialValue % Math.pow(convertBase, counter));
            quotient = (int) ((initialValue - remainder) / Math.pow(convertBase, counter));

            answer = answer + alphabetStartingAtIndex10.charAt(quotient);
            //System.out.println("answer is111   "+answer);
            if (counter == 0) {
                answer = answer + ".";
            }
            initialValue = remainder;
            counter--;
        }

        
        //System.out.println("Final Answer will be: " + answer);
        return (multAtEnd + answer) ;

    }


}
