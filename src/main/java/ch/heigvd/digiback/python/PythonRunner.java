package ch.heigvd.digiback.python;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class PythonRunner {
    private static final Logger logger = LoggerFactory.getLogger(PythonRunner.class);

    private static PythonRunner pythonRunner;
  private static ProcessBuilder processBuilder =
      new ProcessBuilder(
          "py",
          "C:\\Users\\clari\\Documents\\HEIG\\BA6\\TB\\java_backend\\src\\main\\java\\ch\\heigvd\\digiback\\python\\base.py");

    private PythonRunner() {}

    public static PythonRunner getInstance() {
        if (pythonRunner == null) {
            pythonRunner = new PythonRunner();
        }
        return pythonRunner;
    }

    public static String run() throws InterruptedException {
        logger.info("Running");
        processBuilder.redirectErrorStream(true);

        Process process = null;
        String results = null;

        try {
            process = processBuilder.start();
            results = readProcessOutput(process.getInputStream());
        } catch (IOException e) {
            logger.error("An error occurred : " + e.getMessage());
        }

        logger.info("Results : " + results);

        int exitCode = process.waitFor();
        logger.info("Number of errors : " + exitCode);
        return results;
    }

    private static String readProcessOutput(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        long end = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(5);

        while (System.currentTimeMillis() < end && (line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        inputStream.close();
        return stringBuilder.toString();
    }
}
