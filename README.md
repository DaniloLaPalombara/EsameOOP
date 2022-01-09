# TempInformation
AGGIUNGERE QUALCOSA
# SCALETTA DEI CONTENUTI
- Introduzione
- Descrizione API
- Descrizione applicazione
- Rotte
- Autori

# INTRODUZIONE
L'applicazione implementa un servizio meteo incentrato sulle temperature che permette di ottenere dati relativi alla temperatura attuale e di accedere ad uno storico sulle temperature di una città. Inoltre è possibile generare statistiche e filtrare quest'ultime in base alla periodicità: fascia oraria, giorno e settimanale.

# DESCRIZIONE API
Il servizio di cui si è usufruito è OpenWeather ed in particolare di due API:
1. quella relativa alla temperatura corrente di una città tramite Id, ovvero api.openweathermap.org/data/2.5/weather?id={city id}&appid={API key}
dove:
- city id è l'dentificatore della città selezionata
- API key è la chiave di accesso al servizio

2. quella relativa allo storico delle temperature di una città tramite Id, ovvero http://history.openweathermap.org/data/2.5/history/city?id={id}&type=hour&start={start}&end={end}&appid={API key}
dove:
- city id è l'identificatore della città selezionata
- start è l'orario di inizio del raccoglimento dei dati in formato UNIX
- stop è l'orario di fine del raccoglimento dei dati in formato UNIX
- API key è la chiave di accesso al servizio

# DESCRIZIONE APPLICAZIONE
Come precedentemente esposto, l'applicazione si concentra sulla raccolta e l'analisi delle temperature di una città ed in particolare sulla temperatura:
- attuale
- massima
- minima
- percepita
 
Ciò è possibile farlo sia sulle temperature correnti, sia su uno storico delle temperature.
L'applicazione consente anche di confrontare lo storico sulla temperatura effettiva e percepita in un dato intervallo temporale, con le informazioni attuali sempre sulle temperature e di effettuare statistche riguardanti:
- valori massimi
- valori minimi
- media
- varianza

di temperature effettive e percepite. Inoltre si da la possibilità all'utente di filtrare le statistiche in base alla periodicità:
- fascia oraria 
- giorni
- settimanale

# ROTTE
Nella tabella sottostante sono elencate le varie rotte messe a disposizione all'utente:
<table><tr><td class="border_l border_r border_t border_b selected" style="text-align: center; opacity: 1;"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>Rotte</span></p></div></div></td><td class="border_l border_r border_t border_b selected" style="text-align: center;"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>Descrizione</span></p></div></div></td></tr><tr><td class="border_l border_r border_t border_b selected" style="text-align: left; opacity: 1;"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>/CurrentTemp</span></p></div></div></td><td class="border_l border_r border_t border_b selected" style="text-align: left;"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>Restituisce i dati riguaradanti la temperatura corrente di una città</span></p></div></div></td></tr><tr><td class="border_l border_r border_t border_b selected" style="text-align: left; opacity: 1;"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>/HistoryTemp</span></p></div></div></td><td class="border_l border_r border_t border_b selected"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>Restituisce i dati riguaradanti lo storico delle temperature in </span></p><p><span>una fascia oraria di una città</span></p></div></div></td></tr><tr><td class="border_l border_r border_t border_b selected" style="text-align: left; opacity: 1;"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>/HourlySaving&amp;Statistics</span></p></div></div></td><td class="border_l border_r border_t border_b selected"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>Salva localmente e restituisce le statistiche sulle temperature attuali e</span></p><p><span>percepite effettuate in una fascia oraria</span></p></div></div></td></tr><tr><td class="border_l border_r border_t border_b selected" style="text-align: left; opacity: 1;"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>/HourlyStatistics</span></p></div></div></td><td class="border_l border_r border_t border_b selected"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>Restituisce le statistiche sulle temperature attuali e percepite </span></p><p><span>effettuate in una fascia oraria</span></p></div></div></td></tr><tr><td class="border_l border_r border_t border_b selected" style="text-align: left; opacity: 1;"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>/DailySaving&amp;Statistics</span></p></div></div></td><td class="border_l border_r border_t border_b selected"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>Salva localmente e restituisce le statistiche sulle temperature attuali e </span></p><p><span>percepite effettuate nell'arco di una giornata</span></p></div></div></td></tr><tr><td class="border_l border_r border_t border_b selected" style="text-align: left; opacity: 1;"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>/DailyStatistics</span></p></div></div></td><td class="border_l border_r border_t border_b selected"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>restituisce le statistiche sulle temperature attuali e percepite </span></p><p><span>effettuate nell'arco di una giornata</span></p></div></div></td></tr><tr><td class="border_l border_r border_t border_b selected" style="text-align: left; opacity: 1;"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>/WeekSaving&amp;Statistics</span></p></div></div></td><td class="border_l border_r border_t border_b selected"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>Salva localmente e restituisce le statistiche sulle temperature attuali e</span></p><p><span>percepite effettuate nell'arco di una settimana</span></p></div></div></td></tr><tr><td class="border_l border_r border_t border_b selected" style="text-align: left; opacity: 1;"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>/WeekStatistics</span></p></div></div></td><td class="border_l border_r border_t border_b selected"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>Restituisce le statistiche sulle temperature attuali e percepite</span></p><p><span>effettuate nell'arco di una settimana</span></p></div></div></td></tr><tr><td class="border_l border_r border_t border_b selected" style="text-align: left;"><div class="wrap"><div style="margin: 10px 5px;" class="" contenteditable="false"><p><span>/TempCompare</span></p></div></div></td><td class="border_l border_r border_t border_b selected"><div class="wrap"><div class="" contenteditable="false" style="margin: 10px 5px;"><p><span>Restituisce le statistiche sulle temperature attuali e percepite </span></p><p><span>effettuate in una fascia oraria, confrontate con i dati della temperatura attuale</span></p></div></div></td></tr></table>

# 1. /CurrentTemp
METTERE IMMAGINE
# 2. /HistoryTemp
METTERE IMMAGINE
# 3. /HourlySaving&Statistics e /HouryStatistics
METTERE IMMAGINE
# 4. /DailySaving&Statistics e /DailyStatistics
METTERE IMMAGINE
# 5. /WeekSaving&Statistics e /WeekStatistics
METTERE IMMAGINE
# 6. /TempCompare
METTERE IMMAGINE 

# AUTORI
Danilo La Palombara - Contributo 50%

Nicolò Ianni - Contributo 50%
