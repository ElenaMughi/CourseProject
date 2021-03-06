# Перечень автоматизируемых сценариев
## 2 сценария перехода к форме оплаты по кредиту
### 1. Оплата по кнопке Купить в кредит
1. Открываем сайт
2. Нажимаем на Купить в кредит
3. Переходим к форме кредита 
* Полученный результат: Форма открыта
### 2. Переход к форме купить в кредит
1. Открываем сайт
2. Нажимаем на Купить
3. Переходим к форме оплаты
4. Нажимаем на Купить в кредит
5. Переходим к форме кредита
* Полученный результат: Форма открыта

## Сценарии покупки в кредит
### 1. Отправка пустой формы
* Входные данные: нет
1. Переходим к форме кредита одним из доступных способов
2. Нажимаем кнопку Продолжить
3. Фиксируем сообщения об ошибках (незаполненная форма)
* Ожидаемый результат: Красные надписи под полями ввода "Неверный формат"
### 2. Отправка корректно заполненной формы с валидной картой
* Входные данные : карта 1111 2222 3333 4444; месяц 01; год (текущий год +1); ФИО с помощью faker.name(); CVC с помощью faker.number()
1. Переходим к форме кредита одним из доступных способов
2. Заполняем форму валидной картой 
3. Нажимаем кнопку Продолжить
4. Фиксируем сообщение об успешности заявки
* Ожидаемое сообщение: "Операция одобрена Банком".
### 3. Отправка корректно заполненной формы с невалидной картой
* Входные данные : карта 5555 6666 7777 8888; месяц 01; год (текущий год +1); ФИО с помощью faker.name(); CVC с помощью faker.number()
1. Переходим к форме кредита одним из доступных способов
2. Заполняем форму невалидной картой 
3. Нажимаем кнопку Продолжить
4. Фиксируем сообщение об отклонении заявки
* Ожидаемое сообщение: "Ошибка! Банк отказал в проведении операции."
### 4. Отправка корректно заполненной формы с произвольной картой
* Входные данные : карта 9999 8888 7777 6666; месяц 01; год (текущий год +1); ФИО с помощью faker.name(); CVC с помощью faker.number()
1. Переходим к форме кредита одним из доступных способов
2. Заполняем форму произвольной картой 
3. Нажимаем кнопку Продолжить
4. Фиксируем сообщение об отклонении заявки
* Ожидаемое сообщение: "Ошибка! Банк отказал в проведении операции."
### 5. Повторная отправка корректно заполненной формы с валидной картой
* Входные данные : карта 1111 2222 3333 4444; месяц 01; год (текущий год +1); ФИО с помощью faker.name(); CVC с помощью faker.number()
1. Переходим к форме кредита одним из доступных способов
2. Заполняем форму валидными данными 
3. Нажимаем кнопку Продолжить
4. Фиксируем сообщение об успешности заявки
5. Нажимаем кнопку Продолжить
6. Фиксируем сообщение об успешности заявки
* Ожидаемые сообщения: "Операция одобрена Банком".

