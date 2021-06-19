package academy.hiperQuiz.quizz.validations;

public class InputValidator {
    static public boolean isANumber(String in){
        boolean aNumber = false;
        try {
            Integer.parseInt(in);
        }catch (NumberFormatException ex){
            return false;
        }
        return true;

    }
    static public boolean isInRange(String in, int size){
        boolean valid = false;
        int num = Integer.parseInt(in);
        if(num>=0 && num < size){
            return true;
        }
        return false;
    }
}
