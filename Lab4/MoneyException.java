public class MoneyException extends RuntimeException{
        /**
        Error that shows when characters have negative value of money
        */
        public MoneyException(String message){
            super(message);
        }
    }

