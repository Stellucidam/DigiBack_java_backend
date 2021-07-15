package ch.heigvd.digiback.business.movement.seralizer;

import ch.heigvd.digiback.business.movement.Angle;
import ch.heigvd.digiback.util.CsvUtil;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovementDataCsvSerializer implements CsvUtil<Angle> {
    private final static String[] CSV_HEADER = {
            "Time (s)",
            "Linear Acceleration x (m/s^2)",
            "Linear Acceleration y (m/s^2)",
            "Linear Acceleration z (m/s^2)",
            "Absolute acceleration (m/s^2)"
    };

    @Override
    public void ToCsv(Writer writer, List<Angle> val) throws IOException {

    }

    @Override
    public List<Angle> parseCsvFile(InputStream is) {
        Reader fileReader = null;
        CsvToBean<Angle> csvToBean = null;
        List<Angle> movements = new ArrayList<Angle>();

        try {
            fileReader = new InputStreamReader(is);

            ColumnPositionMappingStrategy<Angle> mappingStrategy = new ColumnPositionMappingStrategy<>();

            mappingStrategy.setType(Angle.class);
            mappingStrategy.setColumnMapping(CSV_HEADER);

            csvToBean = new CsvToBeanBuilder<Angle>(fileReader)
                    .withMappingStrategy(mappingStrategy)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            movements = csvToBean.parse();

            return movements;
        } catch (Exception e) {
            System.out.println("Reading CSV Error!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Closing fileReader/csvParser Error!");
                e.printStackTrace();
            }
        }

        return movements;
    }
}
