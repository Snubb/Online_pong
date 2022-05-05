# Online pong

## Basic ass pong fast multiplayer genom nätverk.
Kanske slänger in en databas om jag känner för det.
### Klasser
* Host - "Servern" som tar emot och räknar ut allt som händer. Skickar sedan info till spelarna. Kan ses som controller.
* Player - Den som connectar till hosten. Tar emot information från hosten.
* Model - Standard model.
* View - Det som players använder. Får information från hosten om positioner och sånt som de sedan använder
för att rita ut någonting.

### Objekt delen
* Ett racket på något sätt som spelaren styr.
* Boll :D.