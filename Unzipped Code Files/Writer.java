import java.util.*;
import java.io.*;
import java.util.concurrent.*;
/**
 * 
 * @author TIanhan Wang
 *This class is a Wrtier class to write to output file from a Buffer
 */
public class Writer implements Runnable {
	//Store the queue to take from
	BlockingQueue<String> q;
	
	//initialize it with the parameter queue
	public Writer(BlockingQueue q) {
		this.q=q;
	}
	
	@Override
	public void run() {
		//use Printwriter to write to file
		PrintWriter w=null;
		
		try {
			w=new PrintWriter(new File("C:\\Users\\TIanh\\Desktop\\Antra\\output.txt"));
			while(true) {
				
				String buffer=q.take();
				//test to see if it is the end of the file
				if(buffer.equals("Poison Pill")) {
					break;
				}
				//write to the output
				w.println(buffer);
			}
		}catch (FileNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}finally {
			//close the writer in prevention of resource leak
			w.close();
		}
	}
}
