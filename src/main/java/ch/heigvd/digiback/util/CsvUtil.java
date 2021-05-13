package ch.heigvd.digiback.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.Writer;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface CsvUtil<R> {
    String csvExtension = "csv";

    void ToCsv(Writer writer, List<R> val) throws IOException;

    List<R> parseCsvFile(InputStream is);

    static boolean isCSVFile(MultipartFile file) {
        String extension = file.getOriginalFilename().split("\\.")[1];

        if(!extension.equals(csvExtension)) {
            return false;
        }

        return true;
    }

}
