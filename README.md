To-Do zum Installieren der Minecraft Mod „Twitch Chat“. (Änderungen im Namen sind Vorbehalten)
Warnung: Die Werte, die du während der Installation von den verschiedenen Webseiten holst, 
haben volle Rechte auf deinen Account. 
Bitte beachte, dass diese nicht in fremde Hände geraten. 
Wenn du mit der Mod nur lesen möchtest, kannst du bei Punkt 5. a) und b) weglassen.

1.	Die Mod herunterladen und in den /mods/ Ordner deiner Forge Installation ziehen.
2.	Forge ausführen und nach dem Start wieder beenden.
3.	Auf der Website https://dev.twitch.tv/console/apps eine Anwendung registrieren.

1)	Name = Egal. 
2)	OAuth Redirect URLs = http://localhost
3)	Kategorie = Chat Bot
4)	Ich bin kein Roboter = Haken
5)	Erstellen

4.	Bei der eben erstellten Anwendung auf „Verwalten“ klicken.

5.	Im Ordner /config/ die Werte von der Website eintragen:
1)	clientId = Client-ID
2)	clientSecret = Neues Geheimnis erzeugen und den Wert eintragen.
3)	accessToken = Auf der Website https://www.twitchapps.com/tmi dich mit deinem Twitch Account anmelden und 
    die Zeichenkette startend mit „oauth:xxx“ eingeben.
4)	channel = Dem Channel, dem du zugucken möchtest. Achtung: nur Kleinbuchstaben.
5)	message = Hier wurde schon ein Standard angegeben. Farbcodes können mit $+Zeichen angegeben werden. %User% wird mit dem Nachrichten Sender ersetzt und %Message% mit der Nachricht.
6)	toIgnore = Hier ist eine Liste mit Twitch Accounts, die nicht in Minecraft angezeigt werden sollen. Diese kann während des Spielens mit #ignore oder #unignore verändert werden.

Während des Spielens kann man sich mit #help eine Hilfe Übersicht ausgeben lassen.

Viel Spaß mit der Mod.
