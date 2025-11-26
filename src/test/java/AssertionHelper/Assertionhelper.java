package AssertionHelper;
import io.restassured.response.Response;
import org.testng.Assert;

public class Assertionhelper {


    public static void verifyStatus(Response response, int expectedCode) {
        Assert.assertEquals(response.statusCode(), expectedCode, "Status code mismatch");
    }

    public static void verifyMessage(Response response, String expectedMessage) {
        Assert.assertEquals(response.jsonPath().getString("message"), expectedMessage,
                "--------------Incorrect message-----------");
    }
}
