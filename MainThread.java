public class MainThread {

    public static void main(String[] args){


        ThreadExample example = new ThreadExample();

        Thread t = new Thread(example);

        t.start();
    }
}
