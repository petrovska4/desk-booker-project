package org.example.deskbooker.soap.endpoint;

import org.example.deskbooker.UpdateDeskRequest;
import org.example.deskbooker.UpdateDeskResponse;
import org.example.deskbooker.model.Desk;
import org.example.deskbooker.model.Office;
import org.example.deskbooker.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class DeskEndpoint {
    private static final String NAMESPACE_URI = "http://www.example.org/deskbooker";

    private DeskService deskService;

    @Autowired
    public DeskEndpoint(DeskService deskService) {
        this.deskService = deskService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateDeskRequest")
    @ResponsePayload
    public UpdateDeskResponse updateDesk(@RequestPayload UpdateDeskRequest request) {
        UpdateDeskResponse response = new UpdateDeskResponse();

        Desk desk = new Desk();
        desk.setId(request.getId());
        desk.setPosition(request.getPosition());
        Office office = new Office();
        office.setId(request.getOfficeId());
        desk.setOffice(office);

        deskService.updateDesk(desk);

        response.setId(desk.getId());
        response.setMessage("Desk updated");
        return response;
    }
}
