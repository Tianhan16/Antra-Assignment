import java.util.concurrent.*;
/**
 * 
 * @author TIanhan Wang
 *
 */
//Driver Class for the reader and writer class
public class Calculator {

	public static void main(String[] args) {
		//initialize a thread-safe queue as Buffer to hold Strings to write into output file.
		BlockingQueue<String> q=new ArrayBlockingQueue<String>(1024);
		Reader reader=new Reader(q);
		Writer writer=new Writer(q);
		
		//Put both reader and writer into Thread Objects to start running
		Thread r=new Thread(reader);
		Thread w=new Thread(writer);
		
		//Start the threads to run
		r.start();
		w.start();

	}
}
