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
			    	if (line.contains("Channel name:")){
			    		channelname = line.replace("Channel name:", "");
				    	bw.write("#EXTINF:-1, " + channelname + "\n");
				    	System.out.println("Channel " + "\""  + channelname + "\"" + " added.");
			    	}

			    	else if(line.contains("URL:")){
			    		channelurl = line.replace("URL:","").replace(".ts", ".m3u8");
			    		bw.write(channelurl + "\n");
			    		System.out.println("URL for " + "\""  + channelname + "\"" + " added.");
			    	}
			    	else {
			    		System.out.println("Nothing elese to do, reading next line.");
			    	}

			        line = br.readLine();
			    }
			    
			br.close();
			bw.close();
			System.out.println("\nPlaylist.m3u finished. Press enter to finish.");
			System.in.read();
			
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
			System.out.println("\nAn error occured. Check your filename and your permissions.");
			System.in.read();
		}
		

	}

}
