package org.example.deskbooker.soap.endpoint;

import org.example.deskbooker.AddOfficeRequest;
import org.example.deskbooker.AddOfficeResponse;
import org.example.deskbooker.ListAllOfficesRequest;
import org.example.deskbooker.ListAllOfficesResponse;
import org.example.deskbooker.model.Desk;
import org.example.deskbooker.model.Office;
import org.example.deskbooker.model.enumeration.OfficeTypeEnum;
import org.example.deskbooker.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class OfficeEndpoint {
    private static final String NAMESPACE_URI = "http://www.example.org/deskbooker";

    private final OfficeService officeService;

    @Autowired
    public OfficeEndpoint(OfficeService officeService) {
        this.officeService = officeService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddOfficeRequest")
    @ResponsePayload
    public AddOfficeResponse addOffice(@RequestPayload AddOfficeRequest request) {
        AddOfficeResponse response = new AddOfficeResponse();
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new IllegalArgumentException("Office name cannot be null or empty");
        }

        OfficeTypeEnum type = OfficeTypeEnum.valueOf(request.getType());
        Office office = new Office();
        office.setName(request.getName());
        office.setType(type);

        office = officeService.addOffice(office);
        response.setId(office.getId());
        response.setMessage("Office added successfully");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ListAllOfficesRequest")
    @ResponsePayload
    public ListAllOfficesResponse listAllOffices(@RequestPayload ListAllOfficesRequest request) {
        ListAllOfficesResponse response = new ListAllOfficesResponse();

        List<Office> offices = officeService.getAllOffices();

        for(Office office : offices) {
            ListAllOfficesResponse.Office officeResponse = new ListAllOfficesResponse.Office();
            officeResponse.setId(office.getId());
            officeResponse.setName(office.getName());
            officeResponse.setType(office.getType().name());

            List<ListAllOfficesResponse.Office.Desk> deskResponseList = new ArrayList();
            for(Desk desk : office.getDesks()) {
                ListAllOfficesResponse.Office.Desk deskResponse = new ListAllOfficesResponse.Office.Desk();
                deskResponse.setId(desk.getId());
                deskResponse.setPosition(desk.getPosition());
                deskResponseList.add(deskResponse);
            }

            officeResponse.getDesk().addAll(deskResponseList);

            response.getOffice().add(officeResponse);
        }

        return response;
    }
}
