# Практическое задание Java разработчик

Необходимо реализовать rest api с использованием любого известного фреймворка или библиотеки на Java >=17.

В задании нужно будет написать rest api для перенаправления запросов на https://jsonplaceholder.typicode.com/

Реализовать базовую авторизацию, проработать ролевую модель доступа к данным и ведение аудита.

**Требования**

1. Реализовать обработчики (GET, POST, PUT, DELETE), которые проксируют запросы к https://jsonplaceholder.typicode.com/
   * /api/posts/**
   * /api/users/**
   *  /api/albums/**
2. Реализовать базовую авторизацию, с несколькими аккаунтами, у которых будут разные роли.
3. Проработать ролевую модель доступа. Чтобы было минимум 4 роли - ROLE_ADMIN, ROLE_POSTS, ROLE_USERS, ROLE_ALBUMS
    * ROLE_ADMIN -- имеет доступ ко всем обработчикам
    * ROLE_POSTS -- имеет доступ к обработчикам /posts/**
    * ROLE_USERS -- имеет доступ к обработчикам /users/**
    * ROLE_ALBUMS -- имеет доступ к обработчикам /albums/**
4.  Реализовать ведение аудита действий. (дата-время, пользователь, имеет ли доступ, параметры запроса, ...).
5. Реализовать inmemory кэш, чтобы уменьшить число запросов к jsonplaceholder. То есть изменения данных сначала должны происходить в кэше, а потом отправляться запросы на jsonplaceholder.

**Будет плюсом**

0. Простота запуска вашего приложения. Желательно использовать gradle или maven.
1. Использование базы данных: 
    * для ведением аудита,
    * для хранения данных пользователей. 
2. Добавление rest api для создание пользователей.
3.  Расширенная ролевая модель. (например, ROLE_POSTS_VIEWER, ROLE_POSTS_EDITOR, ...).
4. Написать юнит тесты на написанный код.
5. Реализовать конечную точку для запросов по websocket
```http request
/ws
```
Все запросы на реализованную конечную точку перенаправляются на echo server https://websocket.org/tools/websocket-echo-server/

Также реализовать базовую авторизацию и ролевую модель, ведение аудита запросов. Кэш не требуется. 

Для подключения по websocket использовать wss://echo.websocket.org

# Запуск

```shell
git clone https://github.com/ExcaliBBur/vkProject.git

cd vkProject

mvn clean package

docker-compose build

docker-compose up -d
```

Сервер доступен по адресу [localhost:8080](http://localhost:8080/)

Спецификация доступна по адресу [localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

# Что дополнительно реализовано

- Система сборки Maven. Для запуска приложения используется Docker.
- Использование базы данных для ведения аудита и хранения пользователей
- Доступные пользователи по умолчанию: 
   * login: admin, password: adminadmin, роль: ROLE_ADMIN, привилегии: ROLE_ADMIN_VIEWER, ROLE_ADMIN_EDITOR
   * login: posts, password: postsposts, роль: ROLE_POSTS, привилегии: ROLE_POSTS_VIEWER, ROLE_POSTS_EDITOR
   * login: albums, password: albumsalbums, роль: ROLE_ALBUMS, привилегии: ROLE_ALBUMS_VIEWER, ROLE_ALBUMS_EDITOR
   * login: users, password: usersusers, роль: ROLE_USERS, привилегии: ROLE_USERS_VIEWER, ROLE_USERS_EDITOR
- Авторизация и аутентификация реализована с помощью JWT токенов, поддерживающих refresh токены. 
- REST api для создания и обновления пользователей
- Возможность экспорта Postman-коллекции для тестирования (файл postman_collection.json)
