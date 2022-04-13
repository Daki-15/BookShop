# Simple Registration Example
A JavaFX Application demonstrating how to implement a simplistic approach of a registration use case using the following technologies:
* [Java 11](https://www.oracle.com/java/technologies/javase-downloads.html)
* [JavaFX](https://openjfx.io/openjfx-docs/) (as GUI)
* [Maven](https://maven.apache.org/) (as build tools)
* [Nitrite Java](https://www.dizitart.org/nitrite-database.html) (as Database)

#Book Shop
Membrii echipei: Meșter Rebeca, Paunovic Darjan.

#Descriere generală
Această aplicație își propune să ajute toți utilizatorii cărora le place să citească cărți și librăriile
să-și crească veniturile și să atragă cât mai mulți cititori, oferind acces ușor la un număr mare
de cărți.

#Înregistrare (atât pentru compărător, cât şi pentru Librărie)

Utilizatorii trebuie să se înregistreze mai întâi în aplicație selectând unul dintre cele 2 roluri:
utilizator sau manager de magazin. Ambele roluri necesită un nume de utilizator unic, o
parolă și informații de bază precum numele complet, adresa și numărul de telefon.

#Librarie:
- După ce managerul magazinului se conectează, el poate adăuga, edita sau șterge cartea. O
  carte trebuie să conțină numele, numele autorului, genul, editura, prețul și opțional
  imaginea.
- De asemenea, după autentificare, va vedea o listă cu toate cărțile comandate în acea zi.

#Utilizator:
- Un utilizator trebuie să se autentifice în aplicație unde va putea vedea o listă cu toate
  cărțile. Lista ar trebui să poată fi căutată după numele sau genul cărții.
- De asemenea, utilizatorii înregistrați pot selecta orice carte (este posibil să selecteze mai
  multe copii ale aceleiași cărți) și să creeze o comandă care să conțină cărțile selectate.
- El poate lăsa o recenzie la librărie și poate sau nu evalua produsul pe un sistem de rating
  de la 1 la 10