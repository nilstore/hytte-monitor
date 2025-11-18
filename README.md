# Hytte-monitor

Dette prosjektet er et hytte-overvåkningssystem med Android-telefon, Arduino og webserver.

## Funksjoner (planlagt / første prototype)
- Temperatur og fuktighet fra Arduino via Bluetooth
- Enkel webserver på Android-telefonen
- Passordbeskyttet login
- Refresh-knapp for å oppdatere sensorverdier
- Kamera og blits (kommer senere)
- Rele/varmekontroll (kommer senere)

## Struktur
hytte-monitor/
├─ app/ # Android Studio-prosjekt
├─ Arduino/ # Arduino-kode
├─ docs/ # Dokumentasjon, koblingsskjemaer, instruksjoner
├─ README.md # Dette dokumentet
└─ .gitignore # Ignorer build-filer og andre unødvendige filer

## Hvordan starte
1. Koble Android-telefon til PC med USB-debugging aktivert.
2. Åpne `app/`-prosjektet i Android Studio.
3. Kjør prosjektet på telefonen.
4. Åpne webserver på telefonen via nettleser
