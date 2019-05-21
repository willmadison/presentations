@PUT
@Consumes("application/json")
@Operation(summary = "Update an existing pet",
        tags = {"pets"},
        security = @SecurityRequirement(
                                name = "petstore-auth",
                                scopes = "write:pets"),
        responses = {
                @ApiResponse(
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pet.class))),
                @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                @ApiResponse(responseCode = "404", description = "Pet not found"),
                @ApiResponse(responseCode = "405", description = "Validation exception") }
)
public Response updatePet(
    @RequestBody(description = "Pet object that needs to be added to the store", required = true,
                            content = @Content(
                                    schema = @Schema(implementation = Pet.class))) Pet pet) {
    //..
}