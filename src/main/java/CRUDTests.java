import io.restassured.http.ContentType;
import model.Card;
import model.Deleted;
import model.List;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import model.Board;

public class CRUDTests {

    public String idBoard = null;
    public String idListToDo = null;
    public String idListDone = null;
    public String idCard = null;
    public String cardName = null;

    @Test(priority = 0)
    public void CreateANewBoard(){
        String boardName = "Postman Board from Java";
        String URL = Constants.URI+ Constants.BOARDS_ENDPOINT +"?name="+ boardName +"&defaultLists=false&"+ Constants.AUTHENTICATION;

        Board board = given().contentType(ContentType.JSON)
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(Board.class);

        idBoard = board.getId();
        assertThat(board.getName()).isEqualTo(boardName);
    }

    @Test(priority = 1)
    public void CreateAToDoList(){
        String toDoListName = "To Do from Java";
        String URL = Constants.URI+ Constants.LISTS_ENDPOINT +"?name="+ toDoListName +"&idBoard="+ idBoard + "&" + Constants.AUTHENTICATION;

        List toDoList = given().contentType(ContentType.JSON)
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(List.class);

        idListToDo = toDoList.getId();
        assertThat(toDoList.getClosed()).isEqualTo("false");
        assertThat(toDoList.getName()).isEqualTo(toDoListName);
    }

    @Test(priority = 2)
    public void CreateADoneList(){
        String doneListName = "Done from Java";
        String URL = Constants.URI+ Constants.LISTS_ENDPOINT +"?name="+ doneListName +"&idBoard="+ idBoard + "&" + Constants.AUTHENTICATION;

        List toDoList = given().contentType(ContentType.JSON)
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(List.class);

        idListDone = toDoList.getId();
        assertThat(toDoList.getClosed()).isEqualTo("false");
        assertThat(toDoList.getName()).isEqualTo(doneListName);
    }

    @Test(priority = 3)
    public void PostACardInAToDoList(){
        cardName = "Postman Card from Java";
        String URL = Constants.URI+ Constants.CARDS_ENDPOINT +"?name="+ cardName +"&idList="+ idListToDo + "&" + Constants.AUTHENTICATION;

        Card card = given().contentType(ContentType.JSON)
                .post(URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(Card.class);

        idCard = card.getId();
        assertThat(card.getName()).isEqualTo(cardName);
        card.setName(cardName);
    }

    @Test(priority = 4)
    public void MoveCardToDoneList(){
        String URL = Constants.URI+ Constants.CARDS_ENDPOINT + idCard + "?idList="+ idListDone + "&" + Constants.AUTHENTICATION;

        Card card = given().contentType(ContentType.JSON)
                .put(URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(Card.class);

        idCard = card.getId();
        assertThat(card.getName()).isEqualTo(cardName);
    }

    @Test(priority = 5)
    public void DeleteABoard(){
        String URL = Constants.URI+ Constants.BOARDS_ENDPOINT + idBoard + "?" + Constants.AUTHENTICATION;
        Deleted boardDeleted = given().contentType(ContentType.JSON)
                .delete(URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(Deleted.class);

        assertThat(boardDeleted.get_value()).isEqualTo(null);
    }

    @Test(priority = 6)
    public void ValidateBoardIsNotDisplayed(){
        String URL = Constants.URI+ Constants.BOARDS_ENDPOINT + idBoard +"?"+ Constants.AUTHENTICATION;

        given()
            .get(URL)
            .then()
            .assertThat()
            .statusCode(404).body(containsString("The requested resource was not found."));
    }
}
