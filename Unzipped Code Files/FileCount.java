import java.util.*;
import java.io.*;

/**
 * This is a class to count the number of files and folders in a directory 
 * with option to specify file extension. 
 * @author TIanhan Wang
 *
 */
public class FileCount {
	
	//keep track of the number of files and directory
	static int fcount=0;
	static int dcount=0;
	
	public static void main(String[] args) {
		Criteria test=new FileCount().new Criteria("C:\\Tianhan Wang\\Probability", true, ".pdf");
		count(test);
	}
	public static void count(Criteria c) {
		File file=new File(c.dirPath);
		
		//check to see if the file path is valid
		if(!file.exists() || !file.isDirectory()) {
			System.out.println("Not a valid Directory");
			return;
		}
		
		filter f=new filter(c.extension);
		boolean recursive=c.includeSub;
		fileExplore(file,f,recursive);
		System.out.println("There is a total of "+fcount+" files with the extension "+c.extension+"\nAnd a total of "+dcount+" sub-folder(s) in current folder.");
	}
	
	/**
	 * this is a recurisve method to count the number of files and sub-folder in a directory
	 * @param file
	 * @param filter
	 * @param recursive
	 */
	public static void fileExplore(File file,FileFilter filter,boolean recursive) {
		File[] files=file.listFiles(filter);
		fcount+=files.length;
		File[] directory=file.listFiles(new dFilter());
		dcount+=directory.length;
		if(directory.length>0) {
			if(recursive) {
				for(File f:directory) {
					fileExplore(f,filter,recursive);
				}
			}
		}
	}
	
	/**
	 * This is a filter class to filter the file type by looking at the 
	 * extension
	 * @author TIanhan Wang
	 *
	 */
	public static class filter implements FileFilter{
		
		String suffix;
		
		public filter(String c) {
			this.suffix=c;
		}
		
		@Override
		public boolean accept(File pathname) {
			if(pathname.getName().toLowerCase().endsWith(suffix)) {
				return true;
			}
			return false;
		}
		
	}
	
	/**
	 * this is another filter class to search for sub-directories
	 * @author TIanhan Wang
	 *
	 */
	public static class dFilter implements FileFilter{

		@Override
		public boolean accept(File pathname) {
			if(pathname.isDirectory()) {
				return true;
			}
			return false;
		}
	}
	
	/**
	 * this is the for Criteria which defines the search condition in 
	 * a folder
	 * @author TIanhan Wang
	 *
	 */
 	private class Criteria{
		String dirPath;
		Boolean includeSub;
		String extension;
		
		public Criteria(String path,Boolean includesub,String extension) {
			this.dirPath=path;
			this.includeSub=includesub;
			this.extension=extension;
		}

		@Override
		public String toString() {
			return "Criteria [dirPath=" + dirPath + ", includeSub=" + includeSub + ", extension=" + extension + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(dirPath, extension, includeSub);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Criteria other = (Criteria) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return Objects.equals(dirPath, other.dirPath) && Objects.equals(extension, other.extension)
					&& Objects.equals(includeSub, other.includeSub);
		}

		private FileCount getEnclosingInstance() {
			return FileCount.this;
		}	
	}
}
