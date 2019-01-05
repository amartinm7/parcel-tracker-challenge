package es.amm.intrastructure;

import es.amm.domain.Shipment;
import es.amm.domain.Tracking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponse {

    public static ResponseEntity<ResponseMessage> getOKMessage (Shipment shipment){
        final ResponseMessage<Shipment> responseMessage = new ResponseMessage<>(shipment, HttpStatus.OK.value());
        return new ResponseEntity(responseMessage, HttpStatus.OK );
    }

    public static ResponseEntity<ResponseMessage> getOKMessage (Tracking tracking){
        final ResponseMessage<Tracking> responseMessage = new ResponseMessage<>(tracking, HttpStatus.OK.value());
        return new ResponseEntity(responseMessage, HttpStatus.OK );
    }

    public static ResponseEntity<ResponseMessage> getBadRequestMessage (String message){
        final ResponseMessage<String> responseMessage = new ResponseMessage<>(message, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity( responseMessage, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<ResponseMessage> getInternalErrorMessage (){
        final ResponseMessage<String> responseMessage = new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity( responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