## Автоматизация чек-листа проверки правильности заполнения каждого из полей
### 1. Проверка карт|Неверный формат
* Входные данные/варианты проверок поля Карта: "1", "1111 1111 1111 111", "AAAA 2222 3333 4444", "".
* Входные данные/остальные поля: месяц 01; год (текущий год +1); ФИО с помощью faker.name(); CVC с помощью faker.number()
1. Переход к заполнению поля Карта
2. Ввод некорректных данных 
3. Заполняем остальные поля 
4. Нажимаем Продолжить
* Ожидаемый результат: выдается сообщение-подсказка под полем "Неверный формат"
### 2. Проверка карт|Допустимые значения (валидная карта + цифра|символ)
* Входные данные/варианты проверок поля Карта: "1111 2222 3333 4444 1", "11$11 2222 3333 4444".
* Входные данные/остальные поля: месяц 01; год (текущий год +1); ФИО с помощью faker.name(); CVC с помощью faker.number()
1. Переход к заполнению поля Карта
2. Ввод некорректных данных
3. Заполняем остальные поля 
4. Нажимаем Продолжить
* Ожидаемый результат: Последняя цифра не вводится. Ожидаемое сообщение: под полем отсутствует сообщение-подсказка "Неверный формат"
### 3. Проверка месяца|Неверный формат
* Входные данные/варианты проверок поля Месяц: "0", "AA", "$", "1", "".
* Входные данные/остальные поля: карта 1111 2222 3333 4444; год (текущий год +1); ФИО с помощью faker.name(); CVC с помощью faker.number()
1. Переход к заполнению поля Месяц
2. Ввод некорректных данных 
3. Заполняем остальные поля 
4. Нажимаем Продолжить
* Ожидаемый результат: выдается сообщение-подсказка под полем "Неверный формат"
### 4. Проверка месяца|Неверно указан срок действия карты
* Входные данные/варианты проверок поля Месяц: "13", "210", "001", "000".
* Входные данные/остальные поля: карта 1111 2222 3333 4444; год (текущий год +1); ФИО с помощью faker.name(); CVC с помощью faker.number()
1. Переход к заполнению поля Месяц
2. Ввод некорректных данных
3. Заполняем остальные поля
4. Нажимаем Продолжить
* Ожидаемый результат: выдается сообщение-подсказка под полем "Неверно указан срок действия карты"
### 5. Проверка месяца|Допустимые значения (валидное значение + цифра)
* Входные данные/варианты проверок поля Месяц: "031".
* Входные данные/остальные поля: карта 1111 2222 3333 4444; год (текущий год +1); ФИО с помощью faker.name(); CVC с помощью faker.number()
1. Переход к заполнению поля Месяц
2. Ввод некорректных данных 
3. Заполняем остальные поля
4. Нажимаем Продолжить
*  Ожидаемый результат: Последняя цифра не вводится. Ожидаемое сообщение: под полем отсутствует сообщение-подсказка "Неверный формат"
### 6. Проверка года|Неверный формат
* Входные данные/варианты проверок поля Год: "", "0", (текущий год - 1), "AA", "$".
* Входные данные/остальные поля: карта 1111 2222 3333 4444; месяц 01; ФИО с помощью faker.name(); CVC с помощью faker.number()
1. Переход к заполнению поля Год
2. Ввод некорректных данных
3. Заполняем остальные поля
4. Нажимаем Продолжить
* Ожидаемый результат: выдается сообщение-подсказка под полем "Неверный формат"
### 7. Проверка года|Допустимые значения (валидное значение + цифра)
* Входные данные/варианты проверок поля Год: (текущий год + "1")
* Входные данные/остальные поля: карта 1111 2222 3333 4444; месяц 01; ФИО с помощью faker.name(); CVC с помощью faker.number()
1. Переход к заполнению поля Год
2. Ввод некорректных данных
3. Заполняем остальные поля
4. Нажимаем Продолжить
* Ожидаемый результат: Последняя цифра не вводится. Ожидаемое сообщение: под полем отсутствует сообщение-подсказка "Неверный формат"
### 8. Проверка месяца-года|Неверно указан срок действия карты
* Входные данные/варианты проверок полей месяц/год: (текущий месяц -1, текущий год).
* Входные данные/остальные поля: карта 1111 2222 3333 4444; ФИО с помощью faker.name(); CVC с помощью faker.number()
1. Переход к заполнению поля Месяц
2. Ввод месяца 
3. Ввод текущего года
4. Заполняем остальные поля 
5. Нажимаем Продолжить 
* Ожидаемый результат: выдается сообщение-подсказка под полем "Неверно указан срок действия карты"
### 9. Проверка месяца-года|Текущий месяц-год
* Входные данные/варианты проверок полей месяц/год: (текущий месяц + текущий год).
* Входные данные/остальные поля: карта 1111 2222 3333 4444; ФИО с помощью faker.name(); CVC с помощью faker.number()
1. Переход к заполнению поля Месяц
2. Ввод месяца 
3. Ввод текущего года
4. Заполняем остальные поля 
5. Нажимаем Продолжить
* Ожидаемый результат: под полем отсутствует сообщение-подсказка "Неверный формат"
### 10. Проверка FIO|Неверный формат
* Входные данные/варианты проверок поля Владелец: "Q".
* Входные данные/остальные поля: карта 1111 2222 3333 4444; месяц 01; год (текущий год +1); CVC с помощью faker.number()
1. Переход к заполнению поля Владелец
2. Ввод некорректных данных 
3. Заполняем остальные поля
4. Нажимаем Продолжить
* Ожидаемый результат: выдается сообщение-подсказка под полем "Неверный формат"
### 11. Проверка FIO|Поле требует заполнения
* Входные данные/варианты проверок поля Владелец: "Иванов", "$%&", "123456", "".
* Входные данные/остальные поля: карта 1111 2222 3333 4444; месяц 01; год (текущий год +1); CVC с помощью faker.number()
1. Переход к заполнению поля Владелец
2. Ввод некорректных данных 
3. Заполняем остальные поля
4. Нажимаем Продолжить
* Ожидаемый результат: выдается сообщение-подсказка под полем "Поле требует заполнения"
### 12. Проверка FIO|Допустимые значения
* Входные данные/варианты проверок поля Владелец: "Ivanov-Ivan", "Ivanov".
* Входные данные/остальные поля: карта 1111 2222 3333 4444; месяц 01; год (текущий год +1); CVC с помощью faker.number()
1. Переход к заполнению поля Владелец
2. Ввод некорректных данных 
3. Заполняем остальные поля
4. Нажимаем Продолжить
* Ожидаемый результат: под полем отсутствует сообщение-подсказка "Неверный формат"
### 13. Проверка CVC/CVV|Неверный формат
* Входные данные/варианты проверок поля CVC/CVV: "0", "9", "99", "000".
* Входные данные/остальные поля: карта 1111 2222 3333 4444; месяц 01; год (текущий год +1); ФИО с помощью faker.name()
1. Переход к заполнению поля CVC/CVV
2. Ввод некорректных данных 
3. Заполняем остальные поля
4. Нажимаем Продолжить
* Ожидаемый результат: выдается сообщение-подсказка под полем "Неверный формат"
### 14. Проверка CVC/CVV|Поле требует заполнения
* Входные данные/варианты проверок поля CVC/CVV: "Q", "$%&", "".
* Входные данные/остальные поля: карта 1111 2222 3333 4444; месяц 01; год (текущий год +1); ФИО с помощью faker.name()
1. Переход к заполнению поля CVC/CVV
2. Ввод некорректных данных 
3. Заполняем остальные поля
4. Нажимаем Продолжить
* Ожидаемый результат: выдается сообщение-подсказка под полем "Поле требует заполнения"
### 15. Проверка CVC/CVV|Допустимые значения
* Входные данные/варианты проверок поля CVC/CVV: "9999".
* Входные данные/остальные поля: карта 1111 2222 3333 4444; месяц 01; год (текущий год +1); ФИО с помощью faker.name()
1. Переход к заполнению поля CVC/CVV
2. Ввод некорректных данных
3. Заполняем остальные поля
4. Нажимаем Продолжить
*  Ожидаемый результат: Последняя цифра не вводится. Ожидаемое сообщение: под полем отсутствует сообщение-подсказка "Неверный формат"

