package org.aleksa.employee;

import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    EmployeeService employeeService;

    @GET
    public Response getEmployees(@QueryParam("name") String name) {
        if (name != null && !name.isEmpty()) {
            return Response.ok(employeeService.getEmployeesByName(name)).build();
        } else {
            return Response.ok(employeeService.getEmployees()).build();
        }
    }

    @POST
    public Response insertEmployee(@Valid Employee employee) {
        employeeService.insertEmployee(employee);
        return Response.status(Response.Status.CREATED).entity(employee).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateEmployee(@PathParam("id") Integer id, @Valid Employee employee) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(id, employee);
            return Response.ok(updatedEmployee).build();
        } catch (EntityNotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") Integer id) {
        try {
            employeeService.deleteEmployee(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
