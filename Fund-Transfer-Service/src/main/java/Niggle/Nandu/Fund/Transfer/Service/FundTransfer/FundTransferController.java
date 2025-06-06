package Niggle.Nandu.Fund.Transfer.Service.FundTransfer;

import Niggle.Nandu.Fund.Transfer.Service.Service.FundTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/funds")
@Tag(name = "Fund Transfer", description = "APIs for fund transfers")
public class FundTransferController {
    private static final Logger log = LoggerFactory.getLogger(FundTransferController.class);

    @Autowired
    private FundTransferService fundTransferService;

    @PostMapping("/transfer")
    @Operation(summary = "Transfer funds between accounts", description = "Initiates a fund transfer based on the provided request, handling both internal and external transfers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transfer completed successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid transfer request or validation errors",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<String> transferFunds(@RequestBody @Valid FundTransferRequest request, BindingResult bindingResult) {
        log.info("Received transfer request: {}", request);
        if (bindingResult.hasErrors()) {
            log.error("Validation errors: {}", bindingResult.getAllErrors());
            return new ResponseEntity<>("Transfer failed: Validation errors - " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<String> result = fundTransferService.transferFunds(request);
            if (result.isEmpty() || result.get() == null) {
                log.warn("Transfer failed: No response from Account Management Service");
                return new ResponseEntity<>("Transfer failed: No response from Account Management Service", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error processing fund transfer: {}", e.getMessage(), e);
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}