# Перечень используемых инструментов с обоснованием выбора
* IntelliJ IDEA - среда разработки в которой будут писаться тесты.
* Java - язык программирования на котором будут написаны тесты.
* Gradle - система автоматической сборки, для создания и сборки проекта.
* JUnit Jupiter - фреймворк для создания автотестов.
* Selenide - фреймворк-дополнение к JUnit Jupiter облегчающий написание тестов.
* Git - место для хранения и обновления тестов
* Браузер Chrome - браузер на котором будет проводиться прогон автотестов
* Docker Desktop - приложение для создания и управления контейнерами (в частости postgres)
* postgres/latest - контейнер с СУБД Postgres для развертывания базы (последняя версия)
* DBeaver - приложение для просмотра и изучения используемой БД
* Allure - фреймворк для сбора статистики прогонов тестов


# Перечень и описание возможных рисков при автоматизации
* Наличие соединения с интернетом для обработки запросов к "банковскому сервису"
* Не все может быть автоматизировано при наличии критических ошибок


# Интервальная оценка с учётом рисков (в часах)
* 80 часов (2 недели, рабочие часы)


#План сдачи работ (когда будут автотесты, результаты их прогона и отчёт по автоматизации)
* 7-13 июля - создание автотестов
* 14-17 июля - прогон тестов и создание отчета по результатам, возможно с мелкими правками тестов
* 18-20 июля - финальные отчеты и сдача проекта