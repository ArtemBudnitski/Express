# Aplikacja Express PL
- Android 9(SDK28)

| Express Main Screen | Express List |
| ---- | ---- |
| ![Screenshot_2024-04-10-10-14-45-86_fa95cc535ef60e469b0a9068620f2956](https://github.com/ArtemBudnitski/Express/assets/126951785/e466efba-484d-46de-bf9f-3d2c233e4aba) | ![Screenshot_2024-04-10-10-14-28-65_fa95cc535ef60e469b0a9068620f2956](https://github.com/ArtemBudnitski/Express/assets/126951785/56ada1c3-0519-433d-9395-dfb3aecc5989) |



## Technologie

- Język programowania: Kotlin.
- Wykorzystane technologie: Android Jetpack (Compose, ViewModel), Retrofit do komunikacji z API, Room do lokalnego przechowywania danych, Kotlin Coroutines do obsługi asynchroniczności.

## Użyte Technologie Zewnętrzne

- API Express: Wykorzystano zewnętrzne API do uzyskania bieżących stacji kolejowych.
- Baza Danych: Dane dotyczące kursów walut są przechowywane lokalnie w bazie danych za pomocą Room.
- Firebase: Do testowania i analityki aplikacji

## Funkcje

- Express pozwala na wybranie dwóch stacji kolejowych i obliczenie odległości między nimi.
- Obsługa małych urządzeń.
- Zastosowano architekturę MVVM z użyciem Android Jetpack.
- Przechowywanie aktualnych danych offline przez 24 godziny.
- Pole wyszukiwania, które filtruje stacje na podstawie pola "hits".

  

# Express Application ENG
- Android 9(SDK28)

## Technologies

- Android 9 (SDK28)
- Programming Language: Kotlin.
- Utilized technologies: Android Jetpack (Compose, ViewModel, Kotlin Flow), Retrofit for API communication, Room for local data storage, Kotlin Coroutines for asynchronous handling.

## External Technologies Used

- API Express: External APIs were utilized to obtain real-time train stations data.
- Database: Currency exchange rate data is stored locally in a database using Room.
- Firebase: Utilized for testing and app analytics.

## Features

- Express allows selection of two railway stations and calculates the distance between them.
- Support for small devices.
- MVVM architecture implemented using Android Jetpack.
- Offline storage of current data for 24 hours.
- Search field filtering stations based on the "hits" field.

 # Przyklad zwroconej odpowiedzi z API/ Example API Response

 - stations
```
 [
    {
        "id": 56903,
        "name": "Prudnik",
        "name_slug": "prudnik",
        "latitude": 50.32944848,
        "longitude": 17.58000142,
        "hits": 4623,
        "ibnr": 161,
        "city": "Prudnik",
        "region": "opolskie",
        "country": "Polska",
        "localised_name": null,
        "is_group": false,
        "has_announcements": false,
        "is_nearby_station_enabled": true
    },
    {
        "id": 1000070,
        "name": "Adršpach",
        "name_slug": "adrspach",
        "latitude": 50.615428,
        "longitude": 16.124869,
        "hits": 4254,
        "ibnr": 9392,
        "city": "",
        "region": "",
        "country": "Czechy",
        "localised_name": null,
        "is_group": false,
        "has_announcements": false,
        "is_nearby_station_enabled": true
    }
  ]
```

- station_keywords:
```
[
    {
        "id": 18692,
        "keyword": "Lotnisko Modlin",
        "station_id": 300000
    },
    {
        "id": 18703,
        "keyword": "Zabierzów Rząska",
        "station_id": 236232
    }
]
```
