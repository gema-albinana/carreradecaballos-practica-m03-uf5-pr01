package com.lasalle.horseracinggui;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController {

    @FXML
    ImageView background;

    @FXML
    ImageView crupierHand;

    @FXML
    ImageView drawnCard;

    @FXML
    ImageView iconNote;

    @FXML
    Label summaryRound;

    @FXML
    ImageView knightGoldCard;

    @FXML
    ImageView knightCupsCard;

    @FXML
    ImageView knightClubsCard;

    @FXML
    ImageView knightSwordsCard;

    // Solo para probar el ejemplo, solo para ilustrar como se movería las cartas;
    private int pos = 0;

    /* TODO:
        En el modelo CardsDeck, hay que crear una colección private Map<String, Image> cardImages = new HashMap<>();
        Creo que es la colección que mas se ajusta a las necesidades, mientras se genera la baraja o en un método
        aparte inicial recorrer la baraja y obtener el código de la carta, que nos devolverá ejm: 1_CLUBS
        después obtenemos la imagen de la carta:
        Image cardImage = new Image(getClass().getResource("/images/"+ cardCode +".png").toExternalForm());
        ...Y agregamos a la colección
        cardImages.put(cardCode, cardImage);
        Así tendremos una colección asociado con clave valor "1_CLUBS" -> Imagen, "3_GOLD" -> Imagen ...

        Se deberá crear un método public Image getImageCard(String cardCode);
        Devolverá la imagen que esté asociada en el Map.
        return cardImages.get(cardCode);

        Cuando visualmente debamos actualizar una carta del mazo cuando el crupier saca una carta podremos usarlo por
        ejemplo así:
            card = cardsDeck.getCardFromDeck();
            o
            card = cardsDeck.getSpecificCard(1);

            drawnCard.setImage(cardsDeck.getImageCard(card.getCardCode));

            De la misma manera podemos posicionar los caballos en el tablero.
     */

    @FXML
    public void initialize() {
        /* TODO: los caballos están visualizados de forma directa simplemente para ver que todo esta visible y funciona
             habrá que utilizar la lógica de negocio de los modelos para obtener los caballos y mostralos, etc.
        */


        Image backgroundImage = new Image(getClass().getResource("/images/background.jpg").toExternalForm());
        Image crupierImage = new Image(getClass().getResource("/images/crupierhand.png").toExternalForm());
        Image drawnCardImage = new Image(getClass().getResource("/images/7_CUPS.png").toExternalForm());
        Image iconNoteImage = new Image(getClass().getResource("/images/note.png").toExternalForm());

        Image KGoldCardImage = new Image(getClass().getResource("/images/KNIGHT_GOLD.png").toExternalForm());
        Image KCupsCardImage = new Image(getClass().getResource("/images/KNIGHT_CUPS.png").toExternalForm());
        Image KClubsCardImage = new Image(getClass().getResource("/images/KNIGHT_CLUBS.png").toExternalForm());
        Image KSwordsCardImage = new Image(getClass().getResource("/images/KNIGHT_SWORDS.png").toExternalForm());

        // Cards
        knightGoldCard.setImage(KGoldCardImage);
        knightCupsCard.setImage(KCupsCardImage);
        knightClubsCard.setImage(KClubsCardImage);
        knightSwordsCard.setImage(KSwordsCardImage);

        // GUI
        background.setImage(backgroundImage);
        crupierHand.setImage(crupierImage);
        crupierHand.setCursor(Cursor.HAND);
        drawnCard.setImage(drawnCardImage);
        iconNote.setImage(iconNoteImage);
    }

    @FXML
    public void onMouseMoveCrupierHand(MouseEvent mouseEvent){
        Image crupierImageHover = new Image(getClass().getResource("/images/crupierhandhover.png").toExternalForm());
        crupierHand.setImage(crupierImageHover);
    }
    @FXML
    public void onMouseOutCrupierHand(MouseEvent mouseEvent){
        Image crupierImageHover = new Image(getClass().getResource("/images/crupierhand.png").toExternalForm());
        crupierHand.setImage(crupierImageHover);
    }

    @FXML
    public void onMouseClickCrupierHand(MouseEvent mouseEvent){
        // TODO: Obtener carta del mazo, visualizar la carta, mover caballo, etc. Esto a través de los modelos que
        //  ya tenemos hecho, aunque cambiando cosas.

        if (pos == 0) {
            // La primera posición de la carta va a la coordenadaX 190 que es la casilla 1
            knightCupsCard.setX(190);
            pos++;
        } else {
            // El resto se calcula desde la posicion 1 que es 190, se le suma,
            // la nueva posición * 103pixeles que es la separación entre cartas.
            // la clase board hay que cambiarla para que nos devuelva la posición del caballo que se mueve, y
            // así posicionar los caballos en sus celdas.
            // Sería lo suyo sacar esto a un metodo aparte como paintCardInPosition(int pos, ImageView imageCard).
            double newPosX = 190 + (pos * 103);
            knightCupsCard.setX(newPosX);
            pos++;
            System.out.println("newPosX: " + newPosX + " pos: " + pos);
        }


        summaryRound.setText("El crupier saca otra carta...");


    }
}