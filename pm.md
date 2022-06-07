# Online pong - Oliver Lundqvist 2022-06-02

## Inledning

Syftet med arbetet var att skapa en version av pong som går att spela över nätet. Den största utmaningen och läromålet ligger i att skapa någonting interaktivbart över nätet istället för bara en simpel chatt.

Allt är skrivet i java.
## Bakgrund

Först gjordes en grundläggande planering om vilka klasser som skulle ingå och vilka objekt som skulle finnas.
Metoden att skicka information över nätet är mestadels tagen från chattprogrammet som jag gjort tidigare och anävnder sig av sockets och en serverthread för att sätta upp kopplingen mellan spelarna. Threads används även mycket för att få skickandet av information att bli mer rullande.
Största skillnaden i detta program jämfört med chatprogrammet är att mycket mer information måste skickas över och mycket mer spcifik information, då information om spelobjekten, snarare än bara strings som det var i chatprogrammet.

## Positiva erfarenheter

Om någonting skulle jag säga att det som gått bra har varit att konstruera själva pong spelet(vilket är väldigt basic till att börja med) och att min lösning för kommunikationen mellan spelarna fungerar bra nog då min originalidé inte riktigt gick, mer om det i negativa erfarenheter.

Min lösning för att skicka över information blev att bara skicka strings. Klienten kunde t.ex skicka olika form av kommandon då i det här fallet 'MoveAction' vilket servern tog emot och skickade till båda klienterna. Den lösningen gjorde så att spelarna i sig flyttade inte sin pinne utan bad servern flytte den pinnen som den då gjorde åt båda spelare. 

## Negativa erfarenheter

Min originala idé var att servern skulle ta hand om själva spelet medan allt klienterna gör att att skicka information att antingen putta upp eller ner pinnen på sin sida av spelplanen. För att åstakomma detta så tänkte jag direkt skicka över spelobjekten till klienterna som då skickar den till sin view för att rita ut. Detta fick jag aldrig att fungera för att java klagade en massa över att objekt inte vara Serializable vilket till slut ledde till att någon grund java klass inte var det vilket jag själv inte kan påverka. Till slut ändrade jag planer och gick för en string baserad lösning istället.

## Sammanfattning

I slutändan fick jag en produkt som ser lite annorlunda ut än det jag tänkte men som åtminstone fungerar. Lösningen med strings fungerar bara bra men det kan potentiellt strula till över längre distanser, då jag bara testat på localhost/över ett delat wifi och aldrig över en faktisk server. Ut över det så tog de så pass lång tid att klura ut informationsdelen att själva pong spelet i sig är väldigt barebones och uppkopplingen i sig inte heller är perfekt då man måste t.ex starta om hela servern om en spelare disconnectar.
