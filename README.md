[![Build status](https://ci.appveyor.com/api/projects/status/gt21k41iw95uba8v?svg=true)](https://ci.appveyor.com/project/ElenaMughi/courseproject)

#Для запуска тестов необходимо
1. Скопировать репозиторий к себе на машину и открыть его в IDEA
2. Скачать Image Postgres (запустить в терминале docker pull postgres)
3. Запустить в терминале docker-compose.yml (docker-compose start)
4. Запустить aqa-shop.jar (java -jar .\aqa-shop.jar)
5. Запустить тесты (SiteTests)
6. Запустить Allure (./gradlew allureServe)
7. Посмотреть результат в Allure
