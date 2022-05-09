/**
 * @author TIanhan Wang
 */
import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
public class Reader implements Runnable {
	//a queue to serve as the buffer
	private BlockingQueue<String> q;
	
	//initialized the queue within constructor
	public Reader(BlockingQueue q) {
		this.q=q;
	}

	@Override
	public void run() {
		//Use a Bufferreader to read from a file
		BufferedReader r=null;
		try {
			r=new BufferedReader(new FileReader(new File("C:\\Users\\TIanh\\Desktop\\Antra\\input.txt")));
			String buffer=null;
			while((buffer=r.readLine())!=null) {
				//escape the hidden /n,/r in the file
				if(buffer.equals("")){
					continue;
				}
		
				String result=calculate(buffer);
				q.put(result);
			}
			
			//let the writer know if the end of the file has been reached.
			q.put("Poison Pill");
			
			//Multiple catch cluase to catch exceptions
		}catch(IOException | InterruptedException e) {
			e.printStackTrace();
		}finally {
			try {
				//close the reader to prevent resource leak
				r.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method calculates the value of the String math expression
	 * @param buffer:a single line in the file
	 * @return calculated value as a String
	 */
	private String calculate(String buffer) {
		String s=buffer.replaceAll("\\s+", "");
		// use regex to split strings into operands and operators
		String operators[]=s.split("[0-9]+");
		String operands[]=s.split("[+-]");
		
		//take the first operand as a start
		int sum=Integer.parseInt(operands[0]);

		for(int i=1;i<operands.length;i++) {
			
			if(operators[i].equals("+")) {
				sum+=Integer.parseInt(operands[i]);
			}else {
				sum-=Integer.parseInt(operands[i]);
			}
		}
		//form the result String
		s+="="+sum;
		return s;
	}
}
