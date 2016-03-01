package webtvlist_converter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		try {
			
			boolean chan_name = true;
			File output = new File("playlist.m3u");

			if (!output.exists()) {
				output.createNewFile();
			}
				
			BufferedReader br = new BufferedReader(new FileReader("webtv list.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(output.getAbsoluteFile()));
			String channelname = new String();
			String channelurl = new String();
			
			bw.write("#EXTM3U\n");
			
			String line = br.readLine();

			    while (line != null) {
			    	
			    	if(chan_name == true){
				    	channelname = line.substring(13);
				    	bw.write("#EXTINF:-1, " + channelname + "\n");
				    	chan_name = false;
			    	}
			    	else {
			    		channelurl = line.substring(4).replace(".ts", ".m3u8");
			    		bw.write(channelurl + "\n");
						chan_name = true;
			    	}

			        line = br.readLine();
			    }
			    
			br.close();
			bw.close();
			
			
		} catch (FileNotFoundException e) {	e.printStackTrace();
		}
		

	}

}
