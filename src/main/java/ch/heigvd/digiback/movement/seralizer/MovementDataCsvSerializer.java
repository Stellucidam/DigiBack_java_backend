package ch.heigvd.digiback.movement.seralizer;

import ch.heigvd.digiback.movement.MovementData;
import ch.heigvd.digiback.util.CsvUtil;
import com.opencsv.bean.CsvToBean;

import java.io.*;
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

        List<MovementData> customers = new ArrayList<Customer>();

        try {
            fileReader = new InputStreamReader(is);

            ColumnPositionMappingStrategy<Customer> mappingStrategy = new ColumnPositionMappingStrategy<Customer>();

            mappingStrategy.setType(Customer.class);
            mappingStrategy.setColumnMapping(CSV_HEADER);

            csvToBean = new CsvToBeanBuilder<Customer>(fileReader).withMappingStrategy(mappingStrategy).withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true).build();

            customers = csvToBean.parse();

            return customers;
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

        return customers;
    }
}
