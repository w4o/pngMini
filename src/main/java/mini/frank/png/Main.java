package mini.frank.png;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tinify.Source;
import com.tinify.Tinify;

public class Main {
	
	private static final String DOT = ".";  
	private static final String PNG = ".png";
	private static final List < String > fileList = new ArrayList<String>();

	public static void main(String[] args) {
		
		
		if (args == null || args[0] == null) {
			System.out.println("至少需要一个参数，参数是要压缩图片的目录");
			System.exit(0);
		}
		
		getDir(args[0]);
		
		Tinify.setKey("OHk05-yb5V-PbL0r9fbyDuRPFOAZttxG");
		
		System.out.println(fileList.size());
		
		for (String str : fileList) {
			Source source;
			try {
				System.out.println(str);
				source = Tinify.fromFile(str);
				source.toFile(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
		

	}

	/**
	 * 递归目录
	 * @param strPath
	 */
	public static void getDir(String strPath) {
		File f = new File(strPath);
		if (f.isDirectory()) {
			File[] fList = f.listFiles();
			for (int j = 0; j < fList.length; j++) {
				if (fList[j].isDirectory())
					getDir(fList[j].getPath());
				if (isPng(fList[j].getName())) 
					fileList.add(fList[j].getPath());
			}
		}
	}
	
	/** 
     * 获取扩展名 
     *  
     * @param fileName 
     * @return 
     */  
    private static String getExtension(String fileName) {  
        if (StringUtils.INDEX_NOT_FOUND == StringUtils.indexOf(fileName, DOT))  
            return StringUtils.EMPTY;  
        String ext = StringUtils.substring(fileName,  
                StringUtils.lastIndexOf(fileName, DOT));  
        return StringUtils.trimToEmpty(ext.toLowerCase());  
    } 
    
    private static boolean isPng(String filename) {
    	return PNG.equals(getExtension(filename));
    }

}
