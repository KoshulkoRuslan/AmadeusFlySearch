# AmadeusFlySearch
Для правильной работы приложения необходимо заменить API_KEY и API_SECRET в build.gradle

Приложение для поиска авиабилетов. В качестве REST сервера используется тестовый API системы бронирования AMADEUS https://developers.amadeus.com/
Приложение состоит из трех экранов: 
-Главный фрагмент с полями для поиска (StartSearchFragment)
-Фрагмент поиска аэропортов (AirportSearchFragment)
-Фрагмент для установки параметров перелета (PassengerParamDialogFragment)
-Фрагмент с результатами поиска (FlyOfferResultFragment)

При запуске приложения происходит синхронизация с сервером для получения токена:
https://developers.amadeus.com/self-service/apis-docs/guides/authorization

При первом запуске происходит загрузка таблиц с IATA кодами городов и аэропортов. (ROOM + Retrofit)
Таблицы с городами и аэропортами используются в AirportSearchFragment для поиска. 
Для навигации между фрагментами используется Navigation. 

Для графического отображения сегментов перелета был создан простой CustomView - SegmentMap


