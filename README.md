# Лабораторная работа №2,3
**Вариант 25**

## Игра в города

Сетевая игра в города для двух игроков. Игроки по очереди не повторяясь, называют города, начинающиеся на букву, на которую закончилось название города, названного другим игроком в прошлый раз. Игрок проигрывает, если не может назвать город,либо назвал город, ранее уже названный одним из игроков.

Для запуска сервера необходимо установить [node.js](https://nodejs.org/en/download/) и запустить [server.js](https://github.com/AlexandrovAn/city-game/blob/server/server.js) с помощью команды
```
node server.js
```
Также надо сменить путь для подлкючения в [SocketHandler](https://github.com/AlexandrovAn/city-game/blob/master/app/src/main/java/com/example/citygame/data/websocket/SocketHandler.kt)

```kotlin
@Synchronized
fun setSocket() {
        try {
            socket = IO.socket("http://192.168.1.103:3000")
        } catch (e: URISyntaxException) {
            Log.e("Socket error", e.toString())
        }
    }
 ```

Для запуска приложения для двух клинетов я использовал 2 эмулятора встроенные в [Android Studio](https://developer.android.com/studio)


Нам показалось, что слишком жестоко защитывать игроку поражение, если он ввел повтоярющееся слово, поэтому мы просто блокируем отправку такого ответа. В остальном игра полностью соотвествует варианту.

[Десериализация объекта справочника городов оттестирована по средствам JUnit](https://github.com/AlexandrovAn/city-game/blob/develop/app/src/test/java/com/example/citygame/data/entities/CityTest.kt)
