import java.math.BigInteger;

public class ComplexCalculation {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1,
                                      BigInteger base2, BigInteger power2) {
        BigInteger result;
        PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);
        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread1.start();
        thread2.start();

        try {
            thread1.join(2000);
            thread2.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result = thread1.getResult().add(thread2.getResult());

        if (thread1.isFinished()) {
            System.out.println(base1+ "raised to power " + power1 + " is " + thread1.getResult());
        } else {
            System.out.println("The calculation for thread1 is still in progress");
        }

        if (thread2.isFinished()) {
            System.out.println(base2+ "raised to power " + power2 + " is " + thread2.getResult());
        } else {
            System.out.println("The calculation for thread2 is still in progress");
        }
        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;
        private boolean isFinished = false;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
           result = BigInteger.ONE;
           for(BigInteger i=BigInteger.ZERO; i.compareTo(power)!=0; i=i.add(BigInteger.ONE)){
               result = result.multiply(base);
           }
            this.isFinished = true;
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }

            return result;
        }

        public BigInteger getResult() { return result; }
        public boolean isFinished() {
            return isFinished;
        }


    }

    public static void main(String[] args) {
        ComplexCalculation calc = new ComplexCalculation();
        BigInteger result = calc.calculateResult(BigInteger.valueOf(3), BigInteger.valueOf(2), BigInteger.valueOf(722244442), BigInteger.valueOf(224444227));
        System.out.println("Result: " + result);

    }
}