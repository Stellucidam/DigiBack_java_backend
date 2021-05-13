package ch.heigvd.digiback.movement.seralizer;

import ch.heigvd.digiback.movement.MovementData;
import ch.heigvd.digiback.util.CsvUtil;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovementDataCsvSerializer implements CsvUtil<MovementData> {
    @Override
    public void ToCsv(Writer writer, List<MovementData> val) throws IOException {

    }

    @Override
    public List<MovementData> parseCsvFile(InputStream is) {
        String[] CSV_HEADER = {
                "Time (s)",
                "Linear Acceleration x (m/s^2)",
                "Linear Acceleration y (m/s^2)",
                "Linear Acceleration z (m/s^2)",
                "Absolute acceleration (m/s^2)"
        };
        Reader fileReader = null;
        CsvToBean<MovementData> csvToBean = null;

        List<MovementData> movements = new ArrayList<MovementData>();

        try {
            fileReader = new InputStreamReader(is);

            ColumnPositionMappingStrategy<MovementData> mappingStrategy = new ColumnPositionMappingStrategy<>();

            mappingStrategy.setType(MovementData.class);
            mappingStrategy.setColumnMapping(CSV_HEADER);

            csvToBean = new CsvToBeanBuilder<MovementData>(fileReader)
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
