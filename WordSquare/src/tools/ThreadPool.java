/**
 *  This class manages the threadpools for the game, centering them into one file.
 *  For most used when placing a letter into once own board.
 * 
 *  The ThreadPool is so that everyone can place their own letter on their own board 
 *  without everyone needing to wait on one another. Placing it into one file will give 
 *  an ease for future calls.
 * 
 *  @author Alex Peschel
 */
package tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    private ExecutorService threadpool;

    public void CreateThreadPool(int amount){
        this.threadpool = Executors.newFixedThreadPool(amount); 
    }

    public void CloseThreadPool(Runnable task){
        this.threadpool.execute(task);
    }

    public void ShutdownThreadPool(){
        threadpool.shutdown();
        while(!threadpool.isTerminated()) {
            try{
                Thread.sleep(100);
            }catch (Exception e){}
        }
    }
}
