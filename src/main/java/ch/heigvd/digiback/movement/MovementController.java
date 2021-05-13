package ch.heigvd.digiback.movement;

import ch.heigvd.digiback.movement.credential.MovementCredential;
import ch.heigvd.digiback.util.CsvUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/movement")
public class MovementController {

    private MovementRepository movementRepository;

    public MovementController(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    @PostMapping("/send")
    public MovementCredential uploadSingleCSVFile(@RequestParam("csvfile") MultipartFile csvfile, @RequestBody MovementCredential movementCredential) {

        Response response = new Response();

        // Checking the upload-file's name before processing
        if (csvfile.getOriginalFilename().isEmpty()) {
            response.addMessage(new Message(csvfile.getOriginalFilename(),
                    "No selected file to upload! Please do the checking", "fail"));

            return response;
        }

        // checking the upload file's type is CSV or NOT

        if(!CsvUtil.isCSVFile(csvfile)) {
            response.addMessage(new Message(csvfile.getOriginalFilename(), "Error: this is not a CSV file!", "fail"));
            return response;
        }


        try {
            // save file data to database
            csvFileServices.store(csvfile.getInputStream());
            response.addMessage(new Message(csvfile.getOriginalFilename(), "Upload File Successfully!", "ok"));
        } catch (Exception e) {
            response.addMessage(new Message(csvfile.getOriginalFilename(), e.getMessage(), "fail"));
        }

        return response;
    }
}
