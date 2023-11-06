# Репозиторий для Школы разработчиков Java
**Домашнее задание 3 по теме "Реляционные базы данных"**

![image](https://github.com/AirSunday/LearnJava/assets/42736248/0824dcd5-1632-4fc5-a060-cad2feb4b0e7)

**Задание:**
Реализовать хранение данных об успеваемости учеников в базе данных.

**Храниться должна следующая информация:**
- списки учеников;
- список учебных групп;
- учебные планы (у разных групп могут быть разные планы, на данном этапе учебный план это просто список предметов);
- успеваемость учеников;


**Необходимо:**
1) продумать схему хранения данных (спроектировать таблицы и связи между ними)
2) написать инициализирующий sql скрипт, создающий таблицы
3) реализовать класс в приложении, который будет отвечать за работу с БД (выполнять запросы для получения и модификации данных).
4) реализовать команды, которые будут из базы получать следующие статистические данные:
   - средние оценки по предметам в старших классах (10 и 11);
   - список всех отличников старше 14 лет;
   - среднюю оценку ученика по указанной фамилии (если найдено несколько учеников – нужно вывести список в формате ФИО, учебная группа, средняя оценка.
   Запрос статистки должен выполнятся через консоль;

**Требования по реализации:**
1. В качестве базы данных нужно использовать postgres, для локального запуска нужно его установить или использовать docker.
2. Класс, отвечающий за работу с базой должен реализовать паттерн Transaction Script. (https://martinfowler.com/eaaCatalog/transactionScript.html)
3. При работе с базой должны использовать Data transfer object. (https://martinfowler.com/eaaCatalog/dataTransferObject.html)
   для получения и сохранения данных в БД.
