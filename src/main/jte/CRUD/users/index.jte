@import hexlet.code.CRUD.dto.UsersPage
@import hexlet.code.CRUD.model.User

@param UsersPage page

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Пользователи</title>
</head>
<body>
<h1>Пользователи</h1>

<a href="/users/build">Добавить пользователя</a>

@if(page.getUsers().isEmpty())
    <p>Пока ни одного пользователя</p>
@else
    <ul>
        @for(User user : page.getUsers())
            <li>
                <a href="/users/${user.getId()}">${user.getUsername()}</a>
                — ${user.getEmail()}
            </li>
        @endfor
    </ul>
@endif
</body>
</html>