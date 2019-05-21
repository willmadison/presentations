@GET
@Path("/")
@Operation(operationId = "operationId",
        summary = "Operation Summary",
        description = "Operation Description",
        tags = {"Example Tag", "Second Tag"},
        externalDocs =
        @ExternalDocumentation(
                description = "External documentation description",
                url = "http://url.com"
        ),
        parameters = {
                @Parameter(in = "path", name = "subscriptionId",
                        required = true, description = "parameter description",
                        allowEmptyValue = true, allowReserved = true,
                        schema = @Schema(
                                type = "string",
                                format = "uuid",
                                description = "the generated UUID",
                                accessMode = Schema.AccessMode.READ_ONLY)
                )},
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "voila!",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponsesResource.SampleResponseSchema.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "default",
                        description = "boo",
                        content = @Content(
                                mediaType = "*/*",
                                schema = @Schema(implementation = ResponsesResource.GenericError.class)
                        )
                )
        }
)
public Response getSummaryAndDescription() {
    return Response.ok().entity("ok").build();
}