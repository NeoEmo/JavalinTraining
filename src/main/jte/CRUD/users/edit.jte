@import hexlet.code.CRUD.dto.UserPage

@param UserPage page

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Редактировать: ${page.getUser().getUsername()}</title>
</head>
<body>
<a href="/users">← Все пользователи</a>
<h1>Редактировать пользователя</h1>

<form method="POST" action="/users/${page.getUser().getId()}">
    <input type="hidden" name="_method" value="PATCH">

    <div>
        <label for="username">Username</label>
        <input type="text" id="username" name="username"
               value="${page.getUser().getUsername()}" required>
    </div>

    <div>
        <label for="email">Email</label>
        <input type="email" id="email" name="email"
               value="${page.getUser().getEmail()}" required>
    </div>

    <div>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
    </div>

    <button type="submit">Сохранить</button>
</form>
</body>
</html>