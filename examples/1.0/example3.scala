// Sample resource annotation
@Path("/photo.json")
@Api("/photo")
@Produces(Array("application/json"))
class PhotoResourceJSON extends Help
  with PhotoResource

// Sample method annotation
@GET
@Path("/{userId}")
@ApiOperation(
  value = "Fetches photos by UserId", 
  responseClass = "com.wordnik.swagr.sandbox.data.Photo", 
  mutiValueResponse = true, 
  tags = "DM,PD"
)
@ApiErrors(Array(
  new ApiError(code = 400, reason = "Invalid ID supplied"),
  new ApiError(code = 403, reason = "Not Authorized to access User"),
  new ApiError(code = 404, reason = "User not found")))
def getPhotosById(
  @ApiParam(
    value="ID of User whose photos need to be fetched",
    required=true, 
    allowMultiple=false,
    allowableValues="allowedId1,allowedId2,allowedId3")
  @PathParam("userId") userId: String) = {
    Response.ok.entity(PhotoApi.getPhotos(userId)).build
  }