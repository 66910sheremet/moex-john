## moex2

### Описание
swagger-ui: http://path-to-stub/swagger-ui.html  

### запуск сервиса в docker локально
- mvn clean install -DskipTests
- docker build -t leovante/moex2 .
- docker compose up --build (заменить в файле: leovante/moex2:latest на moex2) 
- Your program changed?
    docker compose down -v
    docker rmi moex2
    docker build -t moex2 .
    docker compose up -d --build
- Invoke only your program inside a container:
    docker run --name moex2 -p 24300:8080 moex2

### запуск локально образа из docker hub
- docker compose up -d

### тест энпоинтов. Ввести в терминале любой запроос: (все ендпоинты это топики, которые возвращают stream-json)
### каждый ответ от биржи кешируется в БД. В случае повторного вызова с теми же датами будет запрос из БД.
- при старте приложения заполнится таблица securities списком актуальных акций на мос бирже. Так же можно запросить вручную.
curl -X GET "http://localhost:24300/iss/securities"

- в таблицу securities_trades сохранятся выполненные ордера текущей торговой сессии.
trades скачивает и в конце продолжает делать запросы пока не появятся новые записи, без остановки.
прокси на официальный https://iss.moex.com/iss/reference/55
curl -X GET "http://localhost:24300/iss/engines/stock/markets/shares/securities/sber/trades"

- в таблицу securities_history сохранятся исторические цены. Один день - одна запись.
history скачивает и автоматически останавливается после окончания.
прокси на официальный https://iss.moex.com/iss/reference/817
curl -X GET "http://localhost:24300/iss/history/engines/stock/markets/shares/boards/TQBR/securities/sber?from=2024-07-09&till=2024-07-11"

- в таблицу securities_candles сохраняются 10-ти минутные свечи.
прокси на официальный https://iss.moex.com/iss/reference/155
curl -X GET "http://localhost:24300/iss/engines/stock/markets/shares/securities/lkoh/candles?from=2024-01-01&till=2024-07-31&interval=1"

## [Apache Superset](https://superset.apache.org/)
Username:guest  
Password:guest 