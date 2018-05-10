package lab1_data;

public class parra_test {

    public static void main(String []args){

        int pool_size = 50;
        parra_client[] pool = new parra_client[pool_size];
        for(int i=0; i<pool_size; i++){   // initiate the pool
            pool[i] = new parra_client();
        }

        for(int i=0; i<pool_size; i++){   // start all pool
            pool[i].start();
        }

//        parra_client t = new parra_client();
//        t.start();

        try{
            Thread.sleep(5000);

            for(int i=0; i<pool_size; i++){
                pool[i].setStop_signal();
            }

            //Thread.sleep(2000);

            for(int i=0; i<pool_size; i++){
                while(pool[i].isAlive()){
                    Thread.sleep(1);
                }
            }

            int tt_op = 0;
            int tt_ss = 0;

            for(int i=0; i<pool_size; i++){
                tt_op = tt_op + pool[i].getTotal_op();
                tt_ss = tt_ss + pool[i].getTotal_success();

            }

            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println(tt_op);
            System.out.println(tt_ss);
            System.out.println((float)tt_ss / (float)tt_op);
        }
        catch (Exception e){
            e.printStackTrace();
        }



    }
}
