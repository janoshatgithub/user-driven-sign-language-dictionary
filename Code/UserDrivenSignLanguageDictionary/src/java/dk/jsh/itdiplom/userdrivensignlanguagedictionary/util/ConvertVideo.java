package dk.jsh.itdiplom.userdrivensignlanguagedictionary.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Convert video files to the OGG video format.
 * 
 * @author Jan S. Hansen
 */
public class ConvertVideo {
    static final Logger logger = Logger.getLogger(ConvertVideo.class.getName());
    
    /**
     * Convert video file til ogg file format.
     * 
     * @param source Source file name
     * @param dest Destination file name
     */
    public boolean convert(String source, String dest)  {
        Runtime runtime = Runtime.getRuntime();
        //TODO: location of ffmpeg2theora-0.28.exe in a properties file
        StringBuilder cmdLine = new StringBuilder();
        cmdLine.append("\\GoogleCode\\user-driven-sign-language-dictionary");
        cmdLine.append("\\ffmpeg2theora-0.28.exe ");  
        cmdLine.append(" -o ");
        cmdLine.append(dest);
        cmdLine.append(" ");
        cmdLine.append(source);
        try {
            logger.info("Convert video: " + cmdLine.toString());
            Process process = runtime.exec(cmdLine.toString());
            
            //Start thread to read standard error
            ProcessOutput err = new ProcessOutput(process.getErrorStream(), 
                    "ERR");
            Thread errThread = new Thread(err);
            errThread.start();
            
            //Start thread to read standard output
            ProcessOutput std = new ProcessOutput(process.getInputStream(), 
                    "STD");
            Thread stdThread = new Thread(std);
            stdThread.start();

            process.waitFor();
        } catch (Exception exception) {
            logger.log(Level.SEVERE, "Error converting video", exception);
            return false;
        }
        return true;
    }
    
    private class ProcessOutput implements Runnable {
        private InputStream inputStream;
        private String type; 
        
        public ProcessOutput(InputStream inputStream, String type) {
            this.inputStream = inputStream;
            this.type = type;
        }
        
        @Override
        public void run() {
            BufferedReader procesOutput = new BufferedReader(new 
                InputStreamReader(inputStream));
            String line = null;
            try {
                while ((line = procesOutput.readLine()) != null) {
                    logger.info(type + ": " + line);
                }
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Error reading output of type " + type,
                        ex);
            }
        }
    }
